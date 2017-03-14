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

  @Override public void reset() {

  }

  @Override public void onLoading() {

  }

  @Override public void onError(int imgResId, String errorMsg) {

    ViewHolder viewHolder = mViewHolderWeakReference.get();

    if (viewHolder == null) {

      return;
    }

    viewHolder.getConvertView().setVisibility(View.VISIBLE);

    viewHolder.ifVisible(R.id.iv_img, true);
    viewHolder.setImageResource(R.id.iv_img, imgResId);

    viewHolder.ifVisible(R.id.pb_progressbar, false);

    viewHolder.ifVisible(R.id.tv_title, true);
    viewHolder.setText(R.id.tv_title, errorMsg);
  }

  @Override public boolean shouldReload() {

    return true;
  }

  @Override public void onCompleted() {

  }
}
