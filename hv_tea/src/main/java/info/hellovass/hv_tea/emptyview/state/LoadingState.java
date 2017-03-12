package info.hellovass.hv_tea.emptyview.state;

import android.support.annotation.LayoutRes;
import android.view.View;
import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/3/11.
 */

public class LoadingState extends BaseState {

  public LoadingState(ViewHolder viewHolder) {
    super(viewHolder);
  }

  @Override public void onLoading() {

    mViewHolder.getConvertView().setVisibility(View.VISIBLE);
    mViewHolder.setText(R.id.tv_title, "加载中...");
  }

  @Override public void onError(@LayoutRes int imgResId, String errorMsg) {

  }

  @Override public void onCompleted() {

  }
}
