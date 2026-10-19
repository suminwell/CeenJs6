package org.ceenjs.ceenjs.ui.main.drawer

interface IPermissionRootItem : IPermissionItem {

    fun requestWithRoot(): Boolean

    fun revokeWithRoot(): Boolean

}
