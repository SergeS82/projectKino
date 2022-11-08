package ru.anudx.project_kino

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.anudx.project_kino.databinding.CardItemBinding

class FilmsAdapter(val context: Context) : RecyclerView.Adapter<FilmsAdapter.MainViewHolder>(){
    var films: ArrayList<FilmsModel> = ArrayList()
    set(newValue) {
        val diff = FilmsDiffUtil(field, newValue)
        val diffResult = DiffUtil.calculateDiff(diff)
        field = newValue
        diffResult.dispatchUpdatesTo(this)
    }
    val popupData = DataManager();
    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.card_item,parent,false)
        //setHasStableIds(true)
        return MainViewHolder(view, CardItemBinding.bind(view))
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.title.text = films[position].title
        holder.descr.text = films[position].description
        holder.imageView.setImageResource(films[position].img)
    }
    override fun getItemCount(): Int {
        return films.size
    }
    // Holder
    class MainViewHolder(itemView: View, b: CardItemBinding) : RecyclerView.ViewHolder(itemView) {
        val title = b.filmTitle
        val descr = b.filmDescription
        val imageView: ImageView = itemView.findViewById(R.id.film_poster)
    }

    override fun onBindViewHolder(
        holder: MainViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        }
        else{
            val i =1
        }
    }
    //
    inner class DataManager {
        private var dataModels = ArrayList<FilmsModel>()
        private var dataModelImages = mutableListOf<Int>(R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
            ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
            ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
            ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
            ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
            ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
        )
        fun dataToBeParsed() {
            var title = mutableListOf<String>()
            title.addAll(context.resources.getStringArray(R.array.film_title))
            var descr = mutableListOf<String>()
            descr.addAll(context.resources.getStringArray(R.array.film_descriptions))
            for (i in 0..title.size-1){
                films.add(FilmsModel(title[i], descr[i], dataModelImages[i], i))
            }
        }
        fun addData(){
            var data = ArrayList(films)
            var title = mutableListOf<String>()
            title.addAll(context.resources.getStringArray(R.array.film_title))
            var descr = mutableListOf<String>()
            descr.addAll(context.resources.getStringArray(R.array.film_descriptions))
            data.add(FilmsModel(title[2], descr[2], dataModelImages[2],films.size))
            films = data
        }
        fun removeLast(){
            var data = ArrayList(films)
            if (data.size>0) {
                data.removeLast()
                films = data
            }
        }
        fun updateTitle(title: String) {
            if (films.size > 0) {
                var data = ArrayList(films)
                data[0] = data[0].copy(title = title)
                films = data
            }
        }
    }
}

