package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.widget.AbsSpinner
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AbsSpinnerInflater<V : AbsSpinner>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : AdapterViewInflater<V>(scriptRuntime, resourceParser) {
    // Empty inflater.
}
