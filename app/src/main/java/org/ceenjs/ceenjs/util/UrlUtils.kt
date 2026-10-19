package org.ceenjs.ceenjs.util

import android.content.Context
import org.ceenjs.ceenjs6.R

object UrlUtils {

    @JvmStatic
    private fun ensureUrl(context: Context, url: String?) {
        val regexUrl = "^(https?|ftp|file)://[-a-zA-Z\\d+&@#/%?=~_|!:,.;]*[-a-zA-Z\\d+&@#/%=~_|]"
        require(url != null && url.matches(regexUrl.toRegex())) {
            context.getString(R.string.error_illegal_url_argument)
        }
    }

}