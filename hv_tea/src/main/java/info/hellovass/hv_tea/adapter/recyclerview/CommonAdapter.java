package info.hellovass.hv_tea.adapter.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import info.hellovass.hv_tea.adapter.recyclerview.base.ItemViewDelegate;
import info.hellovass.hv_tea.adapter.recyclerview.base.MultiViewTypeAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2017/2/27.
 */

public abstract class CommonAdapter<T> extends MultiViewTypeAdapter<T> {

  protected Context mContext;

  protected int mLayoutResId;

  protected List<T> mDataList;

  protected LayoutInflater mLayoutInflater;

  public CommonAdapter(Context context, int layoutResId, List<T> dataList) {
    super(context, dataList);

    mContext = context;
    mLayoutResId = layoutResId;
    mLayoutInflater = LayoutInflater.from(context);
    mDataList = dataList == null ? new ArrayList<T>() : dataList;

    addItemViewDelegate(provideDefaultItemViewDelegate());
  }

  private ItemViewDelegate<T> provideDefaultItemViewDelegate() {

    return new ItemViewDelegate<T>() {

      @Override public int provideItemViewLayoutResId() {

        return mLayoutResId;
      }

      @Override public boolean isForViewType(T item, int position) {

        return true;
      }

      @Override public void convert(ViewHolder holder, T entity, int position) {

        CommonAdapter.this.convert(holder, entity, position);
      }
    };
  }

  protected abstract void convert(ViewHolder holder, T entity, int position);
}
