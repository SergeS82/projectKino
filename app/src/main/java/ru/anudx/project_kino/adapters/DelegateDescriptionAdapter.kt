package ru.anudx.project_kino.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.DescriptionItemBinding
import ru.anudx.project_kino.model.InterfaceData
import ru.anudx.project_kino.model.DescriptionData

class DelegateDescriptionAdapter(val context: Context): AbsListItemAdapterDelegate<DescriptionData, InterfaceData, DelegateDescriptionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View, val b: DescriptionItemBinding): RecyclerView.ViewHolder(itemView), InterfaceViewHolder{
        val description = b.description
        override fun bind(item: InterfaceData) {
            when (item) {
                is DescriptionData -> {
                    description.text = item.description
                    b.inputText.setOnFocusChangeListener { view, hasFocuce ->
                        if (!hasFocuce) {
                            //item.description = b.inputText.text.toString()
                            description.text = b.inputText.text.toString()
                        }
                    }
                }
            }
        }
    }

    override fun isForViewType(
        item: InterfaceData,
        items: MutableList<InterfaceData>,
        position: Int
    ): Boolean {
        return item is DescriptionData
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.description_item, parent, false)
        val b = DescriptionItemBinding.bind(view)
        return ViewHolder(view, b)
    }

    override fun onBindViewHolder(
        item: DescriptionData,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) {
        when (holder){
            is ViewHolder -> holder.bind(item)
        }
    }
}