package com.android.uniLocal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.uniLocal.bd.Usuarios
import com.android.uniLocal.databinding.ActivityRegistroBinding
import com.android.uniLocal.modelo.Usuario

class RegistroActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistro.setOnClickListener { registrar() }

    }

    fun registrar() {

        val nombre = binding.nombreUsuario.text
        val nickname = binding.nicknameUsuario.text
        val correo = binding.emailUsuario.text
        val password = binding.passwordUsuario.text

        if (nombre.isEmpty()) {
            binding.nombreLayout.error = "Debes llenar este campo!"
        } else {
            binding.nombreLayout.error = null
        }

        if (nickname.isEmpty()) {
            binding.nicknameLayout.error = "Debes llenar este campo!"
        } else {
            binding.nicknameLayout.error = null
        }

        if (correo.isEmpty()) {
            binding.emailLayout.error = "Debes llenar este campo!"
        } else {
            binding.emailLayout.error = null
        }

        if (password.isEmpty()) {
            binding.passwordLayout.error = "Debes llenar este campo!"
        } else {
            binding.passwordLayout.error = null
        }



        if (correo.isNotEmpty() && password.isNotEmpty() && nombre.isNotEmpty() && nickname.isNotEmpty()) {
            val user = Usuario(
                1,
                nombre.toString(),
                nickname.toString(),
                correo.toString(),
                password.toString()
            )
            Usuarios.agregar(user)
            Toast.makeText(this, "Se ha registrador correctamente", Toast.LENGTH_LONG).show()
        }

    }
}