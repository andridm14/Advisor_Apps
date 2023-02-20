package com.example.advisorapps.dafServis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.advisorapps.databinding.ItemRvDataServisBinding

class ServisAdapter(private val list: ArrayList<ResponseServis>,
                    private val click: () -> Unit)
    : RecyclerView.Adapter<ServisAdapter.ServisViewHolder>() {

    inner class ServisViewHolder(val itemRvDataServisBinding: ItemRvDataServisBinding): RecyclerView.ViewHolder(itemRvDataServisBinding.root){
        fun bindservis(responseServis: ResponseServis){
            itemRvDataServisBinding.tvStnk.text = responseServis.stnk
            itemRvDataServisBinding.tvModel.text = responseServis.model
            itemRvDataServisBinding.tvJns.text = responseServis.jenis_servis
            itemRvDataServisBinding.tvKeluhan.text = responseServis.keluhan
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServisViewHolder {
        return ServisViewHolder(ItemRvDataServisBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ServisViewHolder, position: Int) {
        holder.bindservis(list[position])
        holder.itemView.setOnClickListener {
            click()
        }
    }

    override fun getItemCount(): Int = list.size
}