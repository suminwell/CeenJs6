package org.ceenjs.ceenjs.ui.widget;

import android.app.Activity;

import androidx.drawerlayout.widget.DrawerLayout;

import org.ceenjs.ceenjs.event.BackPressedHandler;

/**
 * Created by Stardust on Jun 19, 2017.
 */
public class DrawerAutoClose implements BackPressedHandler {

    private final DrawerLayout mDrawerLayout;
    private final int mGravity;

    public DrawerAutoClose(DrawerLayout drawerLayout, int gravity){
        mDrawerLayout = drawerLayout;
        mGravity = gravity;
    }

    @Override
    public boolean onBackPressed(Activity activity) {
        if (mDrawerLayout.isDrawerOpen(mGravity)) {
            mDrawerLayout.closeDrawer(mGravity);
            return true;
        }
        return false;
    }

}