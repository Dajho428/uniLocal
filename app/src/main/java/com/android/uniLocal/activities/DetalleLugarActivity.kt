package com.android.uniLocal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.uniLocal.R
import com.android.uniLocal.adapter.ViewPagerAdapter
import com.android.uniLocal.bd.Categorias
import com.android.uniLocal.bd.Lugares
import com.android.uniLocal.databinding.ActivityDetalleLugarBinding
import com.android.uniLocal.modelo.Lugar
import com.google.android.material.tabs.TabLayoutMediator

class DetalleLugarActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetalleLugarBinding
    private var codigoLugar:Int = 0
    private var lugar: Lugar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleLugarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        codigoLugar = intent.extras!!.getInt("codigo")
        lugar=Lugares.obtener(codigoLugar)
        binding.nombreLugarDetalle.text=lugar!!.nombre


        if(codigoLugar != 0){
            binding.viewPager.adapter = ViewPagerAdapter(this, codigoLugar)
            TabLayoutMediator(binding.tabs, binding.viewPager) { tab, pos ->
                when (pos) {
                    0 -> tab.text = getString(R.string.info_lugar)
                    1 -> tab.text = getString(R.string.comentarios)
                }
            }.attach()
        }
    }
}