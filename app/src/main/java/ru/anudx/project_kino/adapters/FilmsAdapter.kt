package ru.anudx.project_kino.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.anudx.project_kino.DetailsFilmActivity
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.FilmsItemBinding
import ru.anudx.project_kino.model.FilmsData

class FilmsAdapter(val context: Context): RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {
    var data = ArrayList<FilmsData>()
    set(value) {
        val diff = FilmsDiff(field, value)
        val diffResuld = DiffUtil.calculateDiff(diff)
        field = value
        diffResuld.dispatchUpdatesTo(this)
    }
    val dataManager = DataManager()
    inner class ViewHolder(itemView: View, b: FilmsItemBinding): RecyclerView.ViewHolder(itemView){
        val image = b.imageView
        val title = b.title
        val description = b.description
        fun bind(item: FilmsData){
            image.setImageResource(item.image)
            title.text = item.title
            description.text = item.description
            itemView.setOnClickListener {
                val intent = Intent(context, DetailsFilmActivity()::class.java)
                val bundle = Bundle()
                bundle.putParcelable("film", item)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.films_item,parent,false)
        val b = FilmsItemBinding.bind(item)
        return ViewHolder(item, b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(data[position])
            }
        }
    }
    override fun getItemCount(): Int = data.size
    inner class DataManager{
        val images = arrayListOf(R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
        ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
        ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
        ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight)
        fun init(){
            var titles = arrayListOf<String>()
            titles.addAll(context.resources.getStringArray(R.array.films_title))
            val descriptions = arrayListOf<String>()
            descriptions.addAll(context.resources.getStringArray(R.array.films_description))
            for (i in 0 until titles.size) {
                data.add(FilmsData(i, titles[i], descriptions[i], images[i]))
            }
        }
        fun removeSwiped(position: Int){
            var newData = ArrayList(data)
            if (newData.size>0) {
                newData.removeAt(position)
                data = newData
            }
        }
    }

}



