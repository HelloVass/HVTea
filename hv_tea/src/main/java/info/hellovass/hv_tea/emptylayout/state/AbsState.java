package info.hellovass.hv_tea.emptylayout.state;

import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;
import info.hellovass.hv_tea.emptylayout.EmptyLayout;

/**
 * Created by hello on 2017/3/12.
 */

public abstract class AbsState implements EmptyLayout.State {

  protected ViewHolder mViewHolder;

  public AbsState(ViewHolder viewHolder) {

    mViewHolder = viewHolder;
  }
}
