package org.ceenjs.ceenjs.runtime.api

import org.ceenjs.ceenjs.core.http.MutableOkHttp

/**
 * Created by SuperMonster003 on Jul 7, 2024.
 */
class Http {

    val okhttp = MutableOkHttp()

    fun client() = okhttp.client()

}
