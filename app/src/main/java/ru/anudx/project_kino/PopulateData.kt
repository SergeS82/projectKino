package ru.anudx.project_kino

import android.content.Context

class PopulateData(val context: Context) {
    private var dataModels = ArrayList<DataModel>()
    private var dataModelImages = mutableListOf<Int>(R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
        ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
        ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
        ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
        ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
        ,R.drawable.killbillv1, R.drawable.onceinhollywood, R.drawable.pulpfiction, R.drawable.thehatefulleight
    )
    fun dataToBeParsed(): ArrayList<DataModel>{
        var title = mutableListOf<String>()
        title.addAll(context.resources.getStringArray(R.array.film_title))
        var descr = mutableListOf<String>()
        descr.addAll(context.resources.getStringArray(R.array.film_descriptions))
        for (i in title.size..title.size-1){
            dataModels.add(DataModel(title[i], descr[i], dataModelImages[i]))
        }
        return dataModels
    }
    fun addData(adapter: MainAdapter){
        val data = adapter.dataModel
        var title = mutableListOf<String>()
        title.addAll(context.resources.getStringArray(R.array.film_title))
        var descr = mutableListOf<String>()
        descr.addAll(context.resources.getStringArray(R.array.film_descriptions))
        data.add(DataModel(title[2], descr[2], dataModelImages[2]))
        adapter.notifyItemInserted(adapter.dataModel.size)
    }
    fun removeLast(adapter: MainAdapter){
        val data = adapter.dataModel
        if (data.size>0) {
            data.removeLast()
            adapter.notifyItemRemoved(data.size)
        }
    }
}