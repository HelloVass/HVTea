package info.hellovass.hv_tea.pullrecycler.layoutmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by hellovass on 16/11/20.
 */

public class MyStaggeredGridLayoutManager extends StaggeredGridLayoutManager
    implements ILayoutManager {

  public MyStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public MyStaggeredGridLayoutManager(int spanCount, int orientation) {
    super(spanCount, orientation);
  }

  @Override public int fetchLastVisibleItemPosition() {
    int[] lastPositions = new int[super.getSpanCount()];
    findLastVisibleItemPositions(lastPositions);
    return findMax(lastPositions);
  }

  @Override public int fetchItemCount() {
    return super.getItemCount();
  }

  @Override public RecyclerView.LayoutManager getLayoutManager() {
    return this;
  }

  private int findMax(int[] lastPositions) {

    int max = lastPositions[0];

    for (int value : lastPositions) {
      if (value > max) {
        max = value;
      }
    }

    return max;
  }
}
