package unam.fca.adoptamigo

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import unam.fca.adoptamigo.bd.Contrato
import java.net.URI

class FormularioActivity : AppCompatActivity() {
    val REQUEST_CODE = 1000
    private lateinit var imagen : ImageView
    private lateinit var boton: Button
    private lateinit var nombre: EditText
    private lateinit var raza: EditText
    private lateinit var edad: EditText
    private lateinit var genero: EditText
    private lateinit var ubicacion: EditText
    private lateinit var color: EditText
    private lateinit var descripcion: EditText
    private lateinit var nombreContacto: EditText
    private lateinit var correo: EditText
    private lateinit var telefono: EditText

    private lateinit var enviar: Button
    private lateinit var cancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        imagen = findViewById(R.id.inserta)
        boton = findViewById(R.id.botonFoto)
        enviar = findViewById(R.id.enviar)
        cancelar = findViewById(R.id.cancelar)
        nombre = findViewById(R.id.EdNombre)
        raza = findViewById(R.id.EdRaza)
        edad = findViewById(R.id.EdEdad)
        genero = findViewById(R.id.EdGenero)
        ubicacion = findViewById(R.id.EdtLugar)
        color = findViewById(R.id.EdColor)
        descripcion = findViewById(R.id.EdDescripcion)
        nombreContacto = findViewById(R.id.EdtNombreU)
        correo = findViewById(R.id.EdtCorreoU)
        telefono = findViewById(R.id.EdtTelefonoU)


        boton.setOnClickListener {
            cargarImagen()
        }

        enviar.setOnClickListener {
            if(TextUtils.isEmpty(nombre.text)) {
                mostrarMsg("El nombre de la mascota no puede estar vacio", false)
            }
            val values = ContentValues()
            values.put(Contrato.Mascotas.COLUMNA_NOMBRE, nombre.text.toString())
            values.put(Contrato.Mascotas.COLUMNA_RAZA, raza.text.toString())
            values.put(Contrato.Mascotas.COLUMNA_EDAD, edad.text.toString())
            values.put(Contrato.Mascotas.COLUMNA_GENERO, genero.text.toString())
            values.put(Contrato.Mascotas.COLUMNA_UBICACION, ubicacion.text.toString())
            values.put(Contrato.Mascotas.COLUMNA_COLOR, color.text.toString())
            values.put(Contrato.Mascotas.COLUMNA_DESCRIPCION, descripcion.text.toString())
            values.put(Contrato.Mascotas.COLUMNA_NOMBRECONTACTO, nombreContacto.text.toString())
            values.put(Contrato.Mascotas.COLUMNA_TELEFONOCONTACTO, telefono.text.toString())
            values.put(Contrato.Mascotas.COLUMNA_CORREOCONTACTO, correo.text.toString())

            val newUri = contentResolver.insert(Contrato.Mascotas.MASCOTA_URI, values)
            val newUserId = newUri!!.lastPathSegment
            mostrarMsg("Guardo exitosamente. ID de tu adopcion es: $newUserId", true)
            //Toast.makeText(this, "Mascota publicada",
                //Toast.LENGTH_LONG).show()
        }

        cancelar.setOnClickListener {
            val intent = Intent(this@FormularioActivity, ListaActivity::class.java)
            startActivity(intent)
        }
    }

    fun String.toEditable(): Editable =
        Editable.Factory.getInstance().newEditable(this)

    private fun cargarImagen() {
        val intent  = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/")
        startActivityForResult(Intent.createChooser(intent,"Seleccione la aplicacion"),10)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            val uri = data?.data
            imagen.setImageURI(uri)
            val vacio = ""
            nombre.text = vacio.toEditable()
            raza.text = vacio.toEditable()
            edad.text = vacio.toEditable()
            genero.text = vacio.toEditable()
            ubicacion.text = vacio.toEditable()
            color.text = vacio.toEditable()
            descripcion.text = vacio.toEditable()
            nombreContacto.text = vacio.toEditable()
            telefono.text = vacio.toEditable()
            correo.text = vacio.toEditable()

        }

    }

    private fun mostrarMsg(mensaje: String, exitoso: Boolean) {
        try {
            val dialogo = AlertDialog.Builder(this).create()
            dialogo.setTitle("Adopcion")
            dialogo.setMessage(mensaje)
            dialogo.setCancelable(false)
            dialogo.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok") { _, _ ->
                if(exitoso) {
                    val intent = Intent(this, ListaActivity::class.java)
                    startActivityForResult(intent, REQUEST_CODE)
                }
            }
            dialogo.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}