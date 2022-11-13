package ru.anudx.project_kino.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.anudx.project_kino.DetailsFilmActivity
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.FilmsItemBinding
import ru.anudx.project_kino.model.CommonData
import ru.anudx.project_kino.model.FilmsData

class DelegateFilmsAdapter(val context: Context): AbsListItemAdapterDelegate<FilmsData, CommonData, DelegateFilmsAdapter.ViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.films_item,parent,false)
        val b = FilmsItemBinding.bind(item)
        return ViewHolder(item, b)
    }

    override fun onBindViewHolder(item: FilmsData, holder: ViewHolder, payloads: MutableList<Any>) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(item)
            }
        }
    }

    override fun isForViewType(
        item: CommonData,
        items: MutableList<CommonData>,
        position: Int
    ): Boolean {
        return item is FilmsData
    }

}



