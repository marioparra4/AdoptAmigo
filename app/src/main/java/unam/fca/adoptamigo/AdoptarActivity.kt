package unam.fca.adoptamigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdoptarActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var card: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adoptar)

        spinner = findViewById(R.id.spinMascota)
        card = findViewById(R.id.card1)
        iniciarSpinner()

    }

    private fun iniciarSpinner(){
        spinner.adapter = ArrayAdapter(this,
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.mascotas))

        spinner.onItemSelectedListener =
            object: AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0 -> {
                            card.visibility = View.VISIBLE
                            iniciarCard("Perros")
                        }

                        1 -> {
                            card.visibility = View.VISIBLE
                            iniciarCard("Gatos")
                        }

                    }
                }
            }
    }

    private fun iniciarCard(tipo : String){
        card.layoutManager = LinearLayoutManager(this)
        card.setHasFixedSize(true)
        if(tipo == "Perros"){
            card.adapter = AdaptadorCard(this, getPerros(), this)
        } else {
            card.adapter = AdaptadorCard(this, getGatos(), this)
        }
    }

    private fun getPerros(): MutableList<Mascota>{
        var perros: MutableList<Mascota> = mutableListOf()
        perros.add(Mascota(R.mipmap.perro_max, "Max", "Labrador", "1 año", "Macho", "Milpa Alta", "Negro", "Fiel y jugueton", "Patitas Felices MX", "5556879422", "patitasfelices@gmail.com"))
        perros.add(Mascota(R.mipmap.laika, "Laika", "Mestizo", "2 años", "Hembra", "Coyoacan", "Negro", "Amorosa e inquieta", "Kevin Luna", "5519849309", "kev_moon@gmail.com"))
        perros.add(Mascota(R.mipmap.guera, "Güera", "Golden retriever", "6 meses", "Hembra", "Alvaro Obregón", "Cafe con negro", "Amistosa y fiable","AdoptaMX", "5563160106", "adoptamx@hotmail.com"))
        perros.add(Mascota(R.mipmap.milaneso, "Milaneso", "Dalmata", "2 años", "Macho", "Tlalpan", "Blanco con negro", "Glotón y tierno","Huellitas de amor sin fronteras", "5520849331", "huellitas_amor@gmail.com" ))
        perros.add(Mascota(R.mipmap.camila, "Camila", "Pastor alemán", "3 años", "Hembra", "Miguel Hidalgo", "Cafe con negro", "Juguetona y tierna", "Yves Rodríguez", "5538243398", "yves_rodz@gmail.com"))
        perros.add(Mascota(R.mipmap.bombon, "Bombon", "Mestizo", "1 año", "Macho", "Cuauhtémoc", "Cafe con blanco", "Amoroso y fiel", "Salva un perro", "57445683", "salvaaunperro@hotmail.com"))

        return perros
    }

    private fun getGatos(): MutableList<Mascota>{
        var gatos: MutableList<Mascota> = mutableListOf()
        gatos.add(Mascota(R.mipmap.gato1, "Nina", "Gato americano", "5 meses", "Hembra", "Benito Juarez", "Negro y blanco", "Pequeña y dormilona", "Eric Martínez", "5518149030", "eric_martz@hotmail.com"))
        gatos.add(Mascota(R.mipmap.gato2, "Jair", "Gato", "1 año", "Macho", "Iztacalco", "Gris con blanco", "Medio menso y risueño", "Adopta Mascota", "55 14 83 94","ana@adoptamascota.com"))
        gatos.add(Mascota(R.mipmap.gato3, "Espantado", "Gato americano", "5 meses", "Hembra", "Benito Juarez", "Negro y blanco", "Pequeña y dormilona", "Huellitas de amor sin fronteras", "5520849331", "huellitas_amor@gmail.com"))
        gatos.add(Mascota(R.mipmap.gato4, "Nala", "Bombay", "1 año", "Hembra", "Benito Juarez", "Negro", "Muy juguetona y tierna", "Jennifer Sanchez", "5532467893", "jenisan@gmail.com"))
        gatos.add(Mascota(R.mipmap.gato5, "Luna", "Korat", "10 meses", "Hembra", "Lindavista", "Blanco", "Pequeña y dormilona", "Nancy Saldivar", "5596784532", "saldivar_n@hotmail.com"))
        gatos.add(Mascota(R.mipmap.gato6, "Luffy", "Somalí", "2 años", "Macho", "Ecatepec", "Gris", "Tranquilo y noble", "Adopta 4 Patitas", "5574249690", "adopta4p@hotmail.com"))
        return gatos
    }

    fun mostarDetalles(mascota: Mascota) {

        val intent : Intent = Intent(this, DetallesActivity()::class.java)
        intent.putExtra("imagen", mascota.imagen)
        intent.putExtra("nombre", mascota.nombre)
        intent.putExtra("raza", mascota.raza)
        intent.putExtra("edad", mascota.edad)
        intent.putExtra("genero", mascota.genero)
        intent.putExtra("ubicacion", mascota.ubicacion)
        intent.putExtra("color", mascota.color)
        intent.putExtra("descripcion", mascota.descripcion)
        intent.putExtra("nombreContacto", mascota.nombreContacto)
        intent.putExtra("telefonoContacto", mascota.telefonoContacto)
        intent.putExtra("correoContacto", mascota.correoContacto)
        startActivity(intent)
    }
}