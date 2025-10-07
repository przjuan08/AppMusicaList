package lgg.compan.appmusicalist

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lgg.compan.appmusicalist.adapter.AlbumAdapter
import lgg.compan.appmusicalist.db.DBHelper
import lgg.compan.appmusicalist.model.Album

class DetalleArtistaActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var textNombre: TextView
    private lateinit var textGenero: TextView
    private lateinit var textPais: TextView
    private lateinit var textDescripcion: TextView
    private lateinit var recyclerAlbumes: RecyclerView
    private lateinit var adapterAlbumes: AlbumAdapter

    private var artistaId: Int = -1
    private var artistaNombre: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_artista)

        inicializarViews()
        configurarToolbar()
        obtenerDatosIntent()
        mostrarDatosArtista()
        configurarAlbumesArtista()
    }

    private fun inicializarViews() {
        toolbar = findViewById(R.id.toolbar)
        textNombre = findViewById(R.id.textNombre)
        textGenero = findViewById(R.id.textGenero)
        textPais = findViewById(R.id.textPais)
        textDescripcion = findViewById(R.id.textDescripcion)
        recyclerAlbumes = findViewById(R.id.recyclerAlbumesArtista)
    }

    private fun configurarToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""
    }

    private fun obtenerDatosIntent() {
        artistaId = intent.getIntExtra("ARTISTA_ID", -1)
        artistaNombre = intent.getStringExtra("ARTISTA_NOMBRE") ?: "Nombre no disponible"
    }

    private fun mostrarDatosArtista() {
        val pais = intent.getStringExtra("ARTISTA_PAIS") ?: "País no disponible"
        val genero = intent.getStringExtra("ARTISTA_GENERO") ?: "Género no disponible"
        val descripcion = intent.getStringExtra("ARTISTA_DESCRIPCION") ?: "Descripción no disponible"

        textNombre.text = artistaNombre
        textGenero.text = "${getString(R.string.genero)}: $genero"
        textPais.text = "${getString(R.string.pais)}: $pais"
        textDescripcion.text = descripcion

        supportActionBar?.title = artistaNombre
    }

    private fun configurarAlbumesArtista() {
        val dbHelper = DBHelper(this)

        val albumes = dbHelper.obtenerAlbumesPorArtista(artistaId)

        adapterAlbumes = AlbumAdapter(albumes) { album ->
            // Al hacer clic en un álbum, ir al detalle del álbum
            val intent = Intent(this, DetalleAlbumActivity::class.java).apply {
                putExtra("ALBUM_ID", album.id)
                putExtra("ALBUM_TITULO", album.titulo)
                putExtra("ALBUM_ARTISTA", album.artista)
                putExtra("ALBUM_AÑO", album.año)
                putExtra("ALBUM_GENERO", album.genero)
                putExtra("ALBUM_PORTADA", album.portada)
            }
            startActivity(intent)
        }

        recyclerAlbumes.apply {
            layoutManager = LinearLayoutManager(this@DetalleArtistaActivity)
            adapter = adapterAlbumes
        }

        // Mostrar mensaje si no hay álbumes
        if (albumes.isEmpty()) {
            findViewById<TextView>(R.id.textNoAlbumes).apply {
                text = "$artistaNombre no tiene álbumes registrados"
                visibility = TextView.VISIBLE
            }
            recyclerAlbumes.visibility = RecyclerView.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}