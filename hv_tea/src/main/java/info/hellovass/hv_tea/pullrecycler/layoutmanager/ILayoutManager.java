package info.hellovass.hv_tea.pullrecycler.layoutmanager;

import android.support.v7.widget.RecyclerView;

/**
 * Created by hellovass on 16/11/20.
 */

public interface ILayoutManager {

  int fetchLastVisibleItemPosition();

  int fetchItemCount();

  RecyclerView.LayoutManager getLayoutManager();
}
