package lgg.compan.appmusicalist

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lgg.compan.appmusicalist.adapter.ArtistaAdapter
import lgg.compan.appmusicalist.db.DBHelper
import lgg.compan.appmusicalist.model.Artista

class ArtistasActivity : AppCompatActivity() {

    private lateinit var adapter: ArtistaAdapter
    private lateinit var listaOriginal: List<Artista>
    private lateinit var spinnerGenero: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artistas)

        // Configurar toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Inicializar base de datos y obtener datos
        val dbHelper = DBHelper(this)
        val db = dbHelper.readableDatabase

        // Consulta para obtener artistas
        val cursor = db.rawQuery("""
        SELECT a.id_artista, a.nombre, a.pais, a.genero, a.descripcion 
        FROM artistas a
        ORDER BY a.nombre
    """.trimIndent(), null)

        val lista = mutableListOf<Artista>()
        while (cursor.moveToNext()) {
            lista.add(
                Artista(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )
            )
        }
        cursor.close()
        listaOriginal = lista

        // Configurar RecyclerView
        adapter = ArtistaAdapter(listaOriginal) { artista ->
            val intent = Intent(this, DetalleArtistaActivity::class.java).apply {
                putExtra("ARTISTA_ID", artista.id)
                putExtra("ARTISTA_NOMBRE", artista.nombre)
                putExtra("ARTISTA_PAIS", artista.pais)
                putExtra("ARTISTA_GENERO", artista.genero)
                putExtra("ARTISTA_DESCRIPCION", artista.descripcion)
            }
            startActivity(intent)
        }

        findViewById<RecyclerView>(R.id.recyclerArtistas).apply {
            layoutManager = LinearLayoutManager(this@ArtistasActivity)
            adapter = this@ArtistasActivity.adapter
        }

        // Configurar Spinner de géneros
        spinnerGenero = findViewById(R.id.spinnerGeneroArtista)
        configurarSpinnerGeneros(dbHelper)

        // Configurar SearchView
        configurarSearchView()
    }

    private fun configurarSpinnerGeneros(dbHelper: DBHelper) {
        val generos = dbHelper.obtenerGenerosArtistas()
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
        val searchView = findViewById<android.widget.SearchView>(R.id.searchViewArtista)
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarYBuscar()
                return true
            }
        })
    }

    private fun filtrarYBuscar() {
        val query = findViewById<android.widget.SearchView>(R.id.searchViewArtista).query.toString()
        val generoSeleccionado = spinnerGenero.selectedItem.toString()
        val esTodosLosGeneros = generoSeleccionado == getString(R.string.todos_los_generos)

        val filtrado = listaOriginal.filter { artista ->
            val coincideNombre = artista.nombre.contains(query, ignoreCase = true)
            val coincideGenero = esTodosLosGeneros || artista.genero == generoSeleccionado
            coincideNombre && coincideGenero
        }
        adapter.actualizarLista(filtrado)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}