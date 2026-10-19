package org.ceenjs.ceenjs.core.accessibility;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.ceenjs.ceenjs.AbstractCeenJs;
import org.ceenjs.ceenjs.core.activity.ActivityInfoProvider;
import org.ceenjs.ceenjs.runtime.accessibility.AccessibilityConfig;

public class AccessibilityBridgeImpl extends AccessibilityBridge {

    private final AbstractCeenJs mCeenJs;
    private final AccessibilityTool mA11yTool = new AccessibilityTool();
    private final String TAG = AccessibilityBridgeImpl.class.getSimpleName();

    public AccessibilityBridgeImpl(AbstractCeenJs ceenJs) {
        super(ceenJs.getApplicationContext(), new AccessibilityConfig(), ceenJs.getUiHandler());
        mCeenJs = ceenJs;
    }

    @Override
    public void ensureServiceStarted(boolean isForcibleRestart) {
        if (isForcibleRestart && mA11yTool.hasService()) {
            mA11yTool.stopService(true);
            Log.d(TAG, "isForcibleRestart");
        }
        mA11yTool.ensureService();
    }

    public void ensureServiceStarted() {
        ensureServiceStarted(false);
    }

    @Nullable
    @Override
    public AccessibilityService getService() {
        return AccessibilityService.Companion.getInstance();
    }

    @Override
    public ActivityInfoProvider getInfoProvider() {
        return mCeenJs.getInfoProvider();
    }

    @NonNull
    @Override
    public AccessibilityNotificationObserver getNotificationObserver() {
        return mCeenJs.getNotificationObserver();
    }

}
