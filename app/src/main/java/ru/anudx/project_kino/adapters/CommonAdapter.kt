package ru.anudx.project_kino.adapters

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.anudx.project_kino.R
import ru.anudx.project_kino.model.CommonData
import ru.anudx.project_kino.model.FilmsData

class CommonAdapter(val context: Context): ListDelegationAdapter<List<CommonData>>() {
    val dataManager = DataManager()
    init {
        delegatesManager.addDelegate(DelegateFilmsAdapter(context))
    }
    fun setItems(items: ArrayList<CommonData>?) {
        items?.let {
            val diff = CommomDiff(this.items as ArrayList<CommonData>, it)
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
            var newData = mutableListOf<CommonData>()
            var titles = arrayListOf<String>()
            titles.addAll(context.resources.getStringArray(R.array.films_title))
            val descriptions = arrayListOf<String>()
            descriptions.addAll(context.resources.getStringArray(R.array.films_description))
            for (i in 0 until titles.size) {
                newData.add(FilmsData(i, titles[i], descriptions[i], images[i]))
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