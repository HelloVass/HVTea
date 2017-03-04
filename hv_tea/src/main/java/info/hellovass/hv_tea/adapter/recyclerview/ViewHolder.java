package info.hellovass.hv_tea.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hello on 2017/2/27.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

  private Context mContext;

  private View mConvertView;

  private SparseArray<View> mViewSparseArray;

  private ViewHolder(Context context, View itemView) {
    super(itemView);

    mContext = context;
    mConvertView = itemView;
    mViewSparseArray = new SparseArray<>();
  }

  public View getConvertView() {

    return mConvertView;
  }

  public static ViewHolder create(Context context, ViewGroup parent, int layoutResId) {

    View itemView = LayoutInflater.from(context).inflate(layoutResId, parent, false);
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

  public ViewHolder setText(int viewId,String text){

    TextView textView = getView(viewId);
    textView.setText(text);
    return this;
  }

  public ViewHolder setTextColor(int viewId,int textColor){

    TextView textView = getView(viewId);
    textView.setTextColor(textColor);
    return this;
  }

  public ViewHolder setImageResource(int viewId,int resId){

    ImageView imageView = getView(viewId);
    imageView.setImageResource(resId);
    return this;
  }

  public ViewHolder setBackgroundColor(int viewId,int color){

    View view = getView(viewId);
    view.setBackgroundColor(color);
    return this;
  }
}
