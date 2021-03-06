package info.hellovass.hv_tea.adapter.recyclerview.base;

import info.hellovass.hv_tea.adapter.recyclerview.ViewHolder;

/**
 * Created by hello on 2017/2/27.
 */

public interface ItemViewDelegate<T> {

  int provideItemViewLayoutResId();

  boolean isForViewType(T item,int position);

  void convert(ViewHolder holder,T entity,int position);
}
