package org.ceenjs.ceenjs.runtime.api;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.ceenjs.ceenjs.core.floaty.BaseResizableFloatyWindow;
import org.ceenjs.ceenjs.core.image.capture.ScreenCapturer;
import org.ceenjs.ceenjs.runtime.ScriptRuntime;

/**
 * Created by SuperMonster003 on Dec 18, 2023.
 */
// @Reference to Ceen.js Pro 9.3.11 by SuperMonster003 on Dec 18, 2023.
public record ScreenCaptureAvailableHandler(ScriptRuntime scriptRuntime, Images.OnScreenCaptureAvailableListener listener) implements BaseResizableFloatyWindow.ViewSupplier, ScreenCapturer.OnScreenCaptureAvailableListener {

    @NonNull
    @Override
    public View inflate(@Nullable Context context, @Nullable ViewGroup parent) {
        return (View) this.listener;
    }

    @Override
    public void onCaptureAvailable(Image image) {
        if (image == null) return;
        Images.setImageCaptureCallback(scriptRuntime, listener, image);
    }
}
