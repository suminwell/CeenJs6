package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class DrawerLayoutAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ViewGroupAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as DrawerLayout

}
