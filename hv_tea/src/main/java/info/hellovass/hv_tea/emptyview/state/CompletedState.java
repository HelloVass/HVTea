package info.hellovass.hv_tea.emptyview.state;

import android.support.annotation.LayoutRes;
import android.view.View;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/3/11.
 */

public class CompletedState extends BaseState {

  public CompletedState(ViewHolder viewHolder) {
    super(viewHolder);
  }

  @Override public void onLoading() {

  }

  @Override public void onError(@LayoutRes int imgResId, String errorMsg) {

  }

  @Override public void onCompleted() {

    mViewHolder.getConvertView().setVisibility(View.GONE);
  }
}
