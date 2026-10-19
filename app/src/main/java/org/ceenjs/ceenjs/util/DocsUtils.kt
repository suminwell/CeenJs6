package org.ceenjs.ceenjs.util

import org.ceenjs.ceenjs.core.pref.Pref
import org.ceenjs.ceenjs.util.StringUtils.key
import org.ceenjs.ceenjs.util.StringUtils.str
import org.ceenjs.ceenjs6.R

object DocsUtils {

    private val docsUrlBody
        get() = Pref.getStringOrNull(R.string.key_documentation_source).let {
            when (it == null || isLocal(it)) {
                true -> "file:///android_asset/docs/"
                else -> str(R.string.documentation_base_url)
            }
        }

    @JvmStatic
    fun getUrl(suffix: String) = "${docsUrlBody.replace(Regex("/$"), "")}/${suffix.replace(Regex("^/"), "")}"

    private fun isLocal(source: String) = source == key(R.string.key_documentation_source_local)

}
