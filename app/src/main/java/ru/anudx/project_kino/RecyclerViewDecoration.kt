package ru.anudx.project_kino

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.viewbinding.ViewBinding
import ru.anudx.project_kino.databinding.AdItemBinding
import ru.anudx.project_kino.databinding.CardItemBinding

class RecyclerViewDecoration(val context: Context, val sidePadding: Int, val topPadding: Int): ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = topPadding
        outRect.top = topPadding
        outRect.left = sidePadding
        outRect.right = sidePadding
    }
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        parent.forEachIndexed { i, view ->
            // TODO: совсем неврасиво надо переделать
            try {
                val b = CardItemBinding.bind(view)
                // TODO: надо как-то по другому преобразовать 
                if ( "02468".contains(b.id.text[b.id.text.length-1]) ) {
                    b.filmConstraint.background = ResourcesCompat.getDrawable(
                        context.resources,
                        R.color.r_item_back_2,
                        context.theme
                    )
                    b.filmPoster.background = ResourcesCompat.getDrawable(
                        context.resources,
                        R.color.r_item_back_2,
                        context.theme
                    )
                } else {
                    b.filmConstraint.background = ResourcesCompat.getDrawable(
                        context.resources,
                        R.color.r_item_back_1,
                        context.theme
                    )
                    b.filmPoster.background = ResourcesCompat.getDrawable(
                        context.resources,
                        R.color.r_item_back_1,
                        context.theme
                    )
                }
            } catch (e: NullPointerException) {
//                val b = AdItemBinding.bind(view)
//                b.filmConstraint.background = ResourcesCompat.getDrawable(
//                    context.resources,
//                    R.color.r_item_back_2,
//                    context.theme
//                )
//                b.filmPoster.background = ResourcesCompat.getDrawable(
//                    context.resources,
//                    R.color.r_item_back_2,
//                    context.theme
//                )
            }
        }
    }
}