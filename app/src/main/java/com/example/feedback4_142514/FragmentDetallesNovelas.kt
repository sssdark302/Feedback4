package com.example.feedback4_142514

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.feedback4_142514.modelos.Novela

class FragmentDetallesNovelas : Fragment() {

    private lateinit var novela: Novela
    private var listener: OnDetalleNovelaListener? = null

    // Interfaz para notificar a MainActivity de acciones realizadas en FragmentDetallesNovelas
    interface OnDetalleNovelaListener {
        fun onMarcarFavorita(novela: Novela)
        fun onVolverALaLista()
    }

    // Configuración para inicializar el fragmento con una novela específica
    fun configurar(novela: Novela) {
        this.novela = novela
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDetalleNovelaListener) {
            listener = context
        } else {
            throw RuntimeException("$context debe implementar OnDetalleNovelaListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                DetallesNovelaContent(
                    novela = novela,
                    onMarcarFavorita = {
                        novela.esFavorita = !novela.esFavorita  // Alternar favorito
                        listener?.onMarcarFavorita(novela)  // Notificar a la actividad
                    },
                    onVolver = { listener?.onVolverALaLista() }  // Notificar para volver a la lista
                )
            }
        }
    }
}

@Composable
fun DetallesNovelaContent(
    novela: Novela,
    onMarcarFavorita: (Novela) -> Unit,
    onVolver: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = novela.titulo, style = MaterialTheme.typography.headlineMedium)
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "Autor: ${novela.autor}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Publicado en: ${novela.getId()}", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = novela.descripcion, style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onMarcarFavorita(novela) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(if (novela.esFavorita) "Quitar de Favoritas" else "Marcar como Favorita")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onVolver,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer)
        ) {
            Text("Volver")
        }
    }
}
