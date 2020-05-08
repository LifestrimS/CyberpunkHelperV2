package com.example.cyberpunkhelperv2.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This class is recyclerview decoration class used to add space between recyclerview items.
 * Add following lines to it
 */
public class Space extends RecyclerView.ItemDecoration {
    private int mItemOffset;

    public Space(int itemOffset) {
        mItemOffset = itemOffset;
    }

    public Space(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildLayoutPosition(view) == 0)
            outRect.top = mItemOffset;
        outRect.left = mItemOffset;
        outRect.right = mItemOffset;
        outRect.bottom = mItemOffset;
    }
}