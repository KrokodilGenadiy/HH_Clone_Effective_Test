package com.example.hh_clone_test.search.ui.rv_adapters.item_decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.hh_clone_test.util.convertPx

class VacancyItemDecorator(private val paddingInDp: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = paddingInDp.convertPx()
        outRect.right = paddingInDp.convertPx() +paddingInDp.convertPx()
        outRect.left = paddingInDp.convertPx() +paddingInDp.convertPx()
        outRect.bottom = paddingInDp.convertPx()
    }
}