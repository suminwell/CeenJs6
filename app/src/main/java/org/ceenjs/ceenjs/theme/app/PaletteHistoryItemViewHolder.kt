package org.ceenjs.ceenjs.theme.app

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.ceenjs.ceenjs.theme.ThemeColorHelper
import org.ceenjs.ceenjs.util.TimeUtils
import org.ceenjs.ceenjs6.databinding.MtColorLibraryRecyclerViewItemBinding

class PaletteHistoryItemViewHolder(itemViewBinding: MtColorLibraryRecyclerViewItemBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

    private val colorView: ImageView = itemViewBinding.color
    private val hexView: TextView = itemViewBinding.name
    private val lastUsedView: TextView = itemViewBinding.description

    fun bind(item: ColorEntities.PaletteHistory) {
        ThemeColorHelper.setBackgroundColor(colorView, item.colorInfo.colorInt)
        hexView.text = item.colorInfo.hex
        lastUsedView.text = TimeUtils.formatTimestamp(item.lastUsedTime)
    }

}