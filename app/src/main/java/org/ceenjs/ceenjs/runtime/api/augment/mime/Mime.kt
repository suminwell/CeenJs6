package org.ceenjs.ceenjs.runtime.api.augment.mime

import org.ceenjs.ceenjs.runtime.ScriptRuntime
import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import org.ceenjs.ceenjs.runtime.api.augment.Invokable
import org.ceenjs.ceenjs.util.RhinoUtils.coerceString

class Mime(scriptRuntime: ScriptRuntime) : Augmentable(scriptRuntime), Invokable {

    override fun invoke(vararg args: Any?): JsMime = ensureArgumentsOnlyOne(args) { mimeStr ->
        JsMime(coerceString(mimeStr))
    }

}
