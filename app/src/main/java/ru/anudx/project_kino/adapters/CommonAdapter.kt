package ru.anudx.project_kino.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.anudx.project_kino.model.InterfaceData

class CommonAdapter: ListDelegationAdapter<List<InterfaceData>>() {
    val dataManager: DataManager by lazy{ DataManager() }
    var iterator: Int = 0
    init {
        delegatesManager.addDelegate(DelegateFilmsAdapter())
    }
    fun setItems(items: ArrayList<InterfaceData>?) {
        items?.let {
            val diff = if (this.items != null) {
                CommomDiff(this.items as ArrayList<InterfaceData>, it)
            } else {
                CommomDiff(ArrayList(), it)
            }
            val diffResuld = DiffUtil.calculateDiff(diff)
            this.items = it
            diffResuld.dispatchUpdatesTo(this)
        }
    }
    inner class DataManager{
        fun setData(dataBase: ArrayList<InterfaceData>) {
            setItems(dataBase)
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