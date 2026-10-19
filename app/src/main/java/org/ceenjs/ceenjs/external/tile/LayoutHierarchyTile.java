package org.ceenjs.ceenjs.external.tile;

import android.content.Context;
import org.ceenjs.ceenjs.core.accessibility.Capture;
import org.ceenjs.ceenjs.ui.floating.FullScreenFloatyWindow;
import org.ceenjs.ceenjs.ui.floating.layoutinspector.LayoutHierarchyFloatyWindow;

public class LayoutHierarchyTile extends LayoutInspectTileService {

    @Override
    protected FullScreenFloatyWindow onCreateWindow(Capture capture, Context context) {
        return new LayoutHierarchyFloatyWindow(capture, context, true) {
            @Override
            public void close() {
                super.close();
                updateTile();
            }
        };
    }
}
