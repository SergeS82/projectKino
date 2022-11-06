package ru.anudx.project_kino

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.anudx.project_kino.databinding.CardItemBinding

class MainAdapter(val context: Context, val dataModel: ArrayList<DataModel>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){
    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.card_item,parent,false)
        return MainAdapter.MainViewHolder(view)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.title.text = dataModel[position].title
        holder.descr.text = dataModel[position].description
        holder.imageView.setImageResource(dataModel[position].img)
    }
    override fun getItemCount(): Int {
        return dataModel.size
    }
    //
    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.film_poster)
        val title = itemView.findViewById<TextView>(R.id.film_title)
        val descr = itemView.findViewById<TextView>(R.id.film_description)
    }
}

