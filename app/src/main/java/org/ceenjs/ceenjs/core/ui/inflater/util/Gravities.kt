package org.ceenjs.ceenjs.core.ui.inflater.util

import android.annotation.SuppressLint
import android.view.Gravity
import org.ceenjs.ceenjs.core.pref.Language
import org.ceenjs.ceenjs.util.StringUtils.str
import org.ceenjs.ceenjs6.R

/**
 * Created by Stardust on Nov 3, 2017.
 * Modified by SuperMonster003 as of May 26, 2022.
 * Transformed by SuperMonster003 on Nov 12, 2023.
 */
object Gravities {

    @Throws(IllegalArgumentException::class)
    @SuppressLint("RtlHardcoded")
    fun parse(gravity: String): Int {
        var result = Gravity.NO_GRAVITY
        gravity.lowercase(Language.getPrefLanguage().locale).split(Regex("[|]")).forEach { part ->
            result = when (part.trim()) {
                "" -> result
                "left" -> result or Gravity.LEFT
                "start", "textStart" -> result or Gravity.START
                "top" -> result or Gravity.TOP
                "right" -> result or Gravity.RIGHT
                "end", "textEnd" -> result or Gravity.END
                "bottom" -> result or Gravity.BOTTOM
                "center_horizontal" -> result or Gravity.CENTER_HORIZONTAL
                "center_vertical" -> result or Gravity.CENTER_VERTICAL
                "center" -> result or Gravity.CENTER
                else -> str(R.string.error_unknown_gravity_for, part.trim()).let {
                    throw IllegalArgumentException(it)
                }
            }
        }
        return result
    }

}
