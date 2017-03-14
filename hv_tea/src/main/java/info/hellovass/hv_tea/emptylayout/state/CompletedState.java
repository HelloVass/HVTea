package info.hellovass.hv_tea.emptylayout.state;

import android.view.View;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/3/11.
 *
 * 完成状态
 */

public class CompletedState extends BaseState {

  public CompletedState(ViewHolder viewHolder) {
    super(viewHolder);
  }

  @Override public void reset() {

  }

  @Override public void onLoading() {

  }

  @Override public void onError(int imgResId, String errorMsg) {

  }

  @Override public boolean shouldReload() {

    return false;
  }

  @Override public void onCompleted() {

    ViewHolder viewHolder = mViewHolderWeakReference.get();

    if (viewHolder == null) {

      return;
    }

    viewHolder.getConvertView().setVisibility(View.GONE);
  }
}
