package info.hellovass.hv_tea.emptylayout.state;

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

  @Override public void idle() {

  }

  @Override public void onLoading() {

    mViewHolder.getConvertView().setVisibility(View.VISIBLE);

    mViewHolder.ifVisible(R.id.iv_img, false);
    mViewHolder.ifVisible(R.id.pb_progressbar, true);
    mViewHolder.setText(R.id.tv_title, "加载中...");
  }

  @Override public void onError(int imgResId, String errorMsg) {

  }

  @Override public boolean shouldReload() {

    return false;
  }

  @Override public void onCompleted() {

  }
}
