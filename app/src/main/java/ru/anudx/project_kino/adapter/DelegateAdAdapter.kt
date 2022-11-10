package ru.anudx.project_kino.adapter

import android.content.Context
import android.os.Parcelable
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
import ru.anudx.project_kino.model.Item

class DelegateAdAdapter(val context: Context):
    AbsListItemAdapterDelegate<AdModel, Item, DelegateAdAdapter.ViewHolder>()
{
    class ViewHolder(itemView: View, b: ActivityAdBinding): RecyclerView.ViewHolder(itemView), CommonHolder{
        override val rId =  R.layout.activity_ad
    }

    override fun isForViewType(item: Item, items: MutableList<Item>, position: Int): Boolean {
        return item is Item
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_ad, parent,false)
        val b = ActivityAdBinding.bind(view)
        val adapter = TestAdapter(context)
        adapter.popupData.dataToBeParsed() // TODO: не перечитывать при смене ориентации 
        b.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        b.recyclerView.layoutManager
        b.recyclerView.setHasFixedSize(true)
        b.recyclerView.itemAnimator = DefaultItemAnimator()
        b.recyclerView.adapter = adapter
        // binding RecyclerView
        val br = ActivityAdBinding.bind(b.recyclerView.rootView)
        b.btnAdd.setOnClickListener {
            adapter.popupData.addData()
            b.recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
        }
        br.btnDel.setOnClickListener {
            adapter.popupData.removeData()
        }
        //

        val layoutManager = b.recyclerView.layoutManager as LinearLayoutManager
        val savePositionFirst = layoutManager.findFirstCompletelyVisibleItemPosition()
        val savePositionLast = layoutManager.findLastCompletelyVisibleItemPosition()
        var isLoading = false
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager
                val visibleItemCount: Int = layoutManager?.childCount ?: 0
                val totalItemCount: Int = layoutManager?.itemCount ?: 0
                val firstVisibleItems = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (!isLoading) {
                    if (visibleItemCount + firstVisibleItems >= totalItemCount){
                        isLoading = true
                    }
                }
            }
        }
        // TODO: устарело
        b.recyclerView.setOnScrollListener(scrollListener)
        return ViewHolder(view, b)
    }

    override fun onBindViewHolder(item: AdModel, holder: ViewHolder, payloads: MutableList<Any>) {
        //holder.title.text = item.title
        //holder.imageView.setImageResource(item.img)
    }
    fun scrollToStart(r: RecyclerView){
        r.smoothScrollToPosition(0)
    }
    fun scrollToPosition(r: RecyclerView, position: Int){
        r.scrollToPosition(position)
    }
}