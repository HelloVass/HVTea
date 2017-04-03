package info.hellovass.hv_tea.emptylayout.state;

import android.support.annotation.DrawableRes;
import android.view.View;
import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/3/11.
 *
 * 加载中状态
 */

public class LoadingState extends AbsState {

  public LoadingState(ViewHolder viewHolder) {
    super(viewHolder);
  }

  @Override public void reset() {

  }

  @Override public void onLoading() {

    mViewHolder.getConvertView().setVisibility(View.VISIBLE);

    mViewHolder.ifVisible(R.id.iv_img, false);
    mViewHolder.ifVisible(R.id.pb_progressbar, true);
    mViewHolder.ifVisible(R.id.tv_title, true);
    mViewHolder.setText(R.id.tv_title, "加载中...");
  }

  @Override public void onError(@DrawableRes int imgResId, String errorMsg) {

  }

  @Override public boolean canRetry() {

    return false;
  }

  @Override public void onSucceed() {

  }
}
