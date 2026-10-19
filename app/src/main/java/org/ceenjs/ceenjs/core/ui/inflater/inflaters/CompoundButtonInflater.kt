package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.widget.CompoundButton
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class CompoundButtonInflater<V : CompoundButton>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : ButtonInflater<V>(scriptRuntime, resourceParser) {
    // Empty inflater.
}
