package info.hellovass.hv_tea.emptylayout.state;

import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/3/13.
 */

public class IdleState extends BaseState {

  public IdleState(ViewHolder viewHolder) {
    super(viewHolder);
  }

  @Override public void idle() {

    mViewHolder.ifVisible(R.id.iv_img, false);
    mViewHolder.ifVisible(R.id.pb_progressbar, false);
    mViewHolder.ifVisible(R.id.tv_title, false);
  }

  @Override public void onLoading() {

  }

  @Override public void onError(int imgResId, String errorMsg) {

  }

  @Override public boolean shouldReload() {

    return false;
  }

  @Override public void onCompleted() {

  }
}
