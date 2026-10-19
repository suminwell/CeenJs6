package org.ceenjs.ceenjs.rhino;

import android.util.Log;
import org.ceenjs.ceenjs.runtime.exception.ScriptInterruptedException;
import org.mozilla.javascript.Context;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import static org.ceenjs.ceenjs.util.RhinoUtils.isBackgroundThread;

public class InterruptibleAndroidContextFactory extends AndroidContextFactory {

    private final AtomicInteger mContextCount = new AtomicInteger();
    private static final String LOG_TAG = "ContextFactory";

    /**
     * Create a new factory. It will cache generated code in the given directory
     *
     * @param cacheDirectory the cache directory
     */
    public InterruptibleAndroidContextFactory(File cacheDirectory) {
        super(cacheDirectory);
    }

    @Override
    protected void observeInstructionCount(Context cx, int instructionCount) {
        if (Thread.currentThread().isInterrupted() && isBackgroundThread()) {
            throw new ScriptInterruptedException();
        }
    }

    @Override
    protected Context makeContext() {
        Context cx = new CeenJsContext(this);
        cx.setInstructionObserverThreshold(10000);
        return cx;
    }

    @Override
    protected void onContextCreated(Context cx) {
        super.onContextCreated(cx);
        int i = mContextCount.incrementAndGet();
        Log.d(LOG_TAG, "onContextCreated: count = " + i);
    }

    @Override
    protected void onContextReleased(Context cx) {
        super.onContextReleased(cx);
        int i = mContextCount.decrementAndGet();
        Log.d(LOG_TAG, "onContextReleased: count = " + i);
    }

}
