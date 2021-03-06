package info.hellovass.hv_tea.adapter.viewgroup.base;

import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/2/28.
 */

public interface ItemViewDelegate<T> {

  int provideItemViewLayoutResId();

  boolean isForViewType(T entity, int position);

  void convert(ViewHolder holder, T entity, int position);
}
