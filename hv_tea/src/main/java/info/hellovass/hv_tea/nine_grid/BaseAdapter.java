package info.hellovass.hv_tea.nine_grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2016/12/19.
 */

public abstract class BaseAdapter<DATA extends Multiply> {

  private Context mContext;

  private List<DATA> mDataList;

  private LayoutInflater mLayoutInflater;

  public BaseAdapter(Context context, List<DATA> dataList) {
    mContext = context;
    mDataList = dataList == null ? new ArrayList<DATA>() : dataList;
    mLayoutInflater = LayoutInflater.from(context);
  }

  public List<DATA> getDataList() {
    return mDataList;
  }

  public int getCount() {
    return mDataList.size();
  }

  public Context getContext() {
    return mContext;
  }

  public View getView(ViewGroup viewGroup, int position) {

    int itemLayoutResId = getDataList().get(position).provideItemLayoutResId();

    return mLayoutInflater.inflate(itemLayoutResId, viewGroup, false);
  }

  public void convert(ViewHolder holder, int position) {
    convert(holder, position, getDataList().get(position));
  }

  public abstract void convert(ViewHolder holder, int position, DATA data);
}
