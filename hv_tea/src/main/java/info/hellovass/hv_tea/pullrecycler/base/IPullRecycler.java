package info.hellovass.hv_tea.pullrecycler.base;

import info.hellovass.hv_tea.pullrecycler.loadmore.ILoadMoreHandler;
import info.hellovass.hv_tea.pullrecycler.loadmore.ILoadMoreUIHandler;
import info.hellovass.hv_tea.pullrecycler.refresh.IRefreshHandler;

/**
 * Created by hello on 2017/3/11.
 */

public interface IPullRecycler {

  void onRefreshCompleted();

  void onLoadMoreSucceed(boolean hasMore);

  void onLoadMoreFailed(String errorMsg);

  interface IHandlerProvider {

    void setLoadMoreUIHandler(ILoadMoreUIHandler loadMoreUIHandler);

    void setLoadMoreHandler(ILoadMoreHandler loadMoreHandler);

    void setRefreshHandler(IRefreshHandler refreshHandler);
  }
}
