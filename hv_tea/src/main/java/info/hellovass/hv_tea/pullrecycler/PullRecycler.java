package info.hellovass.hv_tea.pullrecycler;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.pullrecycler.layoutmanager.ILayoutManager;

/**
 * Created by hello on 2017/3/10.
 */

public class PullRecycler extends FrameLayout {

  private SwipeRefreshLayout mRefreshLayout;

  private RecyclerView mRecyclerView;

  private ILayoutManager mLayoutManager;

  private boolean mEnableLoadMore = false;

  private boolean mEnableRefresh = false;

  private boolean mIsLoading = false;

  private boolean mLoadError = false;

  private int mTouchSlop = 0;

  private int mYDown = 0;

  private int mLastY = 0;

  private RecyclerView.OnScrollListener mOnReachBottomListener =
      new RecyclerView.OnScrollListener() {

        @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
          super.onScrollStateChanged(recyclerView, newState);

          int totalItemCount = mLayoutManager.fetchItemCount();
          int lastVisibleItemPosition = mLayoutManager.fetchLastVisibleItemPosition();

          if (isPullUp()
              && newState == RecyclerView.SCROLL_STATE_IDLE
              && mEnableLoadMore
              && lastVisibleItemPosition >= totalItemCount - 1) {

            onReachBottom();
          }
        }
      };

  public PullRecycler(@NonNull Context context) {
    this(context, null);
  }

  public PullRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PullRecycler(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    init(context, attrs);
  }

  public void enableLoadMore(boolean enableLoadMore) {
    mEnableLoadMore = enableLoadMore;
  }

  public void setEnableRefresh(boolean enableRefresh) {
    mEnableRefresh = enableRefresh;
    mRefreshLayout.setEnabled(enableRefresh);
  }

  public void setLayoutManager(ILayoutManager layoutManager) {
    mLayoutManager = layoutManager;
    mRecyclerView.setLayoutManager(layoutManager.getLayoutManager());
  }

  @Override public boolean dispatchTouchEvent(MotionEvent ev) {

    int action = ev.getAction();

    switch (action) {

      case MotionEvent.ACTION_DOWN:
        mYDown = (int) ev.getRawY();
        break;

      case MotionEvent.ACTION_MOVE:
        mLastY = (int) ev.getRawY();
        break;

      default:
        break;
    }

    return super.dispatchTouchEvent(ev);
  }

  private void init(Context context, AttributeSet attrs) {

    mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    View.inflate(context, R.layout.layout_pull_recycler, this);

    mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_Layout);
    mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

    mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

      @Override public void onRefresh() {

      }
    });

    mRecyclerView.addOnScrollListener(mOnReachBottomListener);
  }

  /**
   * 是否为上拉操作
   *
   * @return true 为上拉
   */
  private boolean isPullUp() {

    return mYDown - mLastY >= mTouchSlop;
  }

  private void onReachBottom() {

  }
}
