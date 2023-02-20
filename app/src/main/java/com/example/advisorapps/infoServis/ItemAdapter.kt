package com.example.advisorapps.infoServis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.advisorapps.databinding.ItemRvItemBinding

class ItemAdapter(private val list: ArrayList<ResponseInfo>,
                    private val click: () -> Unit
): RecyclerView.Adapter<ItemAdapter.ItemAdapterHolder>() {

    inner class ItemAdapterHolder(val itemRvItemBinding: ItemRvItemBinding): RecyclerView.ViewHolder(itemRvItemBinding.root){
        fun bindItem(responseInfo: ResponseInfo){
            itemRvItemBinding.tviPgj.text = responseInfo.pgj
            itemRvItemBinding.tviKet.text = responseInfo.ket
            itemRvItemBinding.tviEstimasi.text = responseInfo.estimasi
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterHolder {
        return ItemAdapterHolder(ItemRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemAdapterHolder, position: Int) {
        holder.bindItem(list[position])
        holder.itemView.setOnClickListener {
            click()
        }

    }

    override fun getItemCount(): Int = list.size
}