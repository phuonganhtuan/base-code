package com.example.demomotion.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.demomotion.R
import com.example.demomotion.data.entity.DemoEntity
import com.example.demomotion.databinding.ItemSwipeableWithOptionsBinding

class DemoSwipeAdapter :
    ListAdapter<DemoEntity, DemoSwipeAdapter.TrackViewHolder>(TrackDiffCallBack()) {

    private val viewBinderHelper = ViewBinderHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val itemViewBinding =
            ItemSwipeableWithOptionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TrackViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        viewBinderHelper.setOpenOnlyOne(false)
        viewBinderHelper.bind(
            holder.itemView.findViewById(R.id.swipeLayout), getItem(position).id.toString()
        )
        viewBinderHelper.closeLayout(getItem(position).id.toString())
        holder.bindData(getItem(position), position)
    }

    inner class TrackViewHolder(
        private val itemViewBinding: ItemSwipeableWithOptionsBinding,
    ) :
        RecyclerView.ViewHolder(itemViewBinding.root) {


        init {
        }

        fun bindData(entity: DemoEntity, position: Int) = with(itemViewBinding) {
        }
    }
}

class TrackDiffCallBack : DiffUtil.ItemCallback<DemoEntity>() {

    override fun areContentsTheSame(oldItem: DemoEntity, newItem: DemoEntity) =
        oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: DemoEntity, newItem: DemoEntity) =
        oldItem === newItem
}
