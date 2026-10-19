package com.stardust.ceenjs.rhino.debug;

import org.ceenjs.ceenjs.engine.ScriptEngineService;
import org.mozilla.javascript.ContextFactory;

public class Debugger extends org.ceenjs.ceenjs.rhino.debug.Debugger {
    public Debugger(ScriptEngineService scriptEngineService, ContextFactory contextFactory) {
        super(scriptEngineService, contextFactory);
    }
}
