package org.ceenjs.ceenjs.ui.main.scripts

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginBottom
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import org.ceenjs.ceenjs.app.GlobalAppContext
import org.ceenjs.ceenjs.external.fileprovider.AppFileProvider
import org.ceenjs.ceenjs.model.explorer.ExplorerItem
import org.ceenjs.ceenjs.model.script.Scripts
import org.ceenjs.ceenjs.tool.SimpleObserver
import org.ceenjs.ceenjs.ui.common.ScriptOperations
import org.ceenjs.ceenjs.ui.explorer.ExplorerView
import org.ceenjs.ceenjs.ui.fragment.BindingDelegates.viewBinding
import org.ceenjs.ceenjs.ui.main.FloatingActionMenu
import org.ceenjs.ceenjs.ui.main.FloatingActionMenu.OnFloatingActionButtonClickListener
import org.ceenjs.ceenjs.ui.main.MainActivity
import org.ceenjs.ceenjs.ui.main.QueryEvent
import org.ceenjs.ceenjs.ui.main.ViewPagerFragment
import org.ceenjs.ceenjs.ui.main.ViewStatesManageable
import org.ceenjs.ceenjs.ui.project.ProjectConfigActivity
import org.ceenjs.ceenjs.ui.widget.ScrollAwareFABBehavior
import org.ceenjs.ceenjs.util.IntentUtils
import org.ceenjs.ceenjs.util.IntentUtils.SnackExceptionHolder
import org.ceenjs.ceenjs.util.IntentUtils.ToastExceptionHolder
import org.ceenjs.ceenjs.util.IntentUtils.startSafely
import org.ceenjs.ceenjs.util.ViewUtils.excludePaddingClippableViewFromBottomNavigationBar
import org.ceenjs.ceenjs6.R
import org.ceenjs.ceenjs6.databinding.FragmentExplorerBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Stardust on Mar 13, 2017.
 * Modified by SuperMonster003 as of Mar 20, 2022.
 * Transformed by SuperMonster003 on Mar 31, 2023.
 */
class ExplorerFragment : ViewPagerFragment(0), OnFloatingActionButtonClickListener, ViewStatesManageable {

    private val binding by viewBinding(FragmentExplorerBinding::bind)

    private var mExplorerView: ExplorerView? = null
    private var mFloatingActionMenu: FloatingActionMenu? = null
    private var mIsCurrentPageFiles = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentExplorerBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mExplorerView = binding.itemList.also { explorerView ->
            explorerView.setOnItemClickListener(object : ExplorerView.OnItemClickListener {
                override fun onItemClick(view: View?, item: ExplorerItem) {
                    when {
                        item.isTextEditable -> Scripts.edit(requireActivity(), item.toScriptFile())
                        item.isInstallable -> ApkInfoDialogManager.showApkInfoDialog(requireActivity(), item.toScriptFile())
                        item.isMediaMenu || item.isMediaPlayable -> MediaInfoDialogManager.showMediaInfoDialog(requireActivity(), item)
                        else -> viewFile(item)
                    }
                }
            })
            explorerView.explorerItemListView?.excludePaddingClippableViewFromBottomNavigationBar()
        }
        (activity as? MainActivity)?.apply {
            val tabLayout: TabLayout = findViewById(R.id.tab)
            val filesTab = tabLayout.getTabAt(filesItemIndex)
            filesTab?.view?.let { setTabViewClickListeners(it) }
        }
        restoreViewStates()
    }

    private fun setTabViewClickListeners(tabView: TabLayout.TabView) {
        tabView.setOnLongClickListener { if (mIsCurrentPageFiles) true.also { toggleFabVisibility() } else false }
    }

    private fun toggleFabVisibility() {
        val behavior = (fab.layoutParams as? CoordinatorLayout.LayoutParams)?.behavior as? ScrollAwareFABBehavior

        when {
            behavior == null || fab.translationY == 0f -> {
                if (fab.isShown) fab.hide() else fab.show()
            }
            else -> fab.animate()
                .translationY(0f)
                .setDuration(ScrollAwareFABBehavior.DURATION)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        behavior.setHidden(false)
                    }
                })
                .start()
        }
    }

    private fun viewFile(item: ExplorerItem): Boolean {
        val context = context ?: GlobalAppContext.get()
        val exceptionHolder = view?.let { SnackExceptionHolder(it) } ?: ToastExceptionHolder(context)
        return IntentUtils.viewFile(
            context = context,
            path = item.path,
            mimeType = null,
            fileProviderAuthority = AppFileProvider.AUTHORITY,
            exceptionHolder = exceptionHolder,
        )
    }

    override fun onFabClick(fab: FloatingActionButton) {
        initFloatingActionMenuIfNeeded(fab).run { if (isExpanded) collapse() else expand() }
    }

    private fun initFloatingActionMenuIfNeeded(fab: FloatingActionButton): FloatingActionMenu {
        return mFloatingActionMenu ?: requireActivity().findViewById<FloatingActionMenu>(R.id.floating_action_menu).also { menu ->
            val locFab = IntArray(2)
            fab.getLocationOnScreen(locFab)
            menu.state
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SimpleObserver<Boolean?>() {
                    override fun onNext(expanding: Boolean) {
                        fab.animate()
                            .rotation((if (expanding) 45 else 0).toFloat())
                            .setDuration(300)
                            .start()
                    }
                })
            menu.setOnFloatingActionButtonClickListener(this)
            menu.layoutParams.runCatching {
                javaClass.getField("bottomMargin").setInt(this, fab.marginBottom)
            }
            menu.setToggleFab(fab, locFab)
            mFloatingActionMenu = menu
        }
    }

    override fun onBackPressed(activity: Activity): Boolean {
        mFloatingActionMenu?.let {
            if (it.isExpanded) {
                it.collapse()
                return@onBackPressed true
            }
        }
        mExplorerView?.let {
            if (it.canGoBack()) {
                it.goBack()
                return@onBackPressed true
            }
        }
        return false
    }

    override fun onPageShow() {
        super.onPageShow()
        mIsCurrentPageFiles = true
    }

    override fun onPageHide() {
        super.onPageHide()
        mFloatingActionMenu?.let { if (it.isExpanded) it.collapse() }
        mIsCurrentPageFiles = false
    }

    @Subscribe
    fun onQuerySummit(event: QueryEvent) {
        if (!isShown) {
            return
        }
        if (event === QueryEvent.CLEAR) {
            mExplorerView?.setFilter(null)
            return
        }
        if (event === QueryEvent.FIND_FORWARD) {
            return
        }
        if (event === QueryEvent.FIND_BACKWARD) {
            return
        }
        mExplorerView?.setFilter { item: ExplorerItem -> item.name.contains(event.query, true) }
    }

    override fun onStop() {
        super.onStop()
        if (activity?.isFinishing == false) {
            saveViewStates()
        }
        mExplorerView?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        ExplorerView.clearViewStates()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mExplorerView = null
        mFloatingActionMenu = null
    }

    override fun onDetach() {
        super.onDetach()
        mFloatingActionMenu?.setOnFloatingActionButtonClickListener(null)
    }

    override fun onClick(button: FloatingActionButton, pos: Int) {
        mExplorerView?.let { view ->
            val ctx = context ?: return@let
            when (pos) {
                3 -> Intent(ctx, ProjectConfigActivity::class.java)
                    .putExtra(ProjectConfigActivity.EXTRA_PARENT_DIRECTORY, view.currentPage.path)
                    .putExtra(ProjectConfigActivity.EXTRA_NEW_PROJECT, true)
                    .startSafely(ctx)
                2 -> ScriptOperations(ctx, view, view.currentPage)
                    .importFile()
                1 -> ScriptOperations(ctx, view, view.currentPage)
                    .newFile()
                0 -> ScriptOperations(ctx, view, view.currentPage)
                    .newDirectory()
                else -> Unit
            }
        }
    }

    override fun saveViewStates() {
        mExplorerView?.saveViewStates()
    }

    override fun restoreViewStates() {
        mExplorerView?.restoreViewStates()
    }

}