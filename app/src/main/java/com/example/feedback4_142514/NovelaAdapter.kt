package com.example.feedback4_142514
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NovelaAdapter(private val novelas: List<Novela>) : RecyclerView.Adapter<NovelaAdapter.NovelaViewHolder>() {

    // ViewHolder que mantiene la referencia a los elementos de la vista del item
    class NovelaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tituloTextView: TextView = itemView.findViewById(R.id.tituloTextView)
        private val autorTextView: TextView = itemView.findViewById(R.id.autorTextView)
        private val generoTextView: TextView = itemView.findViewById(R.id.generoTextView)
        private val paginasTextView: TextView = itemView.findViewById(R.id.paginasTextView)
        private val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionTextView)

        // Función para enlazar los datos de una novela a los elementos de la vista
        fun bind(novela: Novela) {
            tituloTextView.text = novela.titulo
            autorTextView.text = novela.autor
            generoTextView.text = novela.genero
            paginasTextView.text = "Páginas: ${novela.paginas}"
            descripcionTextView.text = novela.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovelaViewHolder {
        // Inflamos el layout del item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_novela, parent, false)
        return NovelaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NovelaViewHolder, position: Int) {
        // Enlazamos la novela en la posición actual
        holder.bind(novelas[position])
    }

    override fun getItemCount(): Int {
        return novelas.size
    }
}
