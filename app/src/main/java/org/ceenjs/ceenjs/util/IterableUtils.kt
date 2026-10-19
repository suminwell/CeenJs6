package org.ceenjs.ceenjs.util

import org.ceenjs.ceenjs.util.StringUtils.looseKey

object IterableUtils {

    @JvmStatic
    fun Iterable<String>.containsLoosely(value: String): Boolean {
        val key = value.looseKey()
        return any { it.looseKey() == key }
    }

}