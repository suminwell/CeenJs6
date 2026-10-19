package org.ceenjs.ceenjs.theme.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.SwitchCompat;

import org.ceenjs.ceenjs.theme.ThemeColor;
import org.ceenjs.ceenjs.theme.ThemeColorHelper;
import org.ceenjs.ceenjs.theme.ThemeColorManager;
import org.ceenjs.ceenjs.theme.ThemeColorMutable;

/**
 * Created by Stardust on Aug 7, 2016.
 */
public class ThemeColorSwitch extends SwitchCompat implements ThemeColorMutable {

    public ThemeColorSwitch(Context context) {
        super(context);
        init();
    }

    public ThemeColorSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThemeColorSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ThemeColorManager.add(this);
    }

    @Override
    public void setThemeColor(ThemeColor color) {
        ThemeColorHelper.setColorPrimary(this, color.colorPrimary, true);
    }

}
