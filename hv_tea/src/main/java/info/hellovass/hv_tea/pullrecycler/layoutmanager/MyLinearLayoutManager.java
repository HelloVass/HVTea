package info.hellovass.hv_tea.pullrecycler.layoutmanager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hellovass on 16/11/20.
 */

public class MyLinearLayoutManager extends LinearLayoutManager implements ILayoutManager {

  public MyLinearLayoutManager(Context context) {
    super(context);
  }

  public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
    super(context, orientation, reverseLayout);
  }

  @Override public int fetchLastVisibleItemPosition() {
    return super.findLastCompletelyVisibleItemPosition();
  }

  @Override public int fetchItemCount() {
    return super.getItemCount();
  }

  @Override public RecyclerView.LayoutManager getLayoutManager() {
    return this;
  }
}
