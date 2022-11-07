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
        for (i in 0..title.size-1){
            dataModels.add(DataModel(title[i], descr[i], dataModelImages[i]))
        }
        return dataModels
    }
}