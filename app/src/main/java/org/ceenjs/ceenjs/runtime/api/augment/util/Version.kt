package org.ceenjs.ceenjs.runtime.api.augment.util

import android.os.Build
import org.ceenjs.ceenjs.runtime.api.augment.Augmentable

@Suppress("unused")
object Version : Augmentable() {

    override val selfAssignmentProperties = listOf(
        "sdkInt" to Build.VERSION.SDK_INT,
    )

}
