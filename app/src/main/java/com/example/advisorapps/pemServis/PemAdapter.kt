package com.example.advisorapps.pemServis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.advisorapps.databinding.ItemRvPemberitahuanBinding

class PemAdapter(
    private val list: ArrayList<ResponsePem>,
    private val click: () -> Unit
): RecyclerView.Adapter<PemAdapter.PemViewHolder>() {

    inner class PemViewHolder(val itemRvPemberitahuanBinding: ItemRvPemberitahuanBinding)
        : RecyclerView.ViewHolder(itemRvPemberitahuanBinding.root){

        fun bindItems(responsePem: ResponsePem){
            itemRvPemberitahuanBinding.tvModel.text = responsePem.model
            itemRvPemberitahuanBinding.tvJenisServis.text = responsePem.jenis_servis
            itemRvPemberitahuanBinding.tvKeluhan.text = responsePem.keluhan
            itemRvPemberitahuanBinding.tvSaran.text = responsePem.saran
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemViewHolder {
        return PemViewHolder(ItemRvPemberitahuanBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PemViewHolder, position: Int) {
        holder.bindItems(list[position])
        holder.itemView.setOnClickListener {
            click()
        }
    }

    override fun getItemCount(): Int = list.size
}