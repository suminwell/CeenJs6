package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.widget.AdapterView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AdapterViewInflater<V : AdapterView<*>>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ViewGroupInflater<V>(scriptRuntime, resourceParser) {
    // Empty inflater.
}
