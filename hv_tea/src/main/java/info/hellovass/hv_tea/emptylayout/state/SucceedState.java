package info.hellovass.hv_tea.emptylayout.state;

import android.support.annotation.DrawableRes;
import android.view.View;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/3/11.
 *
 * 完成状态
 */

public class SucceedState extends AbsState {

  public SucceedState(ViewHolder viewHolder) {
    super(viewHolder);
  }

  @Override public void reset() {

  }

  @Override public void onLoading() {

  }

  @Override public void onError(@DrawableRes int imgResId, String errorMsg) {

  }

  @Override public boolean canRetry() {

    return false;
  }

  @Override public void onSucceed() {

    mViewHolder.getConvertView().setVisibility(View.GONE);
  }
}
