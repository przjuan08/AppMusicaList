package lgg.compan.appmusicalist.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import lgg.compan.appmusicalist.model.Album
import lgg.compan.appmusicalist.model.Cancion
import java.io.FileOutputStream
import java.io.IOException

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "catalogo_musica.db"
        private const val DB_VERSION = 1

        fun copiarBaseSiempre(context: Context) {
            val dbPath = context.getDatabasePath(DB_NAME)

            // Crear directorio si no existe
            dbPath.parentFile?.mkdirs()

            // Eliminar base de datos existente para forzar recopia
            if (dbPath.exists()) {
                dbPath.delete()
            }

            try {
                context.assets.open(DB_NAME).use { input ->
                    FileOutputStream(dbPath).use { output ->
                        input.copyTo(output)
                    }
                }

                // Verificar que se copió correctamente
                if (dbPath.exists()) {
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        // Verificar contenido de la base de datos
        fun verificarArtistas(context: Context): Int {
            val dbHelper = DBHelper(context)
            val db = dbHelper.readableDatabase
            var count = 0

            try {
                val cursor = db.rawQuery("SELECT COUNT(*) FROM artistas", null)
                if (cursor.moveToFirst()) {
                    count = cursor.getInt(0)
                }
                cursor.close()
            } catch (e: Exception) {
            }
            return count
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // No crear tablas aquí porque ya existen en el archivo de la base de datos
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Manejar actualizaciones futuras de la base de datos
    }

    // Metodo para obtener todos los géneros únicos de artistas
    fun obtenerGenerosArtistas(): List<String> {
        val generos = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT DISTINCT genero FROM artistas WHERE genero IS NOT NULL ORDER BY genero", null)

        while (cursor.moveToNext()) {
            cursor.getString(0)?.let { generos.add(it) }
        }
        cursor.close()
        return generos
    }

    // Metodo para obtener todos los géneros únicos de álbumes
    fun obtenerGenerosAlbumes(): List<String> {
        val generos = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT DISTINCT genero FROM albumes WHERE genero IS NOT NULL ORDER BY genero", null)

        while (cursor.moveToNext()) {
            cursor.getString(0)?.let { generos.add(it) }
        }
        cursor.close()
        return generos
    }

    // Metodo para obtener todos los géneros únicos de canciones
    fun obtenerGenerosCanciones(): List<String> {
        val generos = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT DISTINCT genero FROM canciones WHERE genero IS NOT NULL ORDER BY genero", null)

        while (cursor.moveToNext()) {
            cursor.getString(0)?.let { generos.add(it) }
        }
        cursor.close()
        return generos
    }

    // Metodo para obtener todos los álbumes
    fun obtenerTodosLosAlbumes(): List<lgg.compan.appmusicalist.model.Album> {
        val albumes = mutableListOf<lgg.compan.appmusicalist.model.Album>()
        val db = readableDatabase

        // Consulta con JOIN para obtener el nombre del artista
        val cursor = db.rawQuery("""
        SELECT a.id_album, a.titulo, a.id_artista, ar.nombre, a.año, a.genero, a.portada
        FROM albumes a
        LEFT JOIN artistas ar ON a.id_artista = ar.id_artista
        ORDER BY a.titulo
    """.trimIndent(), null)

        while (cursor.moveToNext()) {
            albumes.add(
                lgg.compan.appmusicalist.model.Album(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3) ?: "Artista Desconocido",
                    cursor.getInt(4),
                    cursor.getString(5) ?: "Sin Género",
                    cursor.getString(6)
                )
            )
        }
        cursor.close()
        return albumes
    }

    // Metodo para obtener todas las canciones
    fun obtenerTodasLasCanciones(): List<lgg.compan.appmusicalist.model.Cancion> {
        val canciones = mutableListOf<lgg.compan.appmusicalist.model.Cancion>()
        val db = readableDatabase

        // Consulta con JOIN para obtener nombres de álbum y artista
        val cursor = db.rawQuery("""
        SELECT c.id_cancion, c.titulo, c.id_album, al.titulo, c.id_artista, ar.nombre, c.duracion, c.genero
        FROM canciones c
        LEFT JOIN albumes al ON c.id_album = al.id_album
        LEFT JOIN artistas ar ON c.id_artista = ar.id_artista
        ORDER BY c.titulo
    """.trimIndent(), null)

        while (cursor.moveToNext()) {
            canciones.add(
                lgg.compan.appmusicalist.model.Cancion(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3) ?: "Álbum Desconocido",
                    cursor.getInt(4),
                    cursor.getString(5) ?: "Artista Desconocido",
                    cursor.getString(6) ?: "00:00",
                    cursor.getString(7) ?: "Sin Género"
                )
            )
        }
        cursor.close()
        return canciones
    }




    // Método para obtener álbumes de un artista específico (máximo 3)
    fun obtenerAlbumesPorArtista(idArtista: Int): List<Album> {
        val albumes = mutableListOf<Album>()
        val db = readableDatabase

        val cursor = db.rawQuery("""
        SELECT a.id_album, a.titulo, a.id_artista, ar.nombre, a.año, a.genero, a.portada
        FROM albumes a
        LEFT JOIN artistas ar ON a.id_artista = ar.id_artista
        WHERE a.id_artista = ?
        ORDER BY a.año DESC
        LIMIT 3
    """.trimIndent(), arrayOf(idArtista.toString()))

        while (cursor.moveToNext()) {
            albumes.add(
                Album(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3) ?: "Artista Desconocido",
                    cursor.getInt(4),
                    cursor.getString(5) ?: "Sin Género",
                    cursor.getString(6)
                )
            )
        }
        cursor.close()
        return albumes
    }

    // Método para obtener canciones de un álbum específico
    fun obtenerCancionesPorAlbum(idAlbum: Int): List<Cancion> {
        val canciones = mutableListOf<Cancion>()
        val db = readableDatabase

        val cursor = db.rawQuery("""
        SELECT c.id_cancion, c.titulo, c.id_album, al.titulo, c.id_artista, ar.nombre, c.duracion, c.genero
        FROM canciones c
        LEFT JOIN albumes al ON c.id_album = al.id_album
        LEFT JOIN artistas ar ON c.id_artista = ar.id_artista
        WHERE c.id_album = ?
        ORDER BY c.id_cancion
    """.trimIndent(), arrayOf(idAlbum.toString()))

        while (cursor.moveToNext()) {
            canciones.add(
                Cancion(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3) ?: "Álbum Desconocido",
                    cursor.getInt(4),
                    cursor.getString(5) ?: "Artista Desconocido",
                    cursor.getString(6) ?: "00:00",
                    cursor.getString(7) ?: "Sin Género"
                )
            )
        }
        cursor.close()
        return canciones
    }

    // Método para obtener canciones del mismo artista (excluyendo la canción actual)
    fun obtenerCancionesMismoArtista(idArtista: Int, idCancionExcluir: Int): List<Cancion> {
        val canciones = mutableListOf<Cancion>()
        val db = readableDatabase

        val cursor = db.rawQuery("""
        SELECT c.id_cancion, c.titulo, c.id_album, al.titulo, c.id_artista, ar.nombre, c.duracion, c.genero
        FROM canciones c
        LEFT JOIN albumes al ON c.id_album = al.id_album
        LEFT JOIN artistas ar ON c.id_artista = ar.id_artista
        WHERE c.id_artista = ? AND c.id_cancion != ?
        ORDER BY c.titulo
        LIMIT 5
    """.trimIndent(), arrayOf(idArtista.toString(), idCancionExcluir.toString()))

        while (cursor.moveToNext()) {
            canciones.add(
                Cancion(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3) ?: "Álbum Desconocido",
                    cursor.getInt(4),
                    cursor.getString(5) ?: "Artista Desconocido",
                    cursor.getString(6) ?: "00:00",
                    cursor.getString(7) ?: "Sin Género"
                )
            )
        }
        cursor.close()
        return canciones
    }

    // Método para obtener información completa de un álbum por ID
    fun obtenerAlbumPorId(idAlbum: Int): Album? {
        val db = readableDatabase

        val cursor = db.rawQuery("""
        SELECT a.id_album, a.titulo, a.id_artista, ar.nombre, a.año, a.genero, a.portada
        FROM albumes a
        LEFT JOIN artistas ar ON a.id_artista = ar.id_artista
        WHERE a.id_album = ?
    """.trimIndent(), arrayOf(idAlbum.toString()))

        return if (cursor.moveToFirst()) {
            Album(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getString(3) ?: "Artista Desconocido",
                cursor.getInt(4),
                cursor.getString(5) ?: "Sin Género",
                cursor.getString(6)
            )
        } else {
            null
        }.also { cursor.close() }
    }

    // Método para obtener información completa de una canción por ID
    fun obtenerCancionPorId(idCancion: Int): Cancion? {
        val db = readableDatabase

        val cursor = db.rawQuery("""
        SELECT c.id_cancion, c.titulo, c.id_album, al.titulo, c.id_artista, ar.nombre, c.duracion, c.genero
        FROM canciones c
        LEFT JOIN albumes al ON c.id_album = al.id_album
        LEFT JOIN artistas ar ON c.id_artista = ar.id_artista
        WHERE c.id_cancion = ?
    """.trimIndent(), arrayOf(idCancion.toString()))

        return if (cursor.moveToFirst()) {
            Cancion(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getString(3) ?: "Álbum Desconocido",
                cursor.getInt(4),
                cursor.getString(5) ?: "Artista Desconocido",
                cursor.getString(6) ?: "00:00",
                cursor.getString(7) ?: "Sin Género"
            )
        } else {
            null
        }.also { cursor.close() }
    }

}




