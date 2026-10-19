package org.ceenjs.ceenjs.inrt

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import org.ceenjs.ceenjs.core.console.ConsoleImpl
import org.ceenjs.ceenjs.inrt.ceenjs.CeenJs
import org.ceenjs.ceenjs.inrt.launch.GlobalProjectLauncher
import org.ceenjs.ceenjs.ui.BaseActivity
import org.ceenjs.ceenjs.util.ViewUtils
import org.ceenjs.ceenjs6.R
import org.ceenjs.ceenjs6.databinding.ActivityMainInrtBinding

class LogActivity : BaseActivity() {

    override val handleStatusBarThemeColorAutomatically = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        if (intent.getBooleanExtra(EXTRA_LAUNCH_SCRIPT, false)) {
            GlobalProjectLauncher.launch(this)
        }
    }

    private fun setupView() {
        ActivityMainInrtBinding.inflate(layoutInflater).also { binding ->
            setContentView(binding.root)
            binding.toolbar.apply {
                setSupportActionBar(this)
                ViewUtils.setStatusBarIconLight(this@LogActivity, false)
            }
            binding.console.apply {
                setConsole(CeenJs.instance.globalConsole as ConsoleImpl)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, SettingsActivity::class.java))
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main_inrt, menu)
        return true
    }

    companion object {

        const val EXTRA_LAUNCH_SCRIPT = "launch_script"

    }

}
