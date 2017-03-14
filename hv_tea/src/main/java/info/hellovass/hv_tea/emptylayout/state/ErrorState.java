package info.hellovass.hv_tea.emptylayout.state;

import android.view.View;
import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/3/11.
 *
 * 错误状态
 */

public class ErrorState extends BaseState {

  public ErrorState(ViewHolder viewHolder) {
    super(viewHolder);
  }

  @Override public void idle() {

  }

  @Override public void onLoading() {

  }

  @Override public void onError(int imgResId, String errorMsg) {

    mViewHolder.getConvertView().setVisibility(View.VISIBLE);

    mViewHolder.ifVisible(R.id.iv_img, true);
    mViewHolder.setImageResource(R.id.iv_img, imgResId);

    mViewHolder.ifVisible(R.id.pb_progressbar, false);

    mViewHolder.ifVisible(R.id.tv_title, true);
    mViewHolder.setText(R.id.tv_title, errorMsg);
  }

  @Override public boolean shouldReload() {

    return true;
  }

  @Override public void onCompleted() {

  }
}
