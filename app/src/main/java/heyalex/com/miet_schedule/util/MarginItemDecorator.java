package heyalex.com.miet_schedule.util;

import android.graphics.Rect;
import android.support.annotation.Dimension;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class MarginItemDecorator extends RecyclerView.ItemDecoration {
  @Dimension
  private final int horizontalMargin;
  @Dimension
  private final int verticalMargin;

  public MarginItemDecorator(int horizontalMargin, int verticalMargin) {
    this.horizontalMargin = horizontalMargin;
    this.verticalMargin = verticalMargin;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    int itemsPerRow = getItemsPerRow(parent.getLayoutManager());

    int itemPosition = parent.getChildAdapterPosition(view);
    if (itemsPerRow == 1) {
      outRect.set(0, 0, 0, verticalMargin);
    } else if (isLeftmostItem(itemPosition, itemsPerRow)) {
      outRect.set(0, 0, horizontalMargin / 2, verticalMargin);
    } else if (isRightmostItem(itemPosition, itemsPerRow)) {
      outRect.set(horizontalMargin / 2, 0, 0, verticalMargin);
    } else {
      outRect.set(horizontalMargin / 2, 0, horizontalMargin / 2, verticalMargin);
    }
  }

  private int getItemsPerRow(RecyclerView.LayoutManager layoutManager) {
    if (layoutManager instanceof GridLayoutManager) {
      return ((GridLayoutManager)layoutManager).getSpanCount();
    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
      return ((StaggeredGridLayoutManager)layoutManager).getSpanCount();
    } else {
      // LinearLayoutManager
      return 1;
    }
  }

  private boolean isLeftmostItem(int position, int itemsPerRow) {
    return position % itemsPerRow == 0;
  }

  private boolean isRightmostItem(int position, int itemsPerRow) {
    return (position + 1) % itemsPerRow == 0;
  }
}
