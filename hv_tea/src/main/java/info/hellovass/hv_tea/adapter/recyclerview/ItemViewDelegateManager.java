package info.hellovass.hv_tea.adapter.recyclerview;

import android.util.SparseArray;
import info.hellovass.hv_tea.adapter.recyclerview.base.ItemViewDelegate;

/**
 * Created by hello on 2017/2/27.
 */

public final class ItemViewDelegateManager<T> {

  private SparseArray<ItemViewDelegate<T>> mItemViewDelegateSparseArray = new SparseArray<>();

  public int getItemViewDelegateCount() {

    return mItemViewDelegateSparseArray.size();
  }

  public ItemViewDelegateManager<T> addItemViewDelegate(ItemViewDelegate<T> delegate) {

    int viewType = mItemViewDelegateSparseArray.size();

    if (delegate != null) {
      mItemViewDelegateSparseArray.put(viewType, delegate);
    }

    return this;
  }

  public ItemViewDelegateManager<T> addItemViewDelegate(int viewType,
      ItemViewDelegate<T> delegate) {

    ItemViewDelegate<T> itemViewDelegate = mItemViewDelegateSparseArray.get(viewType);

    if (itemViewDelegate != null) {

      throw new IllegalArgumentException("这个 ItemViewDelegate 已经注册过了， viewType -->>>"
          + viewType
          + ", ItemViewDelegate -->>>"
          + itemViewDelegate);
    }

    mItemViewDelegateSparseArray.put(viewType, delegate);

    return this;
  }

  public ItemViewDelegateManager<T> removeItemViewDelegate(ItemViewDelegate<T> delegate) {

    if (delegate == null) {

      throw new IllegalArgumentException("ItemViewDelegate can't be null");
    }

    int index = mItemViewDelegateSparseArray.indexOfValue(delegate);

    if (index >= 0) {
      mItemViewDelegateSparseArray.removeAt(index);
    }

    return this;
  }

  public ItemViewDelegateManager<T> removeItemViewDelegate(int itemType) {

    int index = mItemViewDelegateSparseArray.indexOfKey(itemType);

    if (index >= 0) {
      mItemViewDelegateSparseArray.removeAt(index);
    }

    return this;
  }

  public int getItemViewType(T item, int position) {

    int itemViewDelegateCount = mItemViewDelegateSparseArray.size();

    for (int i = itemViewDelegateCount - 1; i >= 0; i--) {

      ItemViewDelegate<T> itemViewDelegate = mItemViewDelegateSparseArray.valueAt(i);

      if (itemViewDelegate.isForViewType(item, position)) {

        return mItemViewDelegateSparseArray.keyAt(i);
      }
    }

    throw new IllegalArgumentException(
        "No ItemViewDelegate added that matches position=" + position + " in data source");
  }

  public void convert(ViewHolder holder, T item, int position) {

    int itemViewDelegateCount = mItemViewDelegateSparseArray.size();

    for (int i = 0; i < itemViewDelegateCount; i++) {

      ItemViewDelegate<T> itemViewDelegate = mItemViewDelegateSparseArray.valueAt(i);

      if (itemViewDelegate.isForViewType(item, position)) {

        itemViewDelegate.convert(holder, item, position);
        return;
      }
    }

    throw new IllegalArgumentException(
        "No ItemViewDelegateManager added that matches position=" + position + " in data source");
  }

  public ItemViewDelegate<T> getItemViewDelegate(int viewType) {

    return mItemViewDelegateSparseArray.get(viewType);
  }

  public int getItemViewLayoutResId(int viewType) {

    return getItemViewDelegate(viewType).provideItemViewLayoutResId();
  }

  public int getItemViewType(ItemViewDelegate<T> itemViewDelegate) {

    return mItemViewDelegateSparseArray.indexOfValue(itemViewDelegate);
  }
}
