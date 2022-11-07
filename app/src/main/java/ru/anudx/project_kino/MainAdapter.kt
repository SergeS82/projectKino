package ru.anudx.project_kino

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.anudx.project_kino.databinding.CardItemBinding

class MainAdapter(val context: Context, val dataModel: ArrayList<DataModel>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){
    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.card_item,parent,false)
        return MainViewHolder(view, CardItemBinding.bind(view))
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
    class MainViewHolder(itemView: View, b: CardItemBinding) : RecyclerView.ViewHolder(itemView) {
        val title = b.filmTitle
        val descr = b.filmDescription
        val imageView: ImageView = itemView.findViewById(R.id.film_poster)
    }
}

