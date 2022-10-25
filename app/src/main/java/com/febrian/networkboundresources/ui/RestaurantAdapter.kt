package com.febrian.networkboundresources.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.febrian.networkboundresources.data.Restaurant
import com.febrian.networkboundresources.databinding.RestaurantItemBinding

class RestaurantAdapter(private var listRestaurant: ArrayList<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    fun setData(newListData: ArrayList<Restaurant>) {
        val diffResult = DiffUtil.calculateDiff(RestaurantComparator(listRestaurant, newListData))
        listRestaurant = newListData
        diffResult.dispatchUpdatesTo(this)
    }

    class RestaurantComparator(
        private val oldItems: ArrayList<Restaurant>,
        private val newItems: ArrayList<Restaurant>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition].name == newItems[newItemPosition].name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems == newItems
        }
    }

    inner class ViewHolder(private val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            binding.apply {
                Glide.with(itemView)
                    .load(restaurant.logo)
                    .into(imageViewLogo)

                textViewName.text = restaurant.name
                textViewType.text = restaurant.type
                textViewAddress.text = restaurant.address
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RestaurantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listRestaurant[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return listRestaurant.size
    }
}