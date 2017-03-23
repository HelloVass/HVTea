package info.hellovass.hv_tea.bottom_navigation;

import info.hellovass.hv_tea.adapter.viewgroup.base.ViewGroupAdapter;

/**
 * Created by hello on 2017/3/22.
 */

public interface PagerIndicator {

  void setAdapter(ViewGroupAdapter adapter);

  void notifyDataSetChanged();
}
