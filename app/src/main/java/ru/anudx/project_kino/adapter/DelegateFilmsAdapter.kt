package ru.anudx.project_kino.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.CardItemBinding
import ru.anudx.project_kino.model.FilmsModel
import ru.anudx.project_kino.model.Item

class DelegateFilmsAdapter(val context: Context):
    AbsListItemAdapterDelegate<FilmsModel, Item, DelegateFilmsAdapter.ViewHolder>()
{

    class ViewHolder(itemView: View, b: CardItemBinding): RecyclerView.ViewHolder(itemView), CommonHolder{
        val title = b.filmTitle
        val descr = b.filmDescription
        val imageView: ImageView = itemView.findViewById(R.id.film_poster)
        override val rId =  R.layout.card_item
    }
    override fun isForViewType(item: Item, items: MutableList<Item>, position: Int): Boolean {
        return item is FilmsModel
    }
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.card_item,parent,false)
        return ViewHolder(view, CardItemBinding.bind(view))
    }

    override fun onBindViewHolder(
        item: FilmsModel,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.title.text = item.title
        holder.descr.text = item.description
        holder.imageView.setImageResource(item.img)
    }
}