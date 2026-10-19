package org.ceenjs.ceenjs.execution;

import android.util.Log;
import org.ceenjs.ceenjs.engine.ScriptEngine;
import org.ceenjs.ceenjs.engine.ScriptEngineManager;
import org.ceenjs.ceenjs.lang.ThreadCompat;
import org.ceenjs.ceenjs.runtime.exception.ScriptInterruptedException;
import org.ceenjs.ceenjs.script.ScriptSource;

/**
 * Created by Stardust on May 1, 2017.
 */
public class RunnableScriptExecution extends ScriptExecution.AbstractScriptExecution implements Runnable {

    private static final String TAG = "RunnableJSExecution";
    private ScriptEngine<? extends ScriptSource> mScriptEngine;
    private final ScriptEngineManager mScriptEngineManager;

    public RunnableScriptExecution(ScriptEngineManager manager, ScriptExecutionTask task) {
        super(task);
        mScriptEngineManager = manager;
    }

    @Override
    public void run() {
        ThreadCompat.currentThread().setName("ScriptThread-" + getId() + "[" + getSource() + "]");
        execute();
    }

    public Object execute() {
        mScriptEngine = mScriptEngineManager.createEngineOfSourceOrThrow(getSource(), getId());
        mScriptEngine.setTag(ExecutionConfig.tag, getConfig());
        return execute(mScriptEngine);
    }

    private Object execute(ScriptEngine<? extends ScriptSource> engine) {
        try {
            prepare(engine);
            Object r = doExecution(engine);
            Throwable uncaughtException = engine.getUncaughtException();
            if (uncaughtException != null) {
                onException(engine, uncaughtException);
                return null;
            }
            getListener().onSuccess(this, r);
            return r;
        } catch (Throwable e) {
            onException(engine, e);
            return null;
        } finally {
            Log.d(TAG, "Engine destroy");
            engine.destroy();
        }
    }

    protected void onException(ScriptEngine<? extends ScriptSource> engine, Throwable e) {
        Log.w(TAG, "onException: engine = " + engine, e);
        getListener().onException(this, e);
    }

    private void prepare(ScriptEngine<? extends ScriptSource> engine) {
        engine.setTag(ScriptEngine.TAG_WORKING_DIRECTORY, getConfig().getWorkingDirectory());
        engine.setTag(ScriptEngine.TAG_ENV_PATH, getConfig().getEnvPath());
        engine.init();
    }

    protected Object doExecution(ScriptEngine<? extends ScriptSource> engine) {
        engine.setTag(ScriptEngine.TAG_SOURCE, getSource());
        getListener().onStart(this);
        Object result = null;
        long delay = getConfig().getDelay();
        int times = getConfig().getLoopTimes();
        if (times == 0) {
            times = Integer.MAX_VALUE;
        }
        long interval = getConfig().getInterval();
        sleep(delay);
        ScriptSource source = getSource();
        for (int i = 0; i < times; i++) {
            result = execute(engine, source);
            sleep(interval);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    protected Object execute(ScriptEngine engine, ScriptSource source) {
        return engine.execute(source);
    }

    protected void sleep(long i) {
        if (i <= 0) {
            return;
        }
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new ScriptInterruptedException();
        }
    }

    @Override
    public ScriptEngine<? extends ScriptSource> getEngine() {
        return mScriptEngine;
    }

}