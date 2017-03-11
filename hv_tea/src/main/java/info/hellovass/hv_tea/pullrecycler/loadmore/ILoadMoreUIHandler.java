package info.hellovass.hv_tea.pullrecycler.loadmore;

import android.view.View;

/**
 * Created by hello on 2017/3/11.
 */

public interface ILoadMoreUIHandler {


  View getConvertView();

  void onLoading();

  void onLoadSucceed(boolean hasMore);

  void onLoadFailed(String errorMsg);
}
