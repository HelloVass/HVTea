package info.hellovass.hv_tea.emptylayout.state;

import android.support.annotation.DrawableRes;
import android.view.View;
import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/3/13.
 *
 * 闲置状态
 */

public class IdleState extends AbsState {

  public IdleState(ViewHolder viewHolder) {
    super(viewHolder);
  }

  @Override public void reset() {

    mViewHolder.getConvertView().setVisibility(View.GONE); // 隐藏

    mViewHolder.ifVisible(R.id.iv_img, false); // 隐藏 ImageView
    mViewHolder.ifVisible(R.id.pb_progressbar, false); // 隐藏进度条
    mViewHolder.ifVisible(R.id.tv_title, false); // 隐藏标题
  }

  @Override public void onLoading() {

  }

  @Override public void onError(@DrawableRes int imgResId, String errorMsg) {

  }

  @Override public boolean canRetry() {

    return false;
  }

  @Override public void onSucceed() {

  }
}
