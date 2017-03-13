package info.hellovass.hv_tea.adapter.recyclerview.wrapper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import info.hellovass.hv_tea.adapter.recyclerview.ViewHolder;

/**
 * Created by hello on 2017/3/13.
 */

public class HeaderAndFooterAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int ITEM_TYPE_HEADER_OFFSET = 100000;

  private static final int ITEM_TYPE_FOOTER_OFFSET = 200000;

  private SparseArray<View> mHeaderViewMap = new SparseArray<>();

  private SparseArray<View> mFooterViewMap = new SparseArray<>();

  private RecyclerView.Adapter mAdapter;

  public HeaderAndFooterAdapterWrapper(RecyclerView.Adapter adapter) {

    mAdapter = adapter;
  }

  public void addHeaderView(View view) {

    mHeaderViewMap.put(mHeaderViewMap.size() + ITEM_TYPE_HEADER_OFFSET, view);
  }

  public void addFooterView(View view) {

    mFooterViewMap.put(mFooterViewMap.size() + ITEM_TYPE_FOOTER_OFFSET, view);
  }

  @SuppressWarnings("unchecked") @Override
  public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
    super.onViewAttachedToWindow(holder);

    mAdapter.onViewAttachedToWindow(holder);

    int position = holder.getLayoutPosition();

    if (isHeaderView(position) || isFooterView(position)) {

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

    mAdapter.onAttachedToRecyclerView(recyclerView);

    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

    if (layoutManager instanceof GridLayoutManager) {

      final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;

      gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

        @Override public int getSpanSize(int position) {

          if (isHeaderView(position) || isFooterView(position)) {

            return gridLayoutManager.getSpanCount();
          }

          return 1;
        }
      });
    }
  }

  @Override public int getItemViewType(int position) {

    if (isHeaderView(position)) {

      return mHeaderViewMap.keyAt(position);
    } else if (isFooterView(position)) {

      return mFooterViewMap.keyAt(position - getHeaderViewCount() - getRealItemCount());
    }

    return super.getItemViewType(position - getHeaderViewCount());
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    if (mHeaderViewMap.get(viewType) != null) {

      return ViewHolder.create(parent.getContext(), parent, mHeaderViewMap.get(viewType));
    } else if (mFooterViewMap.get(viewType) != null) {

      return ViewHolder.create(parent.getContext(), parent, mFooterViewMap.get(viewType));
    }

    return mAdapter.onCreateViewHolder(parent, viewType);
  }

  @SuppressWarnings("unchecked") @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    if (isHeaderView(position)) {

      return;
    }

    if (isFooterView(position)) {

      return;
    }

    mAdapter.onBindViewHolder(holder, position);
  }

  @Override public int getItemCount() {

    return getHeaderViewCount() + getFooterViewCount() + getRealItemCount();
  }

  private int getHeaderViewCount() {

    return mHeaderViewMap.size();
  }

  private int getFooterViewCount() {

    return mFooterViewMap.size();
  }

  private int getRealItemCount() {

    return mAdapter.getItemCount();
  }

  private boolean isFooterView(int position) {

    return position >= getHeaderViewCount() + getRealItemCount();
  }

  private boolean isHeaderView(int position) {

    return position < getHeaderViewCount();
  }
}
