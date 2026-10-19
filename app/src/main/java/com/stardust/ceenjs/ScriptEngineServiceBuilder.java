package com.stardust.ceenjs;

import com.stardust.ceenjs.engine.ScriptEngineManager;
import com.stardust.ceenjs.runtime.ScriptRuntime;
import com.stardust.ceenjs.runtime.api.Console;
import com.stardust.util.Supplier;
import com.stardust.util.UiHandler;

/**
 * Created by Stardust on 2017/4/2.
 */

public class ScriptEngineServiceBuilder {

    ScriptEngineManager mScriptEngineManager;
    Console mGlobalConsole;
    UiHandler mUiHandler;

    public ScriptEngineServiceBuilder() {

    }

    public ScriptEngineServiceBuilder uiHandler(UiHandler uiHandler) {
        mUiHandler = uiHandler;
        return this;
    }

    public ScriptEngineServiceBuilder engineManger(ScriptEngineManager manager) {
        mScriptEngineManager = manager;
        return this;
    }

    public ScriptEngineServiceBuilder globalConsole(Console console) {
        mGlobalConsole = console;
        return this;
    }

    public ScriptEngineService build() {
        return new ScriptEngineService(this);
    }


}
