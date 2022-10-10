package com.android.uniLocal.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.uniLocal.activities.DetalleLugarActivity
import com.android.uniLocal.bd.Categorias
import com.android.uniLocal.databinding.ActivityListaModeradorBinding
import com.android.uniLocal.modelo.Lugar
import com.android.uniLocal.R
import com.android.uniLocal.bd.Comentarios

class LugarAdapter(private var lista:ArrayList<Lugar>): RecyclerView.Adapter<LugarAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ActivityListaModeradorBinding.inflate( LayoutInflater.from(parent.context), parent, false )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind( lista[position] )
    }

    override fun getItemCount() = lista.size

    inner class ViewHolder(private var view:ActivityListaModeradorBinding):RecyclerView.ViewHolder(view.root), View.OnClickListener{

        private var codigoLugar:Int = 0

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(lugar: Lugar){
            view.nombreLugar.text = lugar.nombre
            view.direccionLugar.text = lugar.direccion

            val estado = lugar.estaAbierto()

            if(estado == "Abierto"){
                view.estadoLugar.setTextColor( ContextCompat.getColor(itemView.context, R.color.principal) )
                view.horarioLugar.text = "Cierra a las ${lugar.obtenerHoraCierre()}:00"
            }else{
                view.estadoLugar.setTextColor( ContextCompat.getColor(itemView.context, R.color.red ) )
                view.horarioLugar.visibility = View.GONE
            }

            val calificacion = lugar.obtenerCalificacionPromedio( Comentarios.listar(lugar.id) ).toString()

            view.calificacionLugar.text = calificacion
            view.estadoLugar.text = lugar.estaAbierto()
            view.iconoLugar.text = Categorias.obtener(lugar.idCategoria)!!.icono
            codigoLugar = lugar.id
        }

        override fun onClick(p0: View?) {
            val intent = Intent( view.root.context, DetalleLugarActivity::class.java )
            intent.putExtra("codigo", codigoLugar)
            view.root.context.startActivity(intent)
        }

    }
}