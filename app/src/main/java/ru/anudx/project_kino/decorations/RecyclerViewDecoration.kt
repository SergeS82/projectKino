package ru.anudx.project_kino.decorations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ru.anudx.project_kino.R
import ru.anudx.project_kino.adapter.CommonHolder
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
        for (i in 0..parent.childCount-1) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            val v = parent.findViewHolderForLayoutPosition(position)
            val rId = when (v) {
                null -> -1
                else -> (v as CommonHolder).rId
            }
            if (rId == R.layout.card_item) {
                val b = CardItemBinding.bind(view)
                // TODO: надо как-то по другому преобразовать
                // position - годный признак для черезстрочной декорации
                if (position % 2 == 0) {
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
            }
            else if (rId == R.layout.activity_ad) {
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