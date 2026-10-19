package org.ceenjs.ceenjs.runtime.api.augment

import org.ceenjs.ceenjs.util.RhinoUtils.DEFAULT_CONSTRUCTOR

interface Constructable {

    fun construct(vararg args: Any?): Any? = DEFAULT_CONSTRUCTOR

}
