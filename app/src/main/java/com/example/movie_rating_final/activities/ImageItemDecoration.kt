package com.example.movie_rating_final.activities

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ImageItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        // Add spacing to the right of each item
        outRect.right = spacing
    }
}
