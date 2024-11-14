import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feedback4_142514.Novela
import com.example.feedback4_142514.R

class NovelaAdapter(private val novelas: List<Novela>) : RecyclerView.Adapter<NovelaAdapter.NovelaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovelaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_novela, parent, false)
        return NovelaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NovelaViewHolder, position: Int) {
        val novela = novelas[position]
        holder.bind(novela)
    }

    override fun getItemCount(): Int = novelas.size

    class NovelaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tituloTextView: TextView = itemView.findViewById(R.id.tituloTextView)
        private val autorTextView: TextView = itemView.findViewById(R.id.autorTextView)

        fun bind(novela: Novela) {
            tituloTextView.text = novela.titulo
            autorTextView.text = novela.autor
        }
    }
}