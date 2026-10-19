package org.ceenjs.ceenjs.ui.settings

import android.os.Bundle
import org.ceenjs.ceenjs.theme.ThemeColorManager
import org.ceenjs.ceenjs.ui.BaseActivity
import org.ceenjs.ceenjs.util.ViewUtils.setToolbarAsBack
import org.ceenjs.ceenjs6.R
import org.ceenjs.ceenjs6.databinding.ActivityDeveloperOptionsBinding

/**
 * Created by SuperMonster003 on Jun 2, 2022.
 */
class DeveloperOptionsActivity : BaseActivity() {

    private lateinit var binding: ActivityDeveloperOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeveloperOptionsBinding.inflate(layoutInflater).also { binding ->
            setContentView(binding.root)
            binding.toolbar.setNavigationOnClickListener { finish() }
        }

        setToolbarAsBack(this, R.string.text_developer_options)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_developer_options, DeveloperOptionsFragment())
            .disallowAddToBackStack()
            .commit()
    }

    override fun onStart() {
        super.onStart()
        binding.toolbar.navigationIcon?.setTint(ThemeColorManager.getDayOrNightColorByLuminance(this))
    }

}
