package lgg.compan.appmusicalist

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lgg.compan.appmusicalist.adapter.CancionAdapter
import lgg.compan.appmusicalist.db.DBHelper
import lgg.compan.appmusicalist.model.Cancion

class DetalleAlbumActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var textTitulo: TextView
    private lateinit var textArtista: TextView
    private lateinit var textAño: TextView
    private lateinit var textGenero: TextView
    private lateinit var textTotalCanciones: TextView
    private lateinit var recyclerCanciones: RecyclerView
    private lateinit var adapterCanciones: CancionAdapter

    private var albumId: Int = -1
    private var artistaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_album)

        inicializarViews()
        configurarToolbar()
        obtenerDatosIntent()
        mostrarDatosAlbum()
        configurarCancionesAlbum()
    }

    private fun inicializarViews() {
        toolbar = findViewById(R.id.toolbar)
        textTitulo = findViewById(R.id.textTitulo)
        textArtista = findViewById(R.id.textArtista)
        textAño = findViewById(R.id.textAño)
        textGenero = findViewById(R.id.textGenero)
        textTotalCanciones = findViewById(R.id.textTotalCanciones)
        recyclerCanciones = findViewById(R.id.recyclerCancionesAlbum)
    }

    private fun configurarToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""
    }

    private fun obtenerDatosIntent() {
        albumId = intent.getIntExtra("ALBUM_ID", -1)
        artistaId = intent.getIntExtra("ARTISTA_ID", -1)
    }

    private fun mostrarDatosAlbum() {
        val titulo = intent.getStringExtra("ALBUM_TITULO") ?: "Título no disponible"
        val artista = intent.getStringExtra("ALBUM_ARTISTA") ?: "Artista no disponible"
        val año = intent.getIntExtra("ALBUM_AÑO", 0)
        val genero = intent.getStringExtra("ALBUM_GENERO") ?: "Género no disponible"

        textTitulo.text = titulo
        textArtista.text = "${getString(R.string.artista)}: $artista"
        textAño.text = "${getString(R.string.año)}: $año"
        textGenero.text = "${getString(R.string.genero)}: $genero"

        supportActionBar?.title = titulo
    }

    private fun configurarCancionesAlbum() {
        val dbHelper = DBHelper(this)

        val canciones = dbHelper.obtenerCancionesPorAlbum(albumId)

        // Actualizar contador de canciones
        textTotalCanciones.text = "Canciones: ${canciones.size}"

        adapterCanciones = CancionAdapter(canciones) { cancion ->
            // Al hacer clic en una canción, ir al detalle de la canción
            val intent = Intent(this, DetalleCancionActivity::class.java).apply {
                putExtra("CANCION_ID", cancion.id)
                putExtra("CANCION_TITULO", cancion.titulo)
                putExtra("CANCION_ARTISTA", cancion.artista)
                putExtra("CANCION_ALBUM", cancion.album)
                putExtra("CANCION_DURACION", cancion.duracion)
                putExtra("CANCION_GENERO", cancion.genero)
                putExtra("ARTISTA_ID", cancion.idArtista)
            }
            startActivity(intent)
        }

        recyclerCanciones.apply {
            layoutManager = LinearLayoutManager(this@DetalleAlbumActivity)
            adapter = adapterCanciones
        }

        // Mostrar mensaje si no hay canciones
        if (canciones.isEmpty()) {
            findViewById<TextView>(R.id.textNoCanciones).apply {
                text = "Este álbum no tiene canciones registradas"
                visibility = TextView.VISIBLE
            }
            recyclerCanciones.visibility = RecyclerView.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}