package ru.anudx.project_kino.adapter

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.anudx.project_kino.R
import ru.anudx.project_kino.model.AdModel
import ru.anudx.project_kino.model.FilmsModel
import ru.anudx.project_kino.model.Item

class MainAdapter(val context: Context) : ListDelegationAdapter<List<Item>>() {
    val popupData = DataManager()


    init {
        delegatesManager.addDelegate(DelegateFilmsAdapter(context))
        delegatesManager.addDelegate(DelegateAdAdapter(context))
    }

    override fun setItems(items: List<Item>?) {
        val diff = FilmsDiffUtil(this.items as ArrayList<FilmsModel>, items as ArrayList<FilmsModel>)
        val diffResult = DiffUtil.calculateDiff(diff)
        this.items = items
        diffResult.dispatchUpdatesTo(this)
    }

    //
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
            var data = ArrayList<Item>()
            var data2 = ArrayList<Item>()
            var title = mutableListOf<String>()
            title.addAll(context.resources.getStringArray(R.array.film_title))
            var descr = mutableListOf<String>()
            descr.addAll(context.resources.getStringArray(R.array.film_descriptions))
            for (i in 0..title.size - 1) {
                if (i == 6)
                    data.add(AdModel(ArrayList<FilmsModel>(), "a${i}"))
                else
                    data.add(FilmsModel(title[i], descr[i], dataModelImages[i], "f${i}"))
                data2.add(FilmsModel(title[i], descr[i], dataModelImages[i], "f${i}"))
            }
            //(data[2] as AdModel).list = ArrayList(data2)
            items = data
        }
//        fun addData(){
//            var data = ArrayList(films)
//            var title = mutableListOf<String>()
//            title.addAll(context.resources.getStringArray(R.array.film_title))
//            var descr = mutableListOf<String>()
//            descr.addAll(context.resources.getStringArray(R.array.film_descriptions))
//            data.add(FilmsModel(title[2], descr[2], dataModelImages[2],films.size))
//            films = data
//        }
//        fun removeLast(){
//            var data = ArrayList(films)
//            if (data.size>0) {
//                data.removeLast()
//                films = data
//            }
//        }
//        fun updateTitle(title: String) {
//            if (films.size > 0) {
//                var data = ArrayList(films)
//                data[0] = data[0].copy(title = title)
//                films = data
//            }
//        }
    }
}