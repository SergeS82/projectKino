package ru.anudx.project_kino.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.anudx.project_kino.*
import ru.anudx.project_kino.databinding.FilmsItemBinding
import ru.anudx.project_kino.fragments.DetailFragment
import ru.anudx.project_kino.model.InterfaceData
import ru.anudx.project_kino.model.FilmsData

class DelegateFilmsAdapter: AbsListItemAdapterDelegate<FilmsData, InterfaceData, DelegateFilmsAdapter.ViewHolder>() {
    private val context: MainActivity by lazy {  App.mainContext }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), InterfaceViewHolder{
        val b = FilmsItemBinding.bind(itemView)
        val image = b.imageView
        val title = b.title
        val description = b.description
        override var id: Int = -1
            get() = field
            set(value) {field = value}

        override fun bind(item: InterfaceData) {
            when (item) {
                is FilmsData -> {
                    image.setImageResource(item.image)
                    title.text = item.title
                    description.text = item.description
                    itemView.setOnClickListener {
                        val bundle = Bundle()
                        //bundle.putString("TitleToDetail", title.text.toString())
                        //bundle.putInt("ImageToDetail", item.image)
                        bundle.putParcelable("FilmsData",item)
                        val fragment = DetailFragment()
                        fragment.arguments = bundle
                        (context as MainActivity).supportFragmentManager
                            .beginTransaction()
                            .add(R.id.main_layout, fragment, context.resources.getString(R.string.detail_fragment_tag))
                            .addSharedElement(image,"transaction_poster")
                            .addToBackStack(null)
                            .commit()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.films_item,parent,false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(item: FilmsData, holder: ViewHolder, payloads: MutableList<Any>) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(item)
            }
        }
    }

    override fun isForViewType(
        item: InterfaceData,
        items: MutableList<InterfaceData>,
        position: Int
    ): Boolean {
        return item is FilmsData
    }

}



