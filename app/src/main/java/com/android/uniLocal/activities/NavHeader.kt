package com.android.uniLocal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.uniLocal.R
import com.android.uniLocal.bd.Usuarios
import com.android.uniLocal.databinding.ActivityNavHeaderBinding
import com.android.uniLocal.modelo.Usuario

class NavHeader : AppCompatActivity() {

    lateinit var binding: ActivityNavHeaderBinding
    private var codigoUsuario:Int = 0
    private var usuario:Usuario?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val sp=getSharedPreferences("sesion",Context.MODE_PRIVATE)
        binding= ActivityNavHeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        codigoUsuario=intent.extras!!.getInt("codigo_usuario")
        usuario=Usuarios.obtener(codigoUsuario)
        Log.e("cargarUsuarioNavHeader",codigoUsuario.toString())
        cargarUsuario()
//        val recibir : Intent = intent
////        var nombre = recibir.getStringExtra("nombre")
//        binding.txtNombreMenu.text = sp.getString("nombre_usuario","jmmm")

           }
    fun cargarUsuario(){

        if (usuario!=null){
            binding.txtNombreMenu.text=usuario!!.nombre
            binding.txtCorreoUsuario.text=usuario!!.correo
            Log.e("cargarUsuarioNavHeader",usuario!!.nombre.toString())

        }
    }
}