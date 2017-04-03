package info.hellovass.hv_tea.emptylayout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;
import info.hellovass.hv_tea.emptylayout.state.SucceedState;
import info.hellovass.hv_tea.emptylayout.state.ErrorState;
import info.hellovass.hv_tea.emptylayout.state.IdleState;
import info.hellovass.hv_tea.emptylayout.state.LoadingState;

/**
 * Created by hello on 2017/3/11.
 *
 * 处理各种状态的组件
 */

public class EmptyLayout extends LinearLayout implements View.OnClickListener {

  private State mIdleState;

  private State mLoadingState;

  private State mSucceedState;

  private State mErrorState;

  private State mCurrentState;

  private OnRetryCallback mOnRetryCallback;

  public EmptyLayout(@NonNull Context context) {
    this(context, null);
  }

  public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {

    ViewHolder holder =
        ViewHolder.create(context, View.inflate(context, R.layout.layout_empty, this));

    mIdleState = new IdleState(holder);
    mLoadingState = new LoadingState(holder);
    mSucceedState = new SucceedState(holder);
    mErrorState = new ErrorState(holder);

    setOnClickListener(this); // 设置点击回调

    reset(); // 重置
  }

  public void setCurrentState(State currentState) {

    mCurrentState = currentState;
  }

  public void setOnRetryCallback(OnRetryCallback onRetryCallback) {

    mOnRetryCallback = onRetryCallback;
  }

  public void reset() {

    setCurrentState(mIdleState);
    mCurrentState.reset();
  }

  public void onLoading() {

    setCurrentState(mLoadingState);
    mCurrentState.onLoading();
  }

  public void onSucceed() {

    setCurrentState(mSucceedState);
    mCurrentState.onSucceed();
  }

  public void onError(@DrawableRes int imageResId, String errorMsg) {

    setCurrentState(mErrorState);
    mCurrentState.onError(imageResId, errorMsg);
  }

  @Override public void onClick(View v) {

    // 只有“当前状态允许转换到 loading 状态”并且 OnReloadListener 不为空，才可以执行 onReload 方法
    if (mCurrentState != null && mCurrentState.canRetry() && mOnRetryCallback != null) {

      mOnRetryCallback.onRetry();
    }
  }
  public interface State {

    /**
     * 重置，进入 idle 状态
     */
    void reset();

    /**
     * 加载中
     */
    void onLoading();

    /**
     * 发生错误
     *
     * @param imgResId 图片资源 Id
     * @param errorMsg 抛给用户的错误信息
     */
    void onError(@DrawableRes int imgResId, String errorMsg);

    /**
     * 是否允许点击重试
     *
     * @return true 表示可以
     */
    boolean canRetry();

    /**
     * 加载成功
     */
    void onSucceed();
  }
}
