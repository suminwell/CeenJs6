package org.ceenjs.ceenjs.runtime.api.augment.global

import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import org.ceenjs.ceenjs.runtime.api.augment.Invokable
import org.mozilla.javascript.Scriptable.NOT_FOUND
import org.mozilla.javascript.Undefined

object IsNullish : Augmentable(), Invokable {

    override fun invoke(vararg args: Any?): Boolean = args.all {
        Undefined.isUndefined(it) || it == null || it == NOT_FOUND
    }

}
