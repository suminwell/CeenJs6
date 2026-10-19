package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.VideoView
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.core.ui.inflater.util.Strings
import org.ceenjs.ceenjs.runtime.ScriptRuntime

open class VideoViewAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : SurfaceViewAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as VideoView

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttrs(arrayOf("videoPath", "path", "src")) { view.setVideoPath(Strings.parsePath(view, it) ?: return@registerAttrs) }
    }

}
