// NovelaAdapter.kt
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feedback4_142514.DetallesNovelaActivity
import com.example.feedback4_142514.R
import com.example.feedback4_142514.modelos.Novela
import com.example.feedback4_142514.modelos.NovelaManager

class NovelaAdapter(
    private var novelas: List<Novela>,
    private val context: Context,
    private val novelaManager: NovelaManager
) : RecyclerView.Adapter<NovelaAdapter.NovelaViewHolder>() {

    inner class NovelaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        val authorTextView = itemView.findViewById<TextView>(R.id.authorTextView)
        val optionsButton = itemView.findViewById<ImageView>(R.id.optionsButton) // Botón para mostrar el menú

        fun bind(novela: Novela) {
            titleTextView.text = novela.titulo
            authorTextView.text = novela.autor

            // Abre DetallesNovelaActivity al hacer clic en el ítem
            itemView.setOnClickListener {
                val intent = Intent(context, DetallesNovelaActivity::class.java).apply {
                    putExtra("novelaId", novela.getId())
                }
                context.startActivity(intent)
            }

            // Configura el menú contextual
            optionsButton.setOnClickListener {
                showPopupMenu(it, novela)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovelaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_novela, parent, false)
        return NovelaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NovelaViewHolder, position: Int) {
        holder.bind(novelas[position])
    }

    override fun getItemCount(): Int = novelas.size

    // Muestra el menú contextual para el ítem seleccionado
    private fun showPopupMenu(view: View, novela: Novela) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.novela_item_menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_delete -> {
                    // Lógica de eliminación de la novela
                    novelaManager.deleteNovela(novela.titulo)
                    updateNovelas(novelaManager.getAllNovelas()) // Actualizar la lista
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    // Método para actualizar la lista de novelas en el adaptador
    fun updateNovelas(nuevasNovelas: List<Novela>) {
        novelas = nuevasNovelas
        notifyDataSetChanged()
    }
}
