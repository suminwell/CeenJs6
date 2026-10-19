package org.ceenjs.ceenjs.execution;

import org.ceenjs.ceenjs.AbstractCeenJs;
import org.ceenjs.ceenjs.core.looper.Loopers;
import org.ceenjs.ceenjs.engine.LoopBasedJavaScriptEngine;
import org.ceenjs.ceenjs.engine.ScriptEngine;
import org.ceenjs.ceenjs.engine.ScriptEngineManager;
import org.ceenjs.ceenjs.inrt.ceenjs.LoopBasedJavaScriptEngineWithDecryption;
import org.ceenjs.ceenjs.script.JavaScriptSource;
import org.ceenjs.ceenjs.script.ScriptSource;

/**
 * Created by Stardust on Oct 27, 2017.
 */
public class LoopedBasedJavaScriptExecution extends RunnableScriptExecution {

    public LoopedBasedJavaScriptExecution(ScriptEngineManager manager, ScriptExecutionTask task) {
        super(manager, task);
    }

    protected Object doExecution(ScriptEngine<? extends ScriptSource> engine) {
        engine.setTag(ScriptEngine.TAG_SOURCE, getSource());
        getListener().onStart(this);
        long delay = getConfig().getDelay();
        sleep(delay);
        long interval = getConfig().getInterval();

        var javaScriptEngine = AbstractCeenJs.isInrt()
                ? (LoopBasedJavaScriptEngineWithDecryption) engine
                : (LoopBasedJavaScriptEngine) engine;
        javaScriptEngine.getRuntime().loopers.setMainLooperQuitHandler(new Loopers.LooperQuitHandler() {
            long times = getConfig().getLoopTimes() == 0 ? Integer.MAX_VALUE : getConfig().getLoopTimes();

            @Override
            public boolean shouldQuit() {
                times--;
                if (times > 0) {
                    sleep(interval);
                    javaScriptEngine.execute(getSource());
                    return false;
                }
                javaScriptEngine.getRuntime().loopers.setMainLooperQuitHandler(null);
                return true;
            }
        });
        javaScriptEngine.execute(getSource());

        return null;
    }

    @Override
    public JavaScriptSource getSource() {
        return (JavaScriptSource) super.getSource();
    }

}
