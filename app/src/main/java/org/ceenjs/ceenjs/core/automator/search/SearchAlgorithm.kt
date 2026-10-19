package org.ceenjs.ceenjs.core.automator.search

import org.ceenjs.ceenjs.core.automator.UiObject
import org.ceenjs.ceenjs.core.automator.filter.Filter

interface SearchAlgorithm {

    fun search(root: UiObject, filter: Filter, limit: Int = Int.MAX_VALUE): ArrayList<UiObject>

}