package org.ceenjs.ceenjs.external.tile;

import android.content.Context;
import org.ceenjs.ceenjs.core.accessibility.Capture;
import org.ceenjs.ceenjs.ui.floating.FullScreenFloatyWindow;
import org.ceenjs.ceenjs.ui.floating.layoutinspector.LayoutBoundsFloatyWindow;

public class LayoutBoundsTile extends LayoutInspectTileService {

    @Override
    protected FullScreenFloatyWindow onCreateWindow(Capture capture, Context context) {
        return new LayoutBoundsFloatyWindow(capture, context, true) {
            @Override
            public void close() {
                super.close();
                updateTile();
            }
        };
    }

}
