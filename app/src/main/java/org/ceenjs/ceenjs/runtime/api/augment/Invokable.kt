package org.ceenjs.ceenjs.runtime.api.augment

import org.ceenjs.ceenjs.util.RhinoUtils.DEFAULT_CALLER

interface Invokable {

    fun invoke(vararg args: Any?): Any? = DEFAULT_CALLER

}
