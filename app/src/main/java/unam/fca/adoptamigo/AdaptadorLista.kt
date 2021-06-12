
package unam.fca.adoptamigo

import android.content.Context
import android.database.Cursor
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import unam.fca.adoptamigo.bd.Contrato


class AdaptadorLista(context: Context, cur: Cursor, flags: Int):
    CursorAdapter(context, cur, flags), View.OnClickListener {

    private val layout: LayoutInflater
    private var position: Int = 0
    private val micontext: Context

    init {
        layout = context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        micontext = context
    }

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return layout.inflate(R.layout.item_mascotas, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {

        val itemImagen = view?.findViewById<ImageView>(R.id.itemImagen)
        val itemNombre = view?.findViewById<TextView>(R.id.itemNombre)
        val itemRaza = view?.findViewById<TextView>(R.id.itemRaza)
        val itemEdad = view?.findViewById<TextView>(R.id.itemEdad)
        val itemGenero = view?.findViewById<TextView>(R.id.itemGenero)
        val itemUbicacion = view?.findViewById<TextView>(R.id.itemUbicacion)
        val itemColor = view?.findViewById<TextView>(R.id.itemColor)
        val itemDescripcion = view?.findViewById<TextView>(R.id.itemDescripcion)
        val itemNombreContacto = view?.findViewById<TextView>(R.id.itemNombreUsuario)
        val itemTelefono = view?.findViewById<TextView>(R.id.itemTelefono)
        val itemCorreo = view?.findViewById<TextView>(R.id.itemCorreo)

        val nombre = cursor?.getString(
            cursor.getColumnIndex(Contrato.Mascotas.COLUMNA_NOMBRE)
        )
        val raza = cursor?.getString(
            cursor.getColumnIndex(Contrato.Mascotas.COLUMNA_RAZA)
        )
        val edad = cursor?.getString(
            cursor.getColumnIndex(Contrato.Mascotas.COLUMNA_EDAD)
        )
        val genero = cursor?.getString(
            cursor.getColumnIndex(Contrato.Mascotas.COLUMNA_GENERO)
        )
        val ubicacion = cursor?.getString(
            cursor.getColumnIndex(Contrato.Mascotas.COLUMNA_UBICACION)
        )
        val color = cursor?.getString(
            cursor.getColumnIndex(Contrato.Mascotas.COLUMNA_COLOR)
        )
        val descripcion = cursor?.getString(
            cursor.getColumnIndex(Contrato.Mascotas.COLUMNA_DESCRIPCION)
        )
        val nombreContacto = cursor?.getString(
            cursor.getColumnIndex(Contrato.Mascotas.COLUMNA_NOMBRECONTACTO)
        )
        val telefono = cursor?.getString(
            cursor.getColumnIndex(Contrato.Mascotas.COLUMNA_TELEFONOCONTACTO)
        )
        val correo = cursor?.getString(
            cursor.getColumnIndex(Contrato.Mascotas.COLUMNA_CORREOCONTACTO)
        )

        val imagen: ByteArray? = cursor?.getBlob(
            cursor.getColumnIndex(Contrato.Mascotas.COLUMNA_IMAGEN))

        itemNombre?.text = nombre
        itemRaza?.text = raza
        itemEdad?.text = edad
        itemGenero?.text = genero
        itemUbicacion?.text = ubicacion
        itemColor?.text = color
        itemDescripcion?.text = descripcion
        itemNombreContacto?.text = nombreContacto
        itemTelefono?.text = telefono
        itemCorreo?.text = correo

        if (imagen != null ) {
            val bitmap = BitmapFactory.decodeByteArray(imagen, 0, imagen.size)
            itemImagen?.setImageBitmap(bitmap)
        }

        position = cursor!!.position
        view!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(micontext, "Los amigos de AdoptAmigo estamos en proceso de validar tu adopción",
            Toast.LENGTH_SHORT).show()
    }
}