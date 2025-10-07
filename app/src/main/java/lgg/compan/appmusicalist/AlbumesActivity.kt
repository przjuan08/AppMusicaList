package lgg.compan.appmusicalist

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lgg.compan.appmusicalist.adapter.AlbumAdapter
import lgg.compan.appmusicalist.db.DBHelper
import lgg.compan.appmusicalist.model.Album

class AlbumesActivity : AppCompatActivity() {

    private lateinit var adapter: AlbumAdapter
    private lateinit var listaOriginal: List<Album>
    private lateinit var spinnerGenero: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albumes)

        // Configurar toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Inicializar base de datos y obtener datos
        val dbHelper = DBHelper(this)

        // Obtener todos los álbumes usando el nuevo método
        listaOriginal = dbHelper.obtenerTodosLosAlbumes()

        // Configurar RecyclerView
        adapter = AlbumAdapter(listaOriginal) { album ->
            val intent = Intent(this, DetalleAlbumActivity::class.java).apply {
                putExtra("ALBUM_ID", album.id)
                putExtra("ALBUM_TITULO", album.titulo)
                putExtra("ALBUM_ARTISTA", album.artista)
                putExtra("ALBUM_AÑO", album.año)
                putExtra("ALBUM_GENERO", album.genero)
                putExtra("ALBUM_PORTADA", album.portada)
                putExtra("ARTISTA_ID", album.idArtista)
            }
            startActivity(intent)
        }

        findViewById<RecyclerView>(R.id.recyclerAlbumes).apply {
            layoutManager = LinearLayoutManager(this@AlbumesActivity)
            adapter = this@AlbumesActivity.adapter
        }

        // Configurar Spinner de géneros
        spinnerGenero = findViewById(R.id.spinnerGeneroAlbum)
        configurarSpinnerGeneros(dbHelper)

        // Configurar SearchView
        configurarSearchView()
    }

    private fun configurarSpinnerGeneros(dbHelper: DBHelper) {
        val generos = dbHelper.obtenerGenerosAlbumes()
        val opcionesGeneros = mutableListOf<String>().apply {
            add(getString(R.string.todos_los_generos))
            addAll(generos)
        }

        // Usando los layouts personalizados
        val adapterSpinner = ArrayAdapter(
            this,
            R.layout.spinner_item,  // Layout personalizado para item normal
            opcionesGeneros
        )

        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item) // Layout personalizado para dropdown

        spinnerGenero.adapter = adapterSpinner

        spinnerGenero.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                filtrarYBuscar()
            }
            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {}
        }
    }

    private fun configurarSearchView() {
        val searchView = findViewById<android.widget.SearchView>(R.id.searchViewAlbum)
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarYBuscar()
                return true
            }
        })
    }

    private fun filtrarYBuscar() {
        val query = findViewById<android.widget.SearchView>(R.id.searchViewAlbum).query.toString()
        val generoSeleccionado = spinnerGenero.selectedItem.toString()
        val esTodosLosGeneros = generoSeleccionado == getString(R.string.todos_los_generos)

        val filtrado = listaOriginal.filter { album ->
            val coincideTitulo = album.titulo.contains(query, ignoreCase = true)
            val coincideArtista = album.artista.contains(query, ignoreCase = true)
            val coincideGenero = esTodosLosGeneros || album.genero == generoSeleccionado

            (coincideTitulo || coincideArtista) && coincideGenero
        }
        adapter.actualizarLista(filtrado)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}