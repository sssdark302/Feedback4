package com.example.feedback4_142514

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NovelaAdapter(private val novelas: List<Novela>,
                    private val onClick: (Novela) -> Unit
) : RecyclerView.Adapter<NovelaAdapter.NovelaViewHolder>() {
    inner class NovelaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tituloTextView: TextView = view.findViewById(R.id.novelaTituloTextView)

        fun bind(novela: Novela) {
            tituloTextView.text = novela.titulo
            itemView.setOnClickListener { onClick(novela) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovelaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_novela, parent, false)
        return NovelaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NovelaViewHolder, position: Int) {
        holder.bind(novelas[position])
    }

    override fun getItemCount() = novelas.size
}