package unam.fca.adoptamigo

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
            card.adapter = AdaptadorCard(this, getPerros())
        } else {
            card.adapter = AdaptadorCard(this, getGatos())
        }
    }

    private fun getPerros(): MutableList<Mascota>{
        var perros: MutableList<Mascota> = mutableListOf()
        perros.add(Mascota(R.mipmap.perro_max, "Max", "Labrador", "1 año", "Macho", "Milpa Alta", "Negro", "Fiel y jugueton"))
        perros.add(Mascota(R.mipmap.laika, "Laika", "Mestizo", "2 años", "Hembra", "Coyoacan", "Negro", "Amorosa e inquieta"))
        perros.add(Mascota(R.mipmap.guera, "Güera", "Golden retriever", "6 meses", "Hembra", "Alvaro Obregón", "Cafe con negro", "Amistosa y fiable"))
        perros.add(Mascota(R.mipmap.milaneso, "Milaneso", "Dalmata", "2 años", "Macho", "Tlalpan", "Blanco con negro", "Glotón y tierno"))
        perros.add(Mascota(R.mipmap.camila, "Camila", "Pastor alemán", "3 años", "Hembra", "Miguel Hidalgo", "Cafe con negro", "Juguetona y tierna"))
        perros.add(Mascota(R.mipmap.bombon, "Bombon", "Mestizo", "1 año", "Macho", "Cuauhtémoc", "Cafe con blanco", "Amoroso y fiel"))

        return perros
    }

    private fun getGatos(): MutableList<Mascota>{
        var perros: MutableList<Mascota> = mutableListOf()
        perros.add(Mascota(R.mipmap.perro_max, "MAx", "Labrador", "1 año", "Macho", "Milpa Alta", "Negrp", "Jugueton"))
        perros.add(Mascota(R.mipmap.perro_max, "MAx", "Labrador", "1 año", "Macho", "Milpa Alta", "Negrp", "Jugueton"))
        perros.add(Mascota(R.mipmap.perro_max, "MAx", "ssssssssdsdsd", "1 año", "Macho", "Milpa Alta", "Negrp", "Jugueton"))
        return perros
    }
}