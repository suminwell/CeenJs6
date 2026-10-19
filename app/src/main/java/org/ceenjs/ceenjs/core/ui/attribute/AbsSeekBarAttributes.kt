package org.ceenjs.ceenjs.core.ui.attribute

import android.view.View
import android.widget.AbsSeekBar
import org.ceenjs.ceenjs.core.ui.inflater.ResourceParser
import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.util.ColorUtils

open class AbsSeekBarAttributes(scriptRuntime: ScriptRuntime, resourceParser: ResourceParser, view: View) : ProgressBarAttributes(scriptRuntime, resourceParser, view) {

    override val view = super.view as AbsSeekBar

    override fun onRegisterAttrs(scriptRuntime: ScriptRuntime) {
        super.onRegisterAttrs(scriptRuntime)

        registerAttr("keyProgressIncrement") { view.keyProgressIncrement = it.toInt() }
        registerAttr("splitTrack") { view.splitTrack = it.toBoolean() }
        registerAttr("thumb") { view.thumb = drawables.parse(view, it) }
        registerAttr("thumbOffset") { view.thumbOffset = it.toInt() }
        registerAttr("thumbTintList") { view.thumbTintList = ColorUtils.toColorStateList(view, it) }
        registerAttr("tickMark") { view.tickMark = drawables.parse(view, it) }
        registerAttr("tickMarkTintList") { view.tickMarkTintList = ColorUtils.toColorStateList(view, it) }
    }
}
