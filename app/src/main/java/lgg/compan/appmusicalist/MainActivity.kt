package lgg.compan.appmusicalist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import lgg.compan.appmusicalist.db.DBHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DBHelper.copiarBaseSiempre(this)

        val numeroArtistas = DBHelper.verificarArtistas(this)

        // Configurar los listeners de las cards
        val cardArtistas = findViewById<CardView>(R.id.cardArtistas)
        val cardAlbumes = findViewById<CardView>(R.id.cardAlbumes)
        val cardCanciones = findViewById<CardView>(R.id.cardCanciones)

        // Navegación a ArtistasActivity
        cardArtistas.setOnClickListener {
            val intent = Intent(this, ArtistasActivity::class.java)
            startActivity(intent)
        }

        // Navegación a AlbumesActivity
        cardAlbumes.setOnClickListener {
            val intent = Intent(this, AlbumesActivity::class.java)
            startActivity(intent)
        }

        // Navegación a CancionesActivity
        cardCanciones.setOnClickListener {
            val intent = Intent(this, CancionesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        println("📱 $mensaje")
    }
}