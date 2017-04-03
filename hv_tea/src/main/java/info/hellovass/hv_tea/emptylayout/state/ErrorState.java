package info.hellovass.hv_tea.emptylayout.state;

import android.support.annotation.DrawableRes;
import android.view.View;
import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/3/11.
 *
 * 错误状态
 */

public class ErrorState extends AbsState {

  public ErrorState(ViewHolder viewHolder) {
    super(viewHolder);
  }

  @Override public void reset() {

  }

  @Override public void onLoading() {

  }

  @Override public void onError(@DrawableRes int imgResId, String errorMsg) {

    mViewHolder.getConvertView().setVisibility(View.VISIBLE); // 显示

    mViewHolder.ifVisible(R.id.iv_img, true); // 显示 ImageView
    mViewHolder.setImageResource(R.id.iv_img, imgResId);

    mViewHolder.ifVisible(R.id.pb_progressbar, false); // 隐藏进度条

    mViewHolder.ifVisible(R.id.tv_title, true); // 显示标题
    mViewHolder.setText(R.id.tv_title, errorMsg);
  }

  @Override public boolean canRetry() {

    return true;
  }

  @Override public void onSucceed() {

  }
}
