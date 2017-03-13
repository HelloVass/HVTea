package info.hellovass.hv_tea.adapter.viewgroup.base;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hello on 2017/2/28.
 */

public interface ViewGroupAdapter {

  View getView(ViewGroup viewGroup, int position);

  int getCount();
}
