package unam.fca.adoptamigo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class DetallesActivity() : AppCompatActivity() {
    private lateinit var ivImagen: ImageView
    private lateinit var tvNombre: TextView
    private lateinit var tvRaza: TextView
    private lateinit var tvEdad: TextView
    private lateinit var tvGenero: TextView
    private lateinit var tvUbicacion: TextView
    private lateinit var tvColor: TextView
    private lateinit var tvDescripcion: TextView
    private lateinit var btnAdoptar: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val args = Bundle()
        val imagen: Int = args.getInt("imagen",intent.getIntExtra("imagen",0))
        val nombre: String = args.getString("nombre", intent.getStringExtra("nombre"))
        val raza: String = args.getString("raza", intent.getStringExtra("raza"))
        val edad: String = args.getString("edad", intent.getStringExtra("edad"))
        val genero: String = args.getString("genero", intent.getStringExtra("genero"))
        val ubicacion: String = args.getString("ubicacion", intent.getStringExtra("ubicacion"))
        val color: String = args.getString("color", intent.getStringExtra("color"))
        val descripcion: String = args.getString("descripcion", intent.getStringExtra("descripcion"))
        val nombreC : String = args.getString("nombreContacto", intent.getStringExtra("nombreContacto"))
        val telefonoC : String = args.getString("telefonoContacto", intent.getStringExtra("telefonoContacto"))
        val correoC : String = args.getString("correoContacto", intent.getStringExtra("correoContacto"))

        ivImagen = findViewById(R.id.imgMascota)
        tvNombre = findViewById(R.id.txvNombre)
        tvRaza = findViewById(R.id.txvRaza)
        tvEdad = findViewById(R.id.txvEdad)
        tvGenero = findViewById(R.id.txvGenero)
        tvUbicacion = findViewById(R.id.txvUbicacion)
        tvColor = findViewById(R.id.txvColor)
        tvDescripcion = findViewById(R.id.txvDescripcion)
        btnAdoptar = findViewById(R.id.btnAdoptar)

        ivImagen.setImageResource(imagen)
        tvNombre.text = nombre
        tvRaza.text = raza
        tvEdad.text = edad
        tvGenero.text = genero
        tvUbicacion.text = ubicacion
        tvColor.text = color
        tvDescripcion.text = descripcion


        btnAdoptar.setOnClickListener {
            showDialog("Nombre: $nombreC", "Telefono: $telefonoC", "Correo: $correoC")
        }
    }

    private fun showDialog(nombreC: String, telefonoC: String, correoC: String) {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialogo)
        val usuario = dialog.findViewById(R.id.nombreDialog) as TextView
        usuario.text = nombreC
        val telefono = dialog.findViewById(R.id.telefonoDialog) as TextView
        telefono.text = telefonoC
        val correo = dialog.findViewById(R.id.correoDialog) as TextView
        correo.text = correoC
        val contactar = dialog.findViewById(R.id.Adoptar) as Button
        contactar.setOnClickListener {
            Toast.makeText(this,"En proceso de adopcion",Toast.LENGTH_LONG).show()
            dialog.dismiss()

        }

        dialog.show()

    }
}