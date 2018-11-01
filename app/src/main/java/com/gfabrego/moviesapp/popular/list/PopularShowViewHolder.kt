package com.gfabrego.moviesapp.popular.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gfabrego.moviesapp.popular.domain.model.Show
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_popular_show.*

internal class PopularShowViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: Show) {
        Glide.with(containerView.context).load(item.poster.toExternalForm()).into(ivShowImage)
    }

    fun clear() {
        Glide.with(containerView.context).clear(ivShowImage)
    }
}
