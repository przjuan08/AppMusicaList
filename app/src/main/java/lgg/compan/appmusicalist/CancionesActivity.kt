package lgg.compan.appmusicalist

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lgg.compan.appmusicalist.adapter.CancionAdapter
import lgg.compan.appmusicalist.db.DBHelper
import lgg.compan.appmusicalist.model.Cancion

class CancionesActivity : AppCompatActivity() {

    private lateinit var adapter: CancionAdapter
    private lateinit var listaOriginal: List<Cancion>
    private lateinit var spinnerGenero: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canciones)

        // Configurar toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Inicializar base de datos y obtener datos
        val dbHelper = DBHelper(this)

        // Obtener todas las canciones usando el nuevo método
        listaOriginal = dbHelper.obtenerTodasLasCanciones()

        // Configurar RecyclerView
        adapter = CancionAdapter(listaOriginal) { cancion ->
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

        findViewById<RecyclerView>(R.id.recyclerCanciones).apply {
            layoutManager = LinearLayoutManager(this@CancionesActivity)
            adapter = this@CancionesActivity.adapter
        }

        // Configurar Spinner de géneros
        spinnerGenero = findViewById(R.id.spinnerGeneroCancion)
        configurarSpinnerGeneros(dbHelper)

        // Configurar SearchView
        configurarSearchView()
    }

    private fun configurarSpinnerGeneros(dbHelper: DBHelper) {
        val generos = dbHelper.obtenerGenerosCanciones()
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
        val searchView = findViewById<android.widget.SearchView>(R.id.searchViewCancion)
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarYBuscar()
                return true
            }
        })
    }

    private fun filtrarYBuscar() {
        val query = findViewById<android.widget.SearchView>(R.id.searchViewCancion).query.toString()
        val generoSeleccionado = spinnerGenero.selectedItem.toString()
        val esTodosLosGeneros = generoSeleccionado == getString(R.string.todos_los_generos)

        val filtrado = listaOriginal.filter { cancion ->
            val coincideTitulo = cancion.titulo.contains(query, ignoreCase = true)
            val coincideArtista = cancion.artista.contains(query, ignoreCase = true)
            val coincideAlbum = cancion.album.contains(query, ignoreCase = true)
            val coincideGenero = esTodosLosGeneros || cancion.genero == generoSeleccionado

            (coincideTitulo || coincideArtista || coincideAlbum) && coincideGenero
        }
        adapter.actualizarLista(filtrado)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}