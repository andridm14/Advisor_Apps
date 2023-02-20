package com.example.advisorapps.splashScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.advisorapps.databinding.OnboardingItemContainerBinding

class AdapterOnboardingitem(private val onboardingItems: List<OnboardingItem>)
    :RecyclerView.Adapter<AdapterOnboardingitem.OnboardingItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(OnboardingItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onboardingItems[position])
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }

    inner class OnboardingItemViewHolder(val onboardingItemContainerBinding: OnboardingItemContainerBinding)
        : RecyclerView.ViewHolder(onboardingItemContainerBinding.root){

            private val imageOnboarding = onboardingItemContainerBinding.imgOnboarding
            private val textTitle = onboardingItemContainerBinding.tvTitle
            private val textDescription = onboardingItemContainerBinding.tvDescription

        fun bind(onboardingItem: OnboardingItem){
            imageOnboarding.setImageResource(onboardingItem.onboardingImage)
            textTitle.text = onboardingItem.title
            textDescription.text = onboardingItem.description
        }
    }
}