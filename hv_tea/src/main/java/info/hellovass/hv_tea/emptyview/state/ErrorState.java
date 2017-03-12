package info.hellovass.hv_tea.emptyview.state;

import android.view.View;
import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;

/**
 * Created by hello on 2017/3/11.
 */

public class ErrorState extends BaseState {

  public ErrorState(ViewHolder viewHolder) {
    super(viewHolder);
  }

  @Override public void onLoading() {

  }

  @Override public void onError(int imgResId, String errorMsg) {

    mViewHolder.getConvertView().setVisibility(View.VISIBLE);
    mViewHolder.setImageResource(R.id.iv_img, imgResId);
    mViewHolder.setText(R.id.tv_title, errorMsg);
  }

  @Override public void onCompleted() {

  }
}
