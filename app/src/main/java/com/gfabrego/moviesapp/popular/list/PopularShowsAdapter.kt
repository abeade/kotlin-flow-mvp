package com.gfabrego.moviesapp.popular.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gfabrego.moviesapp.R
import com.gfabrego.moviesapp.popular.domain.model.Show

internal class PopularShowsAdapter : ListAdapter<Show, PopularShowViewHolder>(ModularDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularShowViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_popular_show,
            parent,
            false
        )
        return PopularShowViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PopularShowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: PopularShowViewHolder) {
        holder.clear()
    }

    private class ModularDiffCallback : DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean = oldItem == newItem
    }
}
