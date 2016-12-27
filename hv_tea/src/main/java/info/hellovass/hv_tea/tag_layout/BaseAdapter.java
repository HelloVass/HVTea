package info.hellovass.hv_tea.tag_layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2016/12/21.
 */

public abstract class BaseAdapter<DATA> {

  private Context mContext;

  private List<DATA> mDataList;

  private LayoutInflater mLayoutInflater;

  private int mItemLayoutResId;

  public BaseAdapter(Context context, int itemLayoutResId, List<DATA> dataList) {
    mContext = context;
    mItemLayoutResId = itemLayoutResId;
    mDataList = dataList == null ? new ArrayList<DATA>() : dataList;
    mLayoutInflater = LayoutInflater.from(context);
  }

  public List<DATA> getDataList() {
    return mDataList;
  }

  public int getCount() {
    return mDataList == null ? 0 : mDataList.size();
  }

  public View getView(ViewGroup viewGroup, int position) {
    View view = mLayoutInflater.inflate(mItemLayoutResId, viewGroup, false);
    convert(ViewHolder.get(view), position, getDataList().get(position));
    return view;
  }

  protected abstract void convert(ViewHolder holder, int position, DATA data);
}
