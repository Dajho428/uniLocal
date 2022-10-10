package com.android.uniLocal.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.android.uniLocal.bd.Usuarios
import com.android.uniLocal.databinding.ActivityUserBinding
import com.android.uniLocal.fragmentos.*
import com.android.uniLocal.modelo.Usuario

class UserActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserBinding
    private var MENU_INICIO = "inicio"
    private var MENU_MIS_LUGARES = "mis_lugares"
    private var codigoUsuario: Int = 0
    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            codigoUsuario = intent.extras!!.getInt("codigo_usuario")
            usuario = Usuarios.obtener(codigoUsuario)
            Log.e("UserActivity", codigoUsuario.toString())
        }catch (e :Exception){

        }

//        val recibirDatos : Intent = intent
//        var nombre = recibirDatos.getStringExtra("nombre")
        reemplazarFragmento(1, MENU_INICIO)
        binding.btnMenu.setOnClickListener { abrirMenu() }
        binding.btnCrearLugarUsuario.setOnClickListener { crearLugar() }
        binding.btnLogout.setOnClickListener { cerrarSesion() }
        binding.btnMisLugares.setOnClickListener { reemplazarFragmento(2, MENU_MIS_LUGARES) }

    }


    fun reemplazarFragmento(valor: Int, nombre: String) {

        var fragmento: Fragment = when (valor) {
            1 -> InicioFragment()
            2 -> MisLugaresFragment()
            else -> FavoritosFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.contenidoPrincipal.id, fragmento)
            .addToBackStack(nombre)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val count = supportFragmentManager.backStackEntryCount

        if (count > 0) {
            val nombre = supportFragmentManager.getBackStackEntryAt(count - 1).name
            when (nombre) {
                MENU_INICIO -> binding.navigationView.menu.getItem(0).isChecked = true
                MENU_MIS_LUGARES -> binding.navigationView.menu.getItem(1).isChecked = true
                else -> binding.navigationView.menu.getItem(2).isChecked = true
            }
        }

    }

    fun cerrarSesion() {
        val sh = getSharedPreferences("sesion", Context.MODE_PRIVATE).edit()
        sh.clear()
        sh.commit()
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }


    fun crearLugar() {
        val intent = Intent(this, CrearLugarActivity::class.java)
        startActivity(intent)
    }

    fun abrirMenu() {

        binding.drawerLayout.openDrawer(GravityCompat.START)
    }


}