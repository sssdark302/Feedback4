package com.example.feedback4_142514

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.feedback4_142514.modelos.Novela
import com.example.feedback4_142514.modelos.NovelaManager

class FragmentListaNovelas : Fragment() {

    private lateinit var novelaManager: NovelaManager
    private var listener: OnNovelaClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNovelaClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context debe implementar OnNovelaClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ListaNovelasScreen(onNovelaClick = { novela ->
                    listener?.onNovelaClick(novela)
                })
            }
        }
    }

    @Composable
    fun ListaNovelasScreen(onNovelaClick: (Novela) -> Unit) {
        val novelas = getNovelas()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(novelas) { novela ->
                NovelaItem(novela = novela, onClick = { onNovelaClick(novela) })
            }
        }
    }

    private fun getNovelas(): List<Novela> {
        novelaManager = NovelaManager(requireContext())
        return novelaManager.getAllNovelas()
    }

    interface OnNovelaClickListener {
        fun onNovelaClick(novela: Novela)
    }
}

@Composable
fun NovelaItem(novela: Novela, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Text(text = novela.titulo, style = MaterialTheme.typography.bodyLarge)
        Text(text = "Autor: ${novela.autor}", style = MaterialTheme.typography.bodySmall)
        Divider()
    }
}
