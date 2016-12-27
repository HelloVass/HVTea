package info.hellovass.hv_tea.nine_grid;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by hello on 2016/12/27.
 */

public final class ViewHolder {

  private final SparseArray<View> mViewSparseArray;

  private final View mConvertView;

  private ViewHolder(View convertView) {
    mViewSparseArray = new SparseArray<>();
    mConvertView = convertView;
  }

  static ViewHolder get(View convertView) {
    return new ViewHolder(convertView);
  }

  @SuppressWarnings("unchecked") public  <V extends View> V getView(int viewId) {

    View view = mViewSparseArray.get(viewId);

    if (view == null) {
      view = mConvertView.findViewById(viewId);
      mViewSparseArray.put(viewId, view);
    }

    return (V) view;
  }
}
