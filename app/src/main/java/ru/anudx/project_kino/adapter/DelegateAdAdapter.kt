package ru.anudx.project_kino.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.ActivityAdBinding
import ru.anudx.project_kino.model.AdModel
import ru.anudx.project_kino.model.FilmsModel
import ru.anudx.project_kino.model.Item

class DelegateAdAdapter(val context: Context):
    AbsListItemAdapterDelegate<AdModel, Item, DelegateAdAdapter.ViewHolder>()
{
    class ViewHolder(itemView: View, b: ActivityAdBinding): RecyclerView.ViewHolder(itemView), TestHolder{
        override val rId =  R.layout.activity_ad
    }

    override fun isForViewType(item: Item, items: MutableList<Item>, position: Int): Boolean {
        return item is AdModel
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_ad, parent,false)
        val b = ActivityAdBinding.bind(view)
        val adapter = TestAdapter(context)
        adapter.popupData.dataToBeParsed()
        b.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        b.recyclerView.layoutManager
        b.recyclerView.setHasFixedSize(true)
        b.recyclerView.itemAnimator = DefaultItemAnimator()
        b.recyclerView.adapter = adapter
        return ViewHolder(view, b)
    }

    override fun onBindViewHolder(item: AdModel, holder: ViewHolder, payloads: MutableList<Any>) {
        //holder.title.text = item.title
        //holder.imageView.setImageResource(item.img)
    }
}