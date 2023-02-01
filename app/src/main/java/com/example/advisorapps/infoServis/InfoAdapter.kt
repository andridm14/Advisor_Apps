package com.example.advisorapps.infoServis


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.advisorapps.databinding.ItemRvInfoBinding

class InfoAdapter(private val list: ArrayList<ResponseInfo>): RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    inner class InfoViewHolder(val itemRvInfoBinding: ItemRvInfoBinding): RecyclerView.ViewHolder(itemRvInfoBinding.root) {
        fun bindItem(responseInfo: ResponseInfo){
            itemRvInfoBinding.tvPgj.text = responseInfo.pgj
            itemRvInfoBinding.tvEstimasi.text = responseInfo.estimasi
            itemRvInfoBinding.tvKet.text = responseInfo.ket
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(ItemRvInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size


}