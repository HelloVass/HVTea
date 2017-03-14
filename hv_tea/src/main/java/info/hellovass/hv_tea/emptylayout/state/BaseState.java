package info.hellovass.hv_tea.emptylayout.state;

import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;
import info.hellovass.hv_tea.emptylayout.EmptyLayout;
import java.lang.ref.WeakReference;

/**
 * Created by hello on 2017/3/12.
 */

public abstract class BaseState implements EmptyLayout.State {

  protected WeakReference<ViewHolder> mViewHolderWeakReference; // 对 ViewHolder 使用弱引用

  public BaseState(ViewHolder viewHolder) {

    mViewHolderWeakReference = new WeakReference<>(viewHolder);
  }
}
