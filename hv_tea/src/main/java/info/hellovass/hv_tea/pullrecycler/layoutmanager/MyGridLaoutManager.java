package info.hellovass.hv_tea.pullrecycler.layoutmanager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by hellovass on 16/11/20.
 */

public class MyGridLaoutManager extends GridLayoutManager implements ILayoutManager {

  public MyGridLaoutManager(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public MyGridLaoutManager(Context context, int spanCount) {
    super(context, spanCount);
  }

  @Override public int fetchLastVisibleItemPosition() {
    return super.findLastVisibleItemPosition();
  }

  @Override public int fetchItemCount() {
    return super.getItemCount();
  }

  @Override public RecyclerView.LayoutManager getLayoutManager() {
    return this;
  }
}
