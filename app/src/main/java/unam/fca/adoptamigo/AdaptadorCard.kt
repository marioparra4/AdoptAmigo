package unam.fca.adoptamigo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCard (private val context: Context,
                     private  val mascotas: MutableList<Mascota>,
                     private val activity: MainActivity):
    RecyclerView.Adapter<AdaptadorCard.ViewHolder>()    {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(context)
            .inflate(R.layout.mascota_card, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mascota: Mascota = mascotas[position]
        holder.ivImagen.setImageResource(mascota.imagen)
        holder.tvNombre.text = mascota.nombre
        holder.tvRaza.text = mascota.raza
        holder.tvEdad.text = mascota.edad
    }

    override fun getItemCount(): Int {
        return mascotas.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var ivImagen: ImageView
        var tvNombre: TextView
        var tvRaza: TextView
        var tvEdad: TextView

        init {
            ivImagen = itemView.findViewById(R.id.imagen)
            tvNombre = itemView.findViewById(R.id.nombre)
            tvRaza = itemView.findViewById(R.id.raza)
            tvEdad = itemView.findViewById(R.id.edad)

            itemView.setOnClickListener{

                val mascota: Mascota = mascotas[position]
                // Dialogo
            }
        }
    }
}