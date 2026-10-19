package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class RecyclerViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ViewGroupAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as RecyclerView

}
