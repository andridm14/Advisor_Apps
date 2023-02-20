package com.example.advisorapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.advisorapps.databinding.ActivitySplashBinding
import com.example.advisorapps.splashScreen.AdapterOnboardingitem
import com.example.advisorapps.splashScreen.OnboardingItem
import com.google.android.material.button.MaterialButton
import java.text.FieldPosition

class SplashActivity : AppCompatActivity() {
    private lateinit var AdapterOnboardingitem: AdapterOnboardingitem
    private lateinit var indikatorContainer: LinearLayout

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnboardingItem()
        setIndikator()
        setCurrentIndikator(0)
    }

    private fun setOnboardingItem(){
        AdapterOnboardingitem = AdapterOnboardingitem(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.my_car,
                    title = "Manage Youre Car",
                    description = "Organize all your to do's and project. The Car tag them to set priorities."
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.car_repair,
                    title = "Repair Youre Car",
                    description = "Organize all your to do's and project. The Car tag them to set priorities."
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.electric_car,
                    title = "Fell the power of Youre Car",
                    description = "Organize all your to do's and project. The Car tag them to set priorities."
                )
            )
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onBoarding)
        onboardingViewPager.adapter = AdapterOnboardingitem
        onboardingViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndikator(position)
            }
        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imgNext).setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < AdapterOnboardingitem.itemCount){
                onboardingViewPager.currentItem += 1
            }else {
                navToLogin()
            }
        }
        findViewById<TextView>(R.id.tvSkip).setOnClickListener {
            navToLogin()
        }
        findViewById<MaterialButton>(R.id.btnGetStarted).setOnClickListener {
            navToRegister()
        }
    }

    private fun navToLogin(){
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }
    private fun navToRegister(){
        startActivity(Intent(applicationContext, RegistrasiActivity::class.java))
    }

    private fun setIndikator(){
        indikatorContainer = findViewById(R.id.indikatorContainer)
        val indikators = arrayOfNulls<ImageView>(AdapterOnboardingitem.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indikators.indices){
            indikators[i] = ImageView(applicationContext)
            indikators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indikator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indikatorContainer.addView(it)
            }
        }
    }
    private fun setCurrentIndikator(position: Int){
        val childCount = indikatorContainer.childCount
        for (i in 0 until childCount){
            val imageView = indikatorContainer.getChildAt(i) as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indikator_active_background
                    )
                )
            }else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indikator_inactive_background
                    )
                )
            }
        }
    }
}