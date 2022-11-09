package ru.anudx.project_kino.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.AdItemBinding
import ru.anudx.project_kino.model.FilmsModel

class TestAdapter(val context: Context): RecyclerView.Adapter<TestAdapter.ViewHolder>() {
    var data: ArrayList<FilmsModel> = ArrayList()
        set(value){
            field = value
        }
    val popupData = this.DataManager()

    class ViewHolder(itemView: View, b: AdItemBinding): RecyclerView.ViewHolder(itemView) {
        val image = b.filmPoster
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ad_item,parent,false)
        return ViewHolder(view, AdItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(data[position].img)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    inner class DataManager {
        private var dataModelImages = mutableListOf<Int>(
            R.drawable.killbillv1,
            R.drawable.onceinhollywood,
            R.drawable.pulpfiction,
            R.drawable.thehatefulleight,
            R.drawable.killbillv1,
            R.drawable.onceinhollywood,
            R.drawable.pulpfiction,
            R.drawable.thehatefulleight,
            R.drawable.killbillv1,
            R.drawable.onceinhollywood,
            R.drawable.pulpfiction,
            R.drawable.thehatefulleight,
            R.drawable.killbillv1,
            R.drawable.onceinhollywood,
            R.drawable.pulpfiction,
            R.drawable.thehatefulleight,
            R.drawable.killbillv1,
            R.drawable.onceinhollywood,
            R.drawable.pulpfiction,
            R.drawable.thehatefulleight,
            R.drawable.killbillv1,
            R.drawable.onceinhollywood,
            R.drawable.pulpfiction,
            R.drawable.thehatefulleight
        )

        fun dataToBeParsed() {
            var data1 = ArrayList<FilmsModel>()
            var title = mutableListOf<String>()
            title.addAll(context.resources.getStringArray(R.array.film_title))
            var descr = mutableListOf<String>()
            descr.addAll(context.resources.getStringArray(R.array.film_descriptions))
            for (i in 0..title.size - 1) {
                data1.add(FilmsModel(title[i], descr[i], dataModelImages[i], "f${i}"))
            }
            data = ArrayList(data1)
        }
    }
}