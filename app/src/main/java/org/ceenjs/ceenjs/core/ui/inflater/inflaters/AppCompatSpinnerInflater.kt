package org.ceenjs.ceenjs.core.ui.inflater.inflaters

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatSpinner
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.ViewCreator
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class AppCompatSpinnerInflater<V : AppCompatSpinner>(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser) : SpinnerInflater<V>(scriptRuntime, resourceParser) {

    override fun getCreator(): ViewCreator<in V> = object : ViewCreator<AppCompatSpinner> {
        override fun create(context: Context, attrs: HashMap<String, String>, parent: ViewGroup?): AppCompatSpinner {
            return AppCompatSpinner(context)
        }
    }

}
