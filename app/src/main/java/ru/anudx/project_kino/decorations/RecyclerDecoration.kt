package ru.anudx.project_kino.decorations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.android.material.internal.ViewUtils.RelativePadding
import ru.anudx.project_kino.R
import ru.anudx.project_kino.databinding.FilmsItemBinding

class RecyclerDecoration(val context: Context, val sidePadding: Int, val topPadding: Int): ItemDecoration(){
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = topPadding
        outRect.bottom = topPadding
        outRect.left = sidePadding
        outRect.right = sidePadding
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.forEachIndexed { i, view ->
            val position = parent.getChildAdapterPosition(view)
            val b = FilmsItemBinding.bind(view)
            if (position % 2 == 0){
                b.constraint.background = ResourcesCompat.getDrawable(context.resources, R.color.card_background_light, context.theme)
            }
            else{
                b.constraint.background = ResourcesCompat.getDrawable(context.resources, R.color.card_background_dark, context.theme)
            }
        }
    }
}