package info.hellovass.hv_tea.emptylayout.state;

import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/3/13.
 *
 * 闲置状态
 */

public class IdleState extends BaseState {

  public IdleState(ViewHolder viewHolder) {
    super(viewHolder);
  }

  @Override public void reset() {

    ViewHolder viewHolder = mViewHolderWeakReference.get();

    if (viewHolder == null) {

      return;
    }

    viewHolder.ifVisible(R.id.iv_img, false);
    viewHolder.ifVisible(R.id.pb_progressbar, false);
    viewHolder.ifVisible(R.id.tv_title, false);
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
