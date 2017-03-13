package info.hellovass.hv_tea.adapter.viewgroup.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import info.hellovass.hv_tea.adapter.recyclerview.base.MultiViewTypeAdapter;
import info.hellovass.hv_tea.adapter.viewgroup.ItemViewDelegateManager;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2017/2/28.
 */

public abstract class MultiItemTypeAdapter<T> implements ViewGroupAdapter {

  protected Context mContext;

  protected List<T> mDataList;

  private ItemViewDelegateManager<T> mItemViewDelegateManager;

  protected MultiViewTypeAdapter.OnItemClickListener mOnItemClickListener;

  protected MultiItemTypeAdapter.OnItemLongClickListener mOnItemLongClickListener;

  public MultiItemTypeAdapter(Context context, List<T> dataList) {
    mContext = context;
    mDataList = dataList == null ? new ArrayList<T>() : dataList;
    mItemViewDelegateManager = new ItemViewDelegateManager<>();
  }

  public MultiItemTypeAdapter<T> addItemViewDelegate(ItemViewDelegate<T> delegate) {

    mItemViewDelegateManager.addDelegate(delegate);
    return this;
  }

  @Override public View getView(ViewGroup viewGroup, int position) {

    ItemViewDelegate<T> itemViewDelegate =
        mItemViewDelegateManager.getItemViewDelegate(mDataList.get(position), position);

    int layoutResId = itemViewDelegate.provideItemViewLayoutResId();
    View itemView = LayoutInflater.from(mContext).inflate(layoutResId, viewGroup, false);
    ViewHolder holder = ViewHolder.create(mContext, itemView);
    convert(holder, mDataList.get(position), position);

    return holder.getConvertView();
  }

  protected void convert(ViewHolder holder, T entity, int position) {

    mItemViewDelegateManager.convert(holder, entity, position);
  }

  @Override public int getCount() {

    return mDataList.size();
  }

  public void setOnItemClickListener(MultiViewTypeAdapter.OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }

  public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
    mOnItemLongClickListener = onItemLongClickListener;
  }

  private boolean enableItemViewDelegateManager() {

    return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
  }

  public interface OnItemClickListener {

    void onItemClick(ViewGroup parent, View view, int position);
  }

  public interface OnItemLongClickListener {

    boolean onItemLongClick(ViewGroup parent, View view, int position);
  }
}
