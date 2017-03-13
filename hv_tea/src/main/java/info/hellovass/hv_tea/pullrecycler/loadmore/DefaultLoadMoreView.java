package info.hellovass.hv_tea.pullrecycler.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import info.hellovass.hv_tea.R;

/**
 * Created by hellovass on 16/11/20.
 */

public class DefaultLoadMoreView extends RelativeLayout implements ILoadMoreUIHandler {

  private TextView mLoadingTextView;

  public DefaultLoadMoreView(Context context) {
    this(context, null);
  }

  public DefaultLoadMoreView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {

    View.inflate(context, R.layout.footer_loadmore_default, this);

    mLoadingTextView = (TextView) findViewById(R.id.tv_loading);
  }

  @Override public View getConvertView() {

    return this;
  }

  @Override public void onLoading() {

    setVisibility(VISIBLE);
    mLoadingTextView.setText("加载中...");
  }

  @Override public void onLoadSucceed(boolean hasMore) {

    if (!hasMore) {

      setVisibility(VISIBLE);
      mLoadingTextView.setText("没有更多数据啦");
    } else {

      setVisibility(GONE);
    }
  }

  @Override public void onLoadFailed(String errorMsg) {

    setVisibility(VISIBLE);
    mLoadingTextView.setText(errorMsg);
  }
}
