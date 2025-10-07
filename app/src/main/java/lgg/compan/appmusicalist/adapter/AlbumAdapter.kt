package lgg.compan.appmusicalist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import lgg.compan.appmusicalist.R
import lgg.compan.appmusicalist.model.Album

class AlbumAdapter(
    private var lista: List<Album>,
    private val onItemClick: (Album) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.cardAlbum)
        val titulo: TextView = itemView.findViewById(R.id.textTitulo)
        val artista: TextView = itemView.findViewById(R.id.textArtista)
        val año: TextView = itemView.findViewById(R.id.textAño)
        val genero: TextView = itemView.findViewById(R.id.textGenero)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = lista[position]

        // Configurar los textos
        holder.titulo.text = album.titulo
        holder.artista.text = album.artista
        holder.año.text = album.año.toString()
        holder.genero.text = album.genero

        // Configurar colores diferentes según la posición
        val colorRes = when (position % 3) {
            0 -> R.color.card_color_1
            1 -> R.color.card_color_2
            else -> R.color.card_color_3
        }
        holder.card.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, colorRes))

        // Configurar click listener
        holder.card.setOnClickListener {
            onItemClick(album)
        }
    }

    override fun getItemCount() = lista.size

    fun actualizarLista(nuevaLista: List<Album>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}