'use strict';

( /* @IIFE */ () => {

    // @Reference to .../asset/web/dist/ceenjs.sdk.v1.js from Ceen.js Pro 9.3.11 on May 24, 2025.

    const EVENT_RESPONSE = '$ceenjs:internal:response';
    const EVENT_REQUEST = '$ceenjs:internal:request';

    // noinspection JSValidateTypes
    /** @type {import('events')} */
    let nodejsEvents = events;

    let nextId = 1;

    let helper = {
        isPromise: o => o && typeof o.then === 'function',
        unwrapJson: o => o ? JSON.parse(o) : undefined,
        wrapJson: o => o === undefined ? o : JSON.stringify(o),
    };

    Object.setPrototypeOf($ceenjs, new nodejsEvents.EventEmitter());

    /** @type {Internal.CeenJsBridge} */
    let CeenJsBridge = {
        requestHandlers: {},
        onEventInternal(event, args) {
            this.emit(event, ...helper.unwrapJson(args));
        },
        send(event, ...args) {
            this.sendEventInternal(event, helper.wrapJson(args));
        },
        invoke(channel, ...args) {
            let id = nextId++;
            return new Promise((resolve, reject) => {
                this.once(EVENT_RESPONSE + ':' + id, (result) => {
                    if (result.error) {
                        reject(new Error('Error occurred while handling invoke: channel = ' + channel + ', error = ' + result.error));
                    } else {
                        resolve(result.result);
                    }
                });
                this.send(EVENT_REQUEST, {
                    id, channel, args,
                });
            });
        },
        /** @this {Internal.CeenJsBridge} */
        handle(channel, handler) {
            this.requestHandlers[channel || ''] = handler;
            return this;
        },
        removeHandler(channel) {
            delete this.requestHandlers[channel];
        },
        handleRequest(request) {
            let handler = this.requestHandlers[request.channel] || this.requestHandlers[''];
            if (!handler) {
                return;
            }
            let event = {
                channel: request.channel,
                arguments: request.args,
            };
            let result;
            try {
                result = handler(event, ...event.arguments);
            } catch (e) {
                this.sendResponse(request, undefined, e);
                return;
            }
            if (helper.isPromise(request)) {
                result.then((r) => {
                    this.sendResponse(request, r);
                }).catch((err) => {
                    this.sendResponse(request, undefined, err);
                });
            } else {
                this.sendResponse(request, result);
            }
        },
        sendResponse(request, result, error) {
            this.send(EVENT_RESPONSE + ':' + request.id, { result, error });
        },
    };

    $ceenjs = Object.assign($ceenjs, CeenJsBridge);
    $ceenjs.on(EVENT_REQUEST, CeenJsBridge.handleRequest.bind($ceenjs));

})();
