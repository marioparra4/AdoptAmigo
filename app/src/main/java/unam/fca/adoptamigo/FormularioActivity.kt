package unam.fca.adoptamigo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import java.net.URI

class FormularioActivity : AppCompatActivity() {
    private lateinit var imagen : ImageView
    private lateinit var boton: Button
    private lateinit var enviar: Button
    private lateinit var cancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        imagen = findViewById(R.id.inserta)
        boton = findViewById(R.id.botonFoto)
        enviar = findViewById(R.id.enviar)
        cancelar = findViewById(R.id.cancelar)


        boton.setOnClickListener {
            cargarImagen()
        }

        enviar.setOnClickListener {
            val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Mascota publicada",
                Toast.LENGTH_LONG).show()
        }

        cancelar.setOnClickListener {
            val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

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

        }

    }


}