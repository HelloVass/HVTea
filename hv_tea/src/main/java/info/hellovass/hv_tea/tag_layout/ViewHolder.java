package info.hellovass.hv_tea.tag_layout;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hello on 2016/12/26.
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

  public ViewHolder setText(int viewId, String text) {
    TextView textView = getView(viewId);
    textView.setText(text);
    return this;
  }

  @SuppressWarnings("unchecked") private <V extends View> V getView(int viewId) {

    View view = mViewSparseArray.get(viewId);

    if (view == null) {
      view = mConvertView.findViewById(viewId);
      mViewSparseArray.put(viewId, view);
    }

    return (V) view;
  }
}
