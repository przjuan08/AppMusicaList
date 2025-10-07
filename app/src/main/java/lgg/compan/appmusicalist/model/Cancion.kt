package lgg.compan.appmusicalist.model

data class Cancion(
    val id: Int,
    val titulo: String,
    val idAlbum: Int,
    val album: String, // Para mostrar el nombre del álbum
    val idArtista: Int,
    val artista: String, // Para mostrar el nombre del artista
    val duracion: String,
    val genero: String
)