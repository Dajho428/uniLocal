package com.android.uniLocal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.uniLocal.R
import com.android.uniLocal.adapter.LugarAdapter
import com.android.uniLocal.bd.Lugares
import com.android.uniLocal.databinding.ActivityResultadoBusquedaBinding
import com.android.uniLocal.modelo.Lugar

class ResultadoBusqueda : AppCompatActivity() {

    lateinit var binding:ActivityResultadoBusquedaBinding
    var textoBusqueda:String = ""
    lateinit var listaLugares:ArrayList<Lugar>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBusquedaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textoBusqueda = intent.extras!!.getString("texto", "")
        listaLugares = ArrayList()

        if(textoBusqueda.isNotEmpty()){
            listaLugares = Lugares.buscarNombre(textoBusqueda)
        }

        val adapter = LugarAdapter(listaLugares)
        binding.listaLugares.adapter = adapter
        binding.listaLugares.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}