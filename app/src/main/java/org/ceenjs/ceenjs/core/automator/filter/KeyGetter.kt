package org.ceenjs.ceenjs.core.automator.filter

import org.ceenjs.ceenjs.core.automator.UiObject

/**
 * Created by Stardust on Mar 9, 2017.
 */
interface KeyGetter {

    fun getKey(nodeInfo: UiObject): String?

}
