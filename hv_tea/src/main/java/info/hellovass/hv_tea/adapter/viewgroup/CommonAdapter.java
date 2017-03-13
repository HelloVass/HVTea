package info.hellovass.hv_tea.adapter.viewgroup;

import android.content.Context;
import android.view.LayoutInflater;
import info.hellovass.hv_tea.adapter.viewgroup.base.ItemViewDelegate;
import info.hellovass.hv_tea.adapter.viewgroup.base.MultiItemTypeAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2017/3/3.
 */

public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

  protected Context mContext;

  protected int mLayoutResId;

  protected List<T> mDataList;

  protected LayoutInflater mLayoutInflater;

  public CommonAdapter(Context context, int layoutResId, List<T> dataList) {
    super(context, dataList);

    mContext = context;
    mLayoutResId = layoutResId;
    mLayoutInflater = LayoutInflater.from(mContext);
    mDataList = dataList == null ? new ArrayList<T>() : dataList;

    addItemViewDelegate(provideDefaultItemViewDelegate());
  }

  private ItemViewDelegate<T> provideDefaultItemViewDelegate() {

    return new ItemViewDelegate<T>() {

      @Override public int provideItemViewLayoutResId() {

        return mLayoutResId;
      }

      @Override public boolean isForViewType(T entity, int position) {

        return true;
      }

      @Override public void convert(ViewHolder holder, T entity, int position) {

        CommonAdapter.this.convert(holder, entity, position);
      }
    };
  }

  protected abstract void convert(ViewHolder holder, T entity, int position);
}
