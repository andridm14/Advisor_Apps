package com.example.advisorapps.pemServis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.advisorapps.databinding.ItemRvPemberitahuanBinding

class PemAdapter(private val list: ArrayList<ResponsePem>): RecyclerView.Adapter<PemAdapter.PemViewHolder>() {

    inner class PemViewHolder(val itemRvPemberitahuanBinding: ItemRvPemberitahuanBinding): RecyclerView.ViewHolder(itemRvPemberitahuanBinding.root){
        fun bindItem(responsePem: ResponsePem){
            itemRvPemberitahuanBinding.tvpgj.text = responsePem.pgj
            itemRvPemberitahuanBinding.tvTgl.text = responsePem.tgl
            itemRvPemberitahuanBinding.tvSaran1.text = responsePem.saran_at
            itemRvPemberitahuanBinding.tvSaran2.text = responsePem.saran_n
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemViewHolder {
        return PemViewHolder(ItemRvPemberitahuanBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PemViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size
}