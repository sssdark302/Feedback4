
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feedback4_142514.NovelaDatabaseHelper
import com.example.feedback4_142514.R
import com.example.feedback4_142514.R.id.recycleView

class NovelaListActivity : AppCompatActivity() {

    private lateinit var novelaDatabaseHelper: NovelaDatabaseHelper
    private lateinit var novelaAdapter: NovelaAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_lista_novela)

        novelaDatabaseHelper = NovelaDatabaseHelper(this)
        val novelas = novelaDatabaseHelper.getAllNovelas()

        novelaAdapter = NovelaAdapter(novelas)
        val recyclerView = findViewById<RecyclerView>(recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = novelaAdapter
    }
}