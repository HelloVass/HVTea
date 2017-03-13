package info.hellovass.hv_tea.emptylayout.state;

import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;
import info.hellovass.hv_tea.emptylayout.EmptyLayout;

/**
 * Created by hello on 2017/3/12.
 */

public abstract class BaseState implements EmptyLayout.State{

  protected ViewHolder mViewHolder;

  public BaseState(ViewHolder viewHolder) {

    mViewHolder = viewHolder;
  }
}
