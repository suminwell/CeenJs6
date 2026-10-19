package org.ceenjs.ceenjs.ui.edit.debug;

import org.ceenjs.ceenjs.CeenJs;
import org.ceenjs.ceenjs.rhino.debug.Debugger;
import org.mozilla.javascript.ContextFactory;

public class DebuggerSingleton {

    private static final Debugger sDebugger = new Debugger(CeenJs.getInstance().getScriptEngineService(), ContextFactory.getGlobal());

    public static Debugger get(){
        return sDebugger;
    }

}
