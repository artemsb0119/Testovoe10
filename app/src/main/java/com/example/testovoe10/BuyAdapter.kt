package com.example.testovoe10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class BuyAdapter(val buys: List<Buy>, val balance: Float) : RecyclerView.Adapter<BuyAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.buy_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val buy = buys[position]
        holder.textViewCrypto.text = buy.name
        holder.textViewCost.text = buy.cost.toString()+"$"
        holder.buttonKol.text = buy.kol.toString()
    }

    override fun getItemCount(): Int {
        return buys.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewCrypto: TextView = itemView.findViewById(R.id.textViewCrypto)
        val textViewCost: TextView = itemView.findViewById(R.id.textViewCost)
        val buttonKol: AppCompatButton = itemView.findViewById(R.id.buttonKol)
        init {
            buttonKol.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(position)
                }
            }
        }
    }
}