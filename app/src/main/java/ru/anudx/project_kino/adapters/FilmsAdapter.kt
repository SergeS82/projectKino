package ru.anudx.project_kino.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.anudx.project_kino.DetailsActivityFilm
import ru.anudx.project_kino.MainActivity
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.ActivityMainBinding
import ru.anudx.project_kino.databinding.FilmsItemBinding
import ru.anudx.project_kino.model.FilmsData

class FilmsAdapter(val context: Context, val contectBinding: ActivityMainBinding): RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {
    var data = ArrayList<FilmsData>()
    set(value) {
        val diff = FilmsDiff(field, value)
        val diffResuld = DiffUtil.calculateDiff(diff)
        field = value
        diffResuld.dispatchUpdatesTo(this)
    }
    val dataManager = DataManager()
    class ViewHolder(itemView: View, b: FilmsItemBinding): RecyclerView.ViewHolder(itemView){
        val image = b.imageView
        val title = b.title
        val description = b.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.films_item,parent,false)
        val b = FilmsItemBinding.bind(item)
        item.setOnClickListener {
            val intent = Intent(context, DetailsActivityFilm()::class.java)
            context.startActivity(intent)
        }
        return ViewHolder(item, b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(data[position].image)
        holder.title.text = data[position].title
        holder.description.text = data[position].description
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
    }

}