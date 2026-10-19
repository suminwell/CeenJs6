package org.ceenjs.ceenjs.runtime.api.augment.web

import org.ceenjs.ceenjs.annotation.RhinoFunctionBody
import org.ceenjs.ceenjs.annotation.RhinoRuntimeFunctionInterface
import org.ceenjs.ceenjs.core.web.InjectableWebClient
import org.ceenjs.ceenjs.core.web.InjectableWebView
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.isJsNullish
import org.ceenjs.ceenjs.rhino.extension.AnyExtensions.jsUnwrapped
import org.ceenjs.ceenjs.rhino.ArgumentGuards
import org.ceenjs.ceenjs.rhino.extension.ScriptableExtensions.prop
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import org.ceenjs.ceenjs.runtime.exception.WrappedIllegalArgumentException
import org.ceenjs.ceenjs.util.RhinoUtils.coerceString
import org.ceenjs.ceenjs.util.RhinoUtils.withRhinoContext
import org.mozilla.javascript.Context
import android.content.Context as AndroidContext
import org.ceenjs.ceenjs.core.web.WebSocket as CoreWebSocket

class Web(scriptRuntime: ScriptRuntime) : Augmentable(scriptRuntime) {

    override val selfAssignmentFunctions = listOf(
        ::newInjectableWebView.name to AS_GLOBAL,
        ::newInjectableWebClient.name to AS_GLOBAL,
        ::newWebSocket.name to AS_GLOBAL,
    )

    companion object : ArgumentGuards() {

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun newInjectableWebView(scriptRuntime: ScriptRuntime, args: Array<out Any?>): InjectableWebView = ensureArgumentsAtMost(args, 2) {
            newInjectableWebViewRhinoWithRuntime(scriptRuntime, *it)
        }

        @JvmStatic
        @RhinoFunctionBody
        fun newInjectableWebViewRhinoWithRuntime(scriptRuntime: ScriptRuntime, vararg args: Any?): InjectableWebView = withRhinoContext(scriptRuntime) { cx ->
            when (args.size) {
                2 -> {
                    val (androidContext, url) = args
                    val niceUrl = when {
                        url.isJsNullish() -> null
                        else -> Context.toString(url)
                    }
                    val contextForWebView = androidContext.jsUnwrapped() as? AndroidContext ?: globalContext
                    InjectableWebView(contextForWebView, cx, scriptRuntime.topLevelScope, niceUrl)
                }
                1 -> when {
                    args[0] is String -> {
                        newInjectableWebViewRhinoWithRuntime(scriptRuntime, scriptRuntime.topLevelScope.prop("activity"), args[0])
                    }
                    else -> newInjectableWebViewRhinoWithRuntime(scriptRuntime, args[0], null)
                }
                0 -> newInjectableWebViewRhinoWithRuntime(scriptRuntime, scriptRuntime.topLevelScope.prop("activity"))
                else -> throw WrappedIllegalArgumentException("Invalid arguments length ${args.size} for web.newInjectableWebView")
            }
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun newInjectableWebClient(scriptRuntime: ScriptRuntime, args: Array<out Any?>): InjectableWebClient = ensureArgumentsIsEmpty(args) {
            withRhinoContext(scriptRuntime) { cx -> InjectableWebClient(cx, scriptRuntime.topLevelScope) }
        }

        @JvmStatic
        @RhinoRuntimeFunctionInterface
        fun newWebSocket(scriptRuntime: ScriptRuntime, args: Array<out Any?>): CoreWebSocket = ensureArgumentsOnlyOne(args) {
            CoreWebSocket(scriptRuntime, scriptRuntime.http.okhttp, coerceString(it))
        }

    }

}