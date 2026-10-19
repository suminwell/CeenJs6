package org.ceenjs.ceenjs.execution;

import org.ceenjs.ceenjs.CeenJs;
import org.ceenjs.ceenjs.core.pref.Language;
import org.ceenjs.ceenjs.engine.ScriptEngine;
import org.ceenjs.ceenjs.runtime.api.Console;
import org.ceenjs.ceenjs.script.ScriptSource;
import org.ceenjs.ceenjs6.R;

import static org.ceenjs.ceenjs.util.StringUtils.str;

/**
 * Created by Stardust on May 3, 2017.
 * Modified by SuperMonster003 as of Jan 1, 2022.
 */
public class ScriptExecutionGlobalListener implements ScriptExecutionListener {

    private static final String ENGINE_TAG_START_TIME = "org.ceenjs.ceenjs.ceenjs.Goodbye, World";

    @Override
    public void onStart(ScriptExecution execution) {
        ScriptEngine<? extends ScriptSource> engine = execution.getEngine();
        engine.setTag(ENGINE_TAG_START_TIME, System.currentTimeMillis());
    }

    @Override
    public void onSuccess(ScriptExecution execution, Object result) {
        onFinish(execution);
    }

    private void onFinish(ScriptExecution execution) {
        ScriptEngine<? extends ScriptSource> engine = execution.getEngine();
        Long startTime = (Long) engine.getTag(ENGINE_TAG_START_TIME);
        if (startTime != null) {
            printSeconds(execution, startTime);
        }
    }

    private void printSeconds(ScriptExecution execution, Long startTime) {
        double seconds = (System.currentTimeMillis() - startTime) / 1000.0;
        String secondsString = String.format(Language.getPrefLanguage().getLocale(), "%.3f", seconds).stripTrailing();
        Console console = CeenJs.getInstance().getScriptEngineService().getGlobalConsole();
        String path = execution.getSource().getElegantPath();
        console.verbose(str(R.string.text_execution_finished, path, secondsString));
    }

    @Override
    public void onException(ScriptExecution execution, Throwable e) {
        onFinish(execution);
    }
}