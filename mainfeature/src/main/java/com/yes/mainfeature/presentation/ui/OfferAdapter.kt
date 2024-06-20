package com.yes.mainfeature.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yes.mainfeature.databinding.OfferItemBinding
import com.yes.mainfeature.presentation.model.OfferUi
import java.util.Collections
@Deprecated("use instead adapter delegate")
class OfferAdapter(
    private val context: Context
) : RecyclerView.Adapter<OfferAdapter.ViewHolder>() {
    private val itemList = mutableListOf<OfferUi>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OfferAdapter.ViewHolder {
        val binding = OfferItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    fun setItems(items: List<OfferUi>) {
        // itemList=items
        //  val size = itemList.size
        itemList.clear()
        // notifyItemRangeRemoved(0, size)
        itemList.addAll(items)
        //  notifyItemRangeInserted(0, items.size)
        notifyDataSetChanged()
//notifyItemRangeChanged(0,itemList.size)
    }

    fun clearItewms() {
        itemList.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): OfferUi {
        return itemList[position]
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(itemList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(itemList, i, i - 1)
            }
        }
        // Collections.swap(itemList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onBindViewHolder(holder: OfferAdapter.ViewHolder, position: Int) {
        holder.bind(position, itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }



    inner class ViewHolder(private val binding: OfferItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            position: Int,
            item: OfferUi,
        ) {
            Glide
                .with(context)
                .load(itemList[position].image)
                .fitCenter()
                .into(binding.offerImage)
           /* binding.offerImage.setImageResource(
                itemList[position].image
            )*/
            binding.offerTitle.text=itemList[position].title
            binding.offerTown.text=itemList[position].town
            binding.offerPrice.text=itemList[position].price

        }
    }
}