package org.ceenjs.ceenjs.theme.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.ceenjs.ceenjs.theme.ThemeColor;
import org.ceenjs.ceenjs.theme.ThemeColorManager;
import org.ceenjs.ceenjs.theme.ThemeColorMutable;
import org.ceenjs.ceenjs.util.ColorUtils;
import org.ceenjs.ceenjs6.R;

/**
 * Created by Stardust on Jan 23, 2018.
 */
public class ThemeColorSwipeRefreshLayout extends SwipeRefreshLayout implements ThemeColorMutable {

    public ThemeColorSwipeRefreshLayout(Context context) {
        super(context);
        init();
    }

    public ThemeColorSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ThemeColorManager.add(this);
        setProgressBackgroundColorSchemeResource(R.color.swipe_refresh_background);
    }

    @Override
    public void setThemeColor(ThemeColor themeColor) {
        int backgroundColor = getContext().getColor(R.color.swipe_refresh_background);
        int adjustedColor = ColorUtils.adjustColorForContrast(backgroundColor, themeColor.colorPrimary, 2.3);
        setColorSchemeColors(adjustedColor);
    }

}
