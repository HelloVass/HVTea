package info.hellovass.hv_tea.pullrecycler.base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import info.hellovass.hv_tea.adapter.recyclerview.ViewHolder;

/**
 * Created by hello on 2017/3/11.
 */

public class LoadMoreAdapterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int ITEM_TYPE_LOAD_MORE = 23333;

  private RecyclerView.Adapter mAdapter;

  private View mLoadMoreView;

  public LoadMoreAdapterWrapper(RecyclerView.Adapter adapter) {

    mAdapter = adapter;
  }

  public LoadMoreAdapterWrapper setLoadMoreView(View view) {

    if (view == null) {

      throw new IllegalArgumentException("view can't be null");
    }

    mLoadMoreView = view;
    return this;
  }

  @Override public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
    super.onViewAttachedToWindow(holder);

    if (holder.getItemViewType() == ITEM_TYPE_LOAD_MORE) {

      ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();

      if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {

        StaggeredGridLayoutManager.LayoutParams staggeredGridLayoutParams =
            (StaggeredGridLayoutManager.LayoutParams) layoutParams;

        staggeredGridLayoutParams.setFullSpan(true);
      }
    }
  }

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);

    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

    if (layoutManager instanceof GridLayoutManager) {

      final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;

      gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

        @Override public int getSpanSize(int position) {

          if (getItemViewType(position) == ITEM_TYPE_LOAD_MORE) {

            return gridLayoutManager.getSpanCount();
          }

          return 1;
        }
      });
    }
  }

  @Override public int getItemViewType(int position) {

    if (isForLoadMoreViewType(position)) {

      return ITEM_TYPE_LOAD_MORE;
    }

    return mAdapter.getItemViewType(position);
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    if (viewType == ITEM_TYPE_LOAD_MORE) {

      return ViewHolder.create(parent.getContext(), parent, mLoadMoreView);
    }

    return mAdapter.onCreateViewHolder(parent, viewType);
  }

  @SuppressWarnings("unchecked") @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    int viewType = holder.getItemViewType();

    if (viewType == ITEM_TYPE_LOAD_MORE) {

      return;
    }

    mAdapter.onBindViewHolder(holder, position);
  }

  @Override public int getItemCount() {

    return mAdapter.getItemCount() + getLoadMoreViewCount();
  }

  private int getLoadMoreViewCount() {

    return loadMoreViewExist() ? 1 : 0;
  }

  private boolean isForLoadMoreViewType(int position) {

    return loadMoreViewExist() && (position >= mAdapter.getItemCount());
  }

  private boolean loadMoreViewExist() {

    return mLoadMoreView != null;
  }
}
