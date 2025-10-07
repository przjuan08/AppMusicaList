package lgg.compan.appmusicalist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import lgg.compan.appmusicalist.R
import lgg.compan.appmusicalist.model.Cancion

class CancionAdapter(
    private var lista: List<Cancion>,
    private val onItemClick: (Cancion) -> Unit
) : RecyclerView.Adapter<CancionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.cardCancion)
        val titulo: TextView = itemView.findViewById(R.id.textTitulo)
        val artista: TextView = itemView.findViewById(R.id.textArtista)
        val album: TextView = itemView.findViewById(R.id.textAlbum)
        val duracion: TextView = itemView.findViewById(R.id.textDuracion)
        val genero: TextView = itemView.findViewById(R.id.textGenero)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cancion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cancion = lista[position]

        // Configurar los textos
        holder.titulo.text = cancion.titulo
        holder.artista.text = cancion.artista
        holder.album.text = cancion.album
        holder.duracion.text = cancion.duracion
        holder.genero.text = cancion.genero

        // Configurar colores diferentes según la posición
        val colorRes = when (position % 3) {
            0 -> R.color.card_color_1
            1 -> R.color.card_color_2
            else -> R.color.card_color_3
        }
        holder.card.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, colorRes))

        // Configurar click listener
        holder.card.setOnClickListener {
            onItemClick(cancion)
        }
    }

    override fun getItemCount() = lista.size

    fun actualizarLista(nuevaLista: List<Cancion>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}