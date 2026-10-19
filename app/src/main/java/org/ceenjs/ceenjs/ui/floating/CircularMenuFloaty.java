package org.ceenjs.ceenjs.ui.floating;

import org.ceenjs.ceenjs.ui.enhancedfloaty.FloatyService;

public interface CircularMenuFloaty {

    CircularActionView inflateActionView(FloatyService service, CircularMenuWindow window);

    CircularActionMenu inflateMenuItems(FloatyService service, CircularMenuWindow window);

}
