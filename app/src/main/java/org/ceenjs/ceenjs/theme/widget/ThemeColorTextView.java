package org.ceenjs.ceenjs.theme.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import org.ceenjs.ceenjs.theme.ThemeColor;
import org.ceenjs.ceenjs.theme.ThemeColorManager;
import org.ceenjs.ceenjs.theme.ThemeColorMutable;

/**
 * Created by Stardust on Mar 5, 2017.
 */
public class ThemeColorTextView extends AppCompatTextView implements ThemeColorMutable {

    public ThemeColorTextView(Context context) {
        super(context);
        init();
    }

    public ThemeColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThemeColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ThemeColorManager.add(this);
    }

    @Override
    public void setThemeColor(ThemeColor color) {
        setTextColor(color.colorPrimary);
    }
}
