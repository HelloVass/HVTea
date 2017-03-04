package info.hellovass.hv_tea.adapter.viewgroup;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hello on 2017/2/28.
 */

public final class ViewHolder {

  private Context mContext;

  private View mConvertView;

  private SparseArray<View> mViewSparseArray;

  private ViewHolder(Context context, View itemView) {
    mContext = context;
    mConvertView = itemView;
    mViewSparseArray = new SparseArray<>();
  }

  public static ViewHolder create(Context context, View itemView) {

    return new ViewHolder(context, itemView);
  }

  @SuppressWarnings("unchecked") public <T extends View> T getView(int viewId) {

    View view = mViewSparseArray.get(viewId);

    if (view == null) {

      view = mConvertView.findViewById(viewId);
      mViewSparseArray.put(viewId, view);
    }

    return (T) view;
  }

  public ViewHolder setText(int resId, CharSequence text) {

    TextView textView = getView(resId);
    textView.setText(text);
    return this;
  }

  public View getConvertView() {

    return mConvertView;
  }
}
