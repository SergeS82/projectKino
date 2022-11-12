package ru.anudx.project_kino.decorations

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.android.material.internal.ViewUtils.RelativePadding

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
}