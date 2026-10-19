package org.ceenjs.ceenjs.ui.main.drawer

interface IPermissionAdbItem : IPermissionItem {

    fun requestWithAdb(): Boolean

    fun revokeWithAdb(): Boolean

}
