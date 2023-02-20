package com.example.advisorapps.lapServis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.advisorapps.databinding.ItemRvLaporanBinding

class LapAdapter(private val list: ArrayList<ResponseLap>): RecyclerView.Adapter<LapAdapter.LapViewHolder>() {

    inner class LapViewHolder(val itemRvLaporanBinding: ItemRvLaporanBinding): RecyclerView.ViewHolder(itemRvLaporanBinding.root){
        fun bindItem(responseLap: ResponseLap){
            itemRvLaporanBinding.tvModel.text = responseLap.model
            itemRvLaporanBinding.tvStnk.text = responseLap.stnk
            itemRvLaporanBinding.tvJns.text = responseLap.jenis_servis
            itemRvLaporanBinding.tvPgj.text = responseLap.pgj
            itemRvLaporanBinding.tvTgl.text = responseLap.tgl
            itemRvLaporanBinding.tvEstimasi.text = responseLap.estimasi
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LapViewHolder {
        return LapViewHolder(ItemRvLaporanBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LapViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size
}