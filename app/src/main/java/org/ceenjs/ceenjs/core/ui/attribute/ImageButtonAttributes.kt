package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.ImageButton
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class ImageButtonAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ImageViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as ImageButton

}
