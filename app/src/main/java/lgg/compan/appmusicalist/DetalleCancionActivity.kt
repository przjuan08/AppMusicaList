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

class DetalleCancionActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var textTitulo: TextView
    private lateinit var textArtista: TextView
    private lateinit var textAlbum: TextView
    private lateinit var textDuracion: TextView
    private lateinit var textGenero: TextView
    private lateinit var recyclerOtrasCanciones: RecyclerView
    private lateinit var adapterOtrasCanciones: CancionAdapter

    private var cancionId: Int = -1
    private var artistaId: Int = -1
    private var artistaNombre: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_cancion)

        inicializarViews()
        configurarToolbar()
        obtenerDatosIntent()
        mostrarDatosCancion()
        configurarOtrasCanciones()
    }

    private fun inicializarViews() {
        toolbar = findViewById(R.id.toolbar)
        textTitulo = findViewById(R.id.textTitulo)
        textArtista = findViewById(R.id.textArtista)
        textAlbum = findViewById(R.id.textAlbum)
        textDuracion = findViewById(R.id.textDuracion)
        textGenero = findViewById(R.id.textGenero)
        recyclerOtrasCanciones = findViewById(R.id.recyclerOtrasCanciones)
    }

    private fun configurarToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""
    }

    private fun obtenerDatosIntent() {
        cancionId = intent.getIntExtra("CANCION_ID", -1)
        artistaId = intent.getIntExtra("ARTISTA_ID", -1)
        artistaNombre = intent.getStringExtra("CANCION_ARTISTA") ?: "Artista Desconocido"
    }

    private fun mostrarDatosCancion() {
        val titulo = intent.getStringExtra("CANCION_TITULO") ?: "Título no disponible"
        val artista = intent.getStringExtra("CANCION_ARTISTA") ?: "Artista no disponible"
        val album = intent.getStringExtra("CANCION_ALBUM") ?: "Álbum no disponible"
        val duracion = intent.getStringExtra("CANCION_DURACION") ?: "Duración no disponible"
        val genero = intent.getStringExtra("CANCION_GENERO") ?: "Género no disponible"

        textTitulo.text = titulo
        textArtista.text = "${getString(R.string.artista)}: $artista"
        textAlbum.text = "${getString(R.string.album)}: $album"
        textDuracion.text = "${getString(R.string.duracion)}: $duracion"
        textGenero.text = "${getString(R.string.genero)}: $genero"

        supportActionBar?.title = titulo
    }

    private fun configurarOtrasCanciones() {
        val dbHelper = DBHelper(this)

        val otrasCanciones = dbHelper.obtenerCancionesMismoArtista(artistaId, cancionId)

        adapterOtrasCanciones = CancionAdapter(otrasCanciones) { cancion ->
            // Al hacer clic en otra canción, ir al detalle de esa canción
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

        recyclerOtrasCanciones.apply {
            layoutManager = LinearLayoutManager(this@DetalleCancionActivity)
            adapter = adapterOtrasCanciones
        }

        // Mostrar mensaje si no hay otras canciones
        if (otrasCanciones.isEmpty()) {
            findViewById<TextView>(R.id.textNoOtrasCanciones).apply {
                text = "No hay más canciones de $artistaNombre"
                visibility = TextView.VISIBLE
            }
            recyclerOtrasCanciones.visibility = RecyclerView.GONE
        } else {
            findViewById<TextView>(R.id.textTituloOtrasCanciones).apply {
                text = "Más canciones de $artistaNombre"
                visibility = TextView.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}