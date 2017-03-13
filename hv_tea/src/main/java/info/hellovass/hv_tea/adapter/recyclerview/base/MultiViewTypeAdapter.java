package info.hellovass.hv_tea.adapter.recyclerview.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import info.hellovass.hv_tea.adapter.recyclerview.ItemViewDelegateManager;
import info.hellovass.hv_tea.adapter.recyclerview.ViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2017/2/27.
 */

public abstract class MultiViewTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

  private Context mContext;

  private List<T> mDataList;

  protected ItemViewDelegateManager<T> mItemViewDelegateManager;

  protected OnItemClickListener<T> mOnItemClickListener;

  protected OnItemLongClickListener<T> mOnItemLongClickListener;

  public MultiViewTypeAdapter(Context context, List<T> dataList) {
    mContext = context;
    mDataList = dataList == null ? new ArrayList<T>() : dataList;
    mItemViewDelegateManager = new ItemViewDelegateManager<>();
  }

  @Override public int getItemViewType(int position) {

    if (!enableItemViewDelegateManager()) {
      return super.getItemViewType(position);
    }

    return mItemViewDelegateManager.getItemViewType(mDataList.get(position), position);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ItemViewDelegate<T> itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);

    int layoutResId = itemViewDelegate.provideItemViewLayoutResId();
    ViewHolder holder = ViewHolder.create(mContext, parent, layoutResId);

    onViewHolderCreated(holder, holder.getConvertView());

    setListener(parent, holder, viewType);

    return holder;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {

    convert(holder, mDataList.get(position));
  }

  public void onViewHolderCreated(ViewHolder holder, View convertView) {

  }

  public void convert(ViewHolder holder, T entity) {

    mItemViewDelegateManager.convert(holder, entity, holder.getAdapterPosition());
  }

  public MultiViewTypeAdapter<T> addItemViewDelegate(ItemViewDelegate<T> delegate) {

    mItemViewDelegateManager.addItemViewDelegate(delegate);
    return this;
  }

  public MultiViewTypeAdapter<T> addItemViewDelegate(int viewType, ItemViewDelegate<T> delegate) {

    mItemViewDelegateManager.addItemViewDelegate(viewType, delegate);
    return this;
  }

  @Override public int getItemCount() {

    return mDataList.size();
  }

  private void setListener(ViewGroup parent, final ViewHolder holder, int viewType) {

    holder.getConvertView().setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {

        if (mOnItemClickListener != null) {

          int position = holder.getAdapterPosition();
          mOnItemClickListener.onItemClick(v, mDataList.get(position), position);
        }
      }
    });

    holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {

      @Override public boolean onLongClick(View v) {

        if (mOnItemLongClickListener != null) {

          int position = holder.getAdapterPosition();
          return mOnItemLongClickListener.onItemOnLongClick(v, mDataList.get(position), position);
        }

        return false;
      }
    });
  }

  private boolean enableItemViewDelegateManager() {

    return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
  }

  public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }

  public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
    mOnItemLongClickListener = onItemLongClickListener;
  }

  public interface OnItemClickListener<T> {

    void onItemClick(View view, T entity, int position);
  }

  public interface OnItemLongClickListener<T> {

    boolean onItemOnLongClick(View view, T entity, int position);
  }
}
