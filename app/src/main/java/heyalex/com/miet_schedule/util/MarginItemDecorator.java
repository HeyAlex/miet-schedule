package heyalex.com.miet_schedule.util;

import android.graphics.Rect;
import android.support.annotation.Dimension;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MarginItemDecorator extends RecyclerView.ItemDecoration {
    @Dimension
    private final int verticalMargin;

    public MarginItemDecorator(int verticalMargin) {
        this.verticalMargin = verticalMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        outRect.set(0, 0, 0, verticalMargin);
    }

}
