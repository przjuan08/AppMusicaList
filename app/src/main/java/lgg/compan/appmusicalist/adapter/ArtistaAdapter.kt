package lgg.compan.appmusicalist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import lgg.compan.appmusicalist.R
import lgg.compan.appmusicalist.model.Artista

class ArtistaAdapter(
    private var lista: List<Artista>,
    private val onItemClick: (Artista) -> Unit
) : RecyclerView.Adapter<ArtistaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.cardArtista)
        val nombre: TextView = itemView.findViewById(R.id.textNombre)
        val genero: TextView = itemView.findViewById(R.id.textGenero)
        val pais: TextView = itemView.findViewById(R.id.textPais)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artista, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artista = lista[position]

        holder.nombre.text = artista.nombre
        holder.genero.text = artista.genero
        holder.pais.text = artista.pais

        // Configurar colores diferentes según la posición para mejor visualización
        val colorRes = when (position % 3) {
            0 -> R.color.card_color_1
            1 -> R.color.card_color_2
            else -> R.color.card_color_3
        }
        holder.card.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, colorRes))

        // Configurar click listener
        holder.card.setOnClickListener {
            onItemClick(artista)
        }
    }

    override fun getItemCount() = lista.size

    fun actualizarLista(nuevaLista: List<Artista>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}