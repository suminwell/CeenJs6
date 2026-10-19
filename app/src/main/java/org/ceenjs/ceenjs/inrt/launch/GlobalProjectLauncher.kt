package org.ceenjs.ceenjs.inrt.launch

import android.annotation.SuppressLint
import org.ceenjs.ceenjs.app.GlobalAppContext

/**
 * Created by Stardust on Mar 21, 2018.
 */
@SuppressLint("StaticFieldLeak")
object GlobalProjectLauncher: AssetsProjectLauncher("project", GlobalAppContext.get())
