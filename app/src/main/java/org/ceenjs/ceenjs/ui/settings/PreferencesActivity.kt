package org.ceenjs.ceenjs.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.ceenjs.ceenjs.app.GlobalAppContext
import org.ceenjs.ceenjs.theme.ThemeColorManager
import org.ceenjs.ceenjs.ui.BaseActivity
import org.ceenjs.ceenjs.util.IntentUtils.startSafely
import org.ceenjs.ceenjs6.R
import org.ceenjs.ceenjs6.databinding.ActivityPreferencesBinding

/**
 * Created by Stardust on Feb 2, 2017.
 * Modified by SuperMonster003 as of Dec 1, 2021.
 */
open class PreferencesActivity : BaseActivity() {

    private lateinit var binding: ActivityPreferencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.toolbar.apply {
                setTitle(R.string.text_settings)
                setSupportActionBar(this)
                setNavigationOnClickListener { finish() }
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_preferences, PreferencesFragment())
            .disallowAddToBackStack()
            .commit()
    }

    override fun onStart() {
        super.onStart()
        binding.toolbar.navigationIcon?.setTint(ThemeColorManager.getDayOrNightColorByLuminance(this))
    }

    companion object {

        @JvmStatic
        @JvmOverloads
        fun launch(context: Context = GlobalAppContext.get()) {
            Intent(context, PreferencesActivity::class.java)
                .startSafely(context)
        }

    }

}