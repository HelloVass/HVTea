package info.hellovass.hv_tea.adapter.viewgroup;

import android.util.SparseArray;

/**
 * Created by zhy on 16/6/22.
 */
public class ItemViewDelegateManager<T> {

  private SparseArray<ItemViewDelegate<T>> mItemViewDelegateSparseArray = new SparseArray<>();

  public int getItemViewDelegateCount() {

    return mItemViewDelegateSparseArray.size();
  }

  public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate) {

    int viewType = mItemViewDelegateSparseArray.size();

    if (delegate != null) {

      mItemViewDelegateSparseArray.put(viewType, delegate);
    }

    return this;
  }

  public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate) {

    if (mItemViewDelegateSparseArray.get(viewType) != null) {

      throw new IllegalArgumentException("这个 ItemViewDelegate 已经注册过了， viewType -->>>"
          + viewType
          + ", ItemViewDelegate -->>>"
          + delegate);
    }

    mItemViewDelegateSparseArray.put(viewType, delegate);

    return this;
  }

  public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate) {

    if (delegate == null) {

      throw new IllegalArgumentException("ItemViewDelegate can't be null");
    }

    int indexToRemove = mItemViewDelegateSparseArray.indexOfValue(delegate);

    if (indexToRemove >= 0) {
      mItemViewDelegateSparseArray.removeAt(indexToRemove);
    }

    return this;
  }

  public ItemViewDelegateManager<T> removeDelegate(int itemType) {

    int indexToRemove = mItemViewDelegateSparseArray.indexOfKey(itemType);

    if (indexToRemove >= 0) {

      mItemViewDelegateSparseArray.removeAt(indexToRemove);
    }

    return this;
  }

  public int getItemViewType(T item, int position) {

    int itemViewDelegateCount = mItemViewDelegateSparseArray.size();

    for (int i = itemViewDelegateCount - 1; i >= 0; i--) {

      ItemViewDelegate<T> delegate = mItemViewDelegateSparseArray.valueAt(i);

      if (delegate.isForViewType(item, position)) {

        return mItemViewDelegateSparseArray.keyAt(i);
      }
    }

    throw new IllegalArgumentException(
        "No ItemViewDelegate added that matches position=" + position + " in data source");
  }

  public void convert(ViewHolder holder, T entity, int position) {

    int itemViewDelegateCount = mItemViewDelegateSparseArray.size();

    for (int i = 0; i < itemViewDelegateCount; i++) {

      ItemViewDelegate<T> delegate = mItemViewDelegateSparseArray.valueAt(i);

      if (delegate.isForViewType(entity, position)) {

        delegate.convert(holder, entity, position);
        return;
      }
    }

    throw new IllegalArgumentException(
        "No ItemViewDelegateManager added that matches position=" + position + " in data source");
  }

  public int getItemViewLayoutResId(int viewType) {

    return mItemViewDelegateSparseArray.get(viewType).provideItemViewLayoutResId();
  }

  public int getItemViewType(ItemViewDelegate<T> itemViewDelegate) {

    return mItemViewDelegateSparseArray.indexOfValue(itemViewDelegate);
  }

  public ItemViewDelegate<T> getItemViewDelegate(T entity, int position) {

    int itemViewDelegateCount = mItemViewDelegateSparseArray.size();

    for (int i = itemViewDelegateCount - 1; i >= 0; i--) {

      ItemViewDelegate<T> itemViewDelegate = mItemViewDelegateSparseArray.valueAt(i);

      if (itemViewDelegate.isForViewType(entity, position)) {

        return itemViewDelegate;
      }
    }

    throw new IllegalArgumentException(
        "No ItemViewDelegate added that matches position=" + position + " in data source");
  }
}

