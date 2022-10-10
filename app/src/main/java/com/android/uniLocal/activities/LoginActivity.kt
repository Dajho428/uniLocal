package com.android.uniLocal.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.uniLocal.R
import com.android.uniLocal.bd.Personas
import com.android.uniLocal.databinding.ActivityLoginBinding
import com.android.uniLocal.modelo.Administrador
import com.android.uniLocal.modelo.Moderador
import com.android.uniLocal.modelo.Usuario
import com.google.android.material.tabs.TabLayoutMediator

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sp = getSharedPreferences("sesion", Context.MODE_PRIVATE)

        val correo = sp.getString("correo_usuario", "")
        val tipo = sp.getString("tipo_usuario", "")

        if (correo!!.isNotEmpty() && tipo!!.isNotEmpty()) {

            when (tipo) {
                "usuario" -> startActivity(Intent(this, UserActivity::class.java))
                "moderador" -> startActivity(Intent(this, ModeradorActivity::class.java))
                "admin" -> startActivity(Intent(this, AdministradorActivity::class.java))
            }

            finish()

        } else {

            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)


            binding.btnLogin.setOnClickListener { login() }
            binding.btnRegistro.setOnClickListener { irPantallaRegistro() }


        }

    }

    fun irPantallaRegistro() {
        startActivity(Intent(this, RegistroActivity::class.java))

    }

    fun login() {

        val correo = binding.emailUsuario.text
        var password = binding.passwordUsuario.text

        if (correo.isEmpty()) {
            binding.emailLayout.isErrorEnabled = true
            binding.emailLayout.error = getString(R.string.es_obligatorio)
        } else {
            binding.emailLayout.error = null
        }

        if (password.isEmpty()) {
            binding.passwordLayout.error = getString(R.string.es_obligatorio)
        } else {
            binding.passwordLayout.error = null
        }

        if (correo.isNotEmpty() && password.isNotEmpty()) {

            try {
                val persona = Personas.login(correo.toString(), password.toString())

                if (persona != null) {

                    val tipo =
                        if (persona is Usuario) "usuario" else if (persona is Moderador) "moderador" else "admin"
//                    val datos= Intent(this,UserActivity::class.java)
//                    datos.putExtra("nombre",persona.nombre)
//                    datos.putExtra("correo",persona.correo)
                    val sharedPreferences =
                        this.getSharedPreferences("sesion", Context.MODE_PRIVATE).edit()
                    sharedPreferences.putInt("codigo_usuario",persona.id)
                    sharedPreferences.putString("correo_usuario", persona.correo)
                    sharedPreferences.putString("tipo_usuario", tipo)
                    sharedPreferences.putString("nombre_usuario", persona.nombre)
                    sharedPreferences.commit()
                    when (persona) {

                        is Usuario -> startActivity(Intent(this, UserActivity::class.java))
                        //is Usuario -> startActivity(datos)
                        is Moderador -> startActivity(Intent(this, ModeradorActivity::class.java))
                        is Administrador -> startActivity(
                            Intent(
                                this,
                                AdministradorActivity::class.java
                            )
                        )
                    }
                } else {
                    Toast.makeText(this, getString(R.string.datos_incorrectos), Toast.LENGTH_LONG)
                        .show()
                }

            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.datos_incorrectos), Toast.LENGTH_LONG)
                    .show()
            }

        }

    }


}