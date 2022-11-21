package ru.anudx.project_kino

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

class TestLinearLayoutManager(val context: Context): LinearLayoutManager(context) {
    override fun findLastCompletelyVisibleItemPosition(): Int {
        val item: View? = findVisibleItem(childCount - 1, -1, true)
        return if (item == null) -1 else getPosition(item)
    }
    private fun findVisibleItem(
        fromIndex: Int,
        toIndex: Int,
        isCompletely: Boolean
    ): View? {
        val next = if (toIndex > fromIndex) 1 else -1
        var i = fromIndex
        while (i != toIndex) {
            val child: View? = getChildAt(i)
            child?.let {
                if (checkIsVisible(child, isCompletely)) {
                    return child
                }
                i += next
            }
        }
        return null
    }
    private fun checkIsVisible(child: View, isCompletely: Boolean): Boolean {
        val location = IntArray(2)
        child.getLocationOnScreen(location)
        val parent: View = child.parent as View
        val parentRect = Rect()
        parent.getGlobalVisibleRect(parentRect)
        return if (orientation == HORIZONTAL) {
            val childLeft = location[0]
            val childRight: Int = location[0] + child.width
            if (isCompletely) {
                childLeft >= parentRect.left && childRight <= parentRect.right
            } else {
                childLeft <= parentRect.right && childRight >= parentRect.left
            }
        } else {
            val childTop = location[1]
            val childBottom: Int = location[1] + child.height
            if (isCompletely) {
                childTop >= parentRect.top && childBottom <= parentRect.bottom
            } else {
                childTop <= parentRect.bottom && childBottom >= parentRect.top
            }
        }
    }

    override fun scrollToPosition(position: Int) {
        super.scrollToPosition(position)
    }
}