package org.ceenjs.ceenjs.runtime.exception;

import org.ceenjs.ceenjs.annotation.ScriptInterfaceCompatible;

/**
 * Created by Stardust on Apr 30, 2017.
 */
public class ScriptInterruptedException extends ScriptException {

    public ScriptInterruptedException() {
        /* Empty body. */
    }

    public ScriptInterruptedException(Throwable e) {
        super(e);
    }

    @Deprecated
    @ScriptInterfaceCompatible
    public static boolean causedByInterrupted(Throwable e) {
        return causedByInterrupt(e);
    }

    public static boolean causedByInterrupt(Throwable e) {
        while (e != null) {
            if (e instanceof ScriptInterruptedException || e instanceof InterruptedException) {
                return true;
            }
            e = e.getCause();
        }
        return false;
    }

}
