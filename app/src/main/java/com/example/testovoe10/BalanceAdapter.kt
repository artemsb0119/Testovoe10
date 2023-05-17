package com.example.testovoe10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BalanceAdapter (val crypts: List<Crypta>, val balance: Float) : RecyclerView.Adapter<BalanceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypta_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crypta = crypts[position]
        holder.textViewCrypto.text = crypta.name
        holder.textViewCost.text = crypta.cost.toString()+"$"
        val dol = balance/crypta.cost
        holder.textViewKol.text = String.format("%.5f", dol)
    }

    override fun getItemCount(): Int {
        return crypts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCrypto: TextView = itemView.findViewById(R.id.textViewCrypto)
        val textViewCost: TextView = itemView.findViewById(R.id.textViewCost)
        val textViewKol: TextView = itemView.findViewById(R.id.textViewKol)
    }
}