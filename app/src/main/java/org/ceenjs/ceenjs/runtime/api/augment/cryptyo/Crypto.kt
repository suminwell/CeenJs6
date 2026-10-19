package org.ceenjs.ceenjs.runtime.api.augment.cryptyo

import org.ceenjs.ceenjs.runtime.api.augment.Augmentable
import kotlin.reflect.KClass
import org.ceenjs.ceenjs.core.crypto.Crypto as CoreCrypto

object Crypto : Augmentable() {

    override val selfAssignmentJavaClasses = listOf<Pair<String, KClass<*>>>(
        "Key" to CoreCrypto.Key::class,
        "KeyPair" to CoreCrypto.KeyPair::class,
    )

}