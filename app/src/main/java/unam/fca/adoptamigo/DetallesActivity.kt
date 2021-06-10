package unam.fca.adoptamigo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetallesActivity() : AppCompatActivity() {
    private lateinit var ivImagen: ImageView
    private lateinit var tvNombre: TextView
    private lateinit var tvRaza: TextView
    private lateinit var tvEdad: TextView
    private lateinit var tvGenero: TextView
    private lateinit var tvUbicacion: TextView
    private lateinit var tvColor: TextView
    private lateinit var tvDescripcion: TextView



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


        ivImagen = findViewById(R.id.imgMascota)
        tvNombre = findViewById(R.id.txvNombre)
        tvRaza = findViewById(R.id.txvRaza)
        tvEdad = findViewById(R.id.txvEdad)
        tvGenero = findViewById(R.id.txvGenero)
        tvUbicacion = findViewById(R.id.txvUbicacion)
        tvColor = findViewById(R.id.txvColor)
        tvDescripcion = findViewById(R.id.txvDescripcion)

        ivImagen.setImageResource(imagen)
        tvNombre.text = nombre
        tvRaza.text = raza
        tvEdad.text = edad
        tvGenero.text = genero
        tvUbicacion.text = ubicacion
        tvColor.text = color
        tvDescripcion.text = descripcion

    }
}