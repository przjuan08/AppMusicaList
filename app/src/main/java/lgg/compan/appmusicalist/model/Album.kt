package lgg.compan.appmusicalist.model

data class Album(
    val id: Int,
    val titulo: String,
    val idArtista: Int,
    val artista: String, // Para mostrar el nombre del artista
    val año: Int,
    val genero: String,
    val portada: String?
)