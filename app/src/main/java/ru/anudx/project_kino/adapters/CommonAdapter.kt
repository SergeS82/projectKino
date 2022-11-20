package ru.anudx.project_kino.adapters

import android.content.Context
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.anudx.project_kino.R
import ru.anudx.project_kino.model.InterfaceData
import ru.anudx.project_kino.model.FilmsData
import ru.anudx.project_kino.model.DescriptionData

class CommonAdapter(val context: Context): ListDelegationAdapter<List<InterfaceData>>() {
    val dataManager = DataManager()
    var iterator: Int = 0
    init {
        delegatesManager.addDelegate(DelegateFilmsAdapter(context))
        delegatesManager.addDelegate(DelegateDescriptionAdapter(context))
    }
    fun setItems(items: ArrayList<InterfaceData>?) {
        items?.let {
            val diff = CommomDiff(this.items as ArrayList<InterfaceData>, it)
            val diffResuld = DiffUtil.calculateDiff(diff)
            this.items = it
            diffResuld.dispatchUpdatesTo(this)
        }
    }
    inner class DataManager{
        val images = arrayListOf(
            R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
            , R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
            , R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
            , R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight)
        fun init(){
            var newData = mutableListOf<InterfaceData>()
            var titles = arrayListOf<String>()
            titles.addAll(context.resources.getStringArray(R.array.films_title))
            val descriptions = arrayListOf<String>()
            descriptions.addAll(context.resources.getStringArray(R.array.films_description))
            var (j, k) = 0
            while (k < titles.size) {
                when ((iterator+1)%3) {
                    0 -> {j+=10; newData.add(DescriptionData(++iterator,"", j))}
                    else -> newData.add(FilmsData(++iterator, titles[k], descriptions[k], images[k], ++k))
                }
            }
            setItems(newData)
        }
        fun removeSwiped(position: Int){
            var newData = ArrayList(items)
            if (newData.size > 0) {
                newData.removeAt(position)
                setItems(newData)
            }
        }
    }
}