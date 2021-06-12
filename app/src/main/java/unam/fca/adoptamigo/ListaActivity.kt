package unam.fca.adoptamigo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.TextView
import unam.fca.adoptamigo.bd.Contrato

class ListaActivity : AppCompatActivity() {

    private lateinit var listaMascostas: ListView
    private lateinit var titulo: TextView
    private lateinit var mAdapter: CursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        titulo = findViewById(R.id.txtTitulo)
        listaMascostas = findViewById(R.id.listaMascotas)
        cargaMascotas()
    }

    private fun cargaMascotas() {
        val mascotaUri = Contrato.Mascotas.MASCOTA_URI
        val projection = arrayOf(
            Contrato.Mascotas.COLUMNA_ID,
            Contrato.Mascotas.COLUMNA_NOMBRE,
            Contrato.Mascotas.COLUMNA_RAZA,
            Contrato.Mascotas.COLUMNA_EDAD,
            Contrato.Mascotas.COLUMNA_GENERO,
            Contrato.Mascotas.COLUMNA_UBICACION,
            Contrato.Mascotas.COLUMNA_COLOR,
            Contrato.Mascotas.COLUMNA_DESCRIPCION,
            Contrato.Mascotas.COLUMNA_NOMBRECONTACTO,
            Contrato.Mascotas.COLUMNA_TELEFONOCONTACTO,
            Contrato.Mascotas.COLUMNA_CORREOCONTACTO
        )
        val selection = ""
        val selectionArgs: Array<String>? = null
        val sortOrder: String? = null

        val cursor = contentResolver.query(mascotaUri, projection,
            selection, selectionArgs, sortOrder)
        if(cursor != null) {
            mAdapter = AdaptadorLista(this, cursor, 0)
            listaMascostas.adapter = mAdapter
        } else { // "No hay datos de contactos."
            //titulo.text = getString(R.string.sin_contactos)
        }
    }
}