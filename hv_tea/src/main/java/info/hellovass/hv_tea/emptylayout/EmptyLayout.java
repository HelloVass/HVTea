package info.hellovass.hv_tea.emptylayout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;
import info.hellovass.hv_tea.emptylayout.state.CompletedState;
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

  private State mCompletedState;

  private State mErrorState;

  private State mCurrentState;

  private OnReloadListener mOnReloadListener;

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

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();

    release();
  }

  private void init(Context context, AttributeSet attrs) {

    ViewHolder holder =
        ViewHolder.create(context, View.inflate(context, R.layout.layout_empty, this));

    setOnClickListener(this); // 设置点击回调

    mIdleState = new IdleState(holder);
    mLoadingState = new LoadingState(holder);
    mCompletedState = new CompletedState(holder);
    mErrorState = new ErrorState(holder);

    reset();
  }

  public void setCurrentState(State currentState) {

    mCurrentState = currentState;
  }

  public void setOnReloadListener(OnReloadListener onReloadListener) {

    mOnReloadListener = onReloadListener;
  }

  public void reset() {

    if (mIdleState != null) {

      setCurrentState(mIdleState);
      mCurrentState.reset();
    }
  }

  public void onLoading() {

    if (mLoadingState != null) {

      setCurrentState(mLoadingState);
      mCurrentState.onLoading();
    }
  }

  public void onCompleted() {

    if (mCompletedState != null) {

      setCurrentState(mCompletedState);
      mCurrentState.onCompleted();
    }
  }

  public void onError(int imageResId, String errorMsg) {

    if (mErrorState != null) {

      setCurrentState(mErrorState);
      mCurrentState.onError(imageResId, errorMsg);
    }
  }

  @Override public void onClick(View v) {

    // 只有“当前状态允许转换到 loading 状态”并且 OnReloadListener 不为空，才可以执行 onReload 方法
    if (mCurrentState != null && mCurrentState.shouldReload() && mOnReloadListener != null) {

      mOnReloadListener.onReload();
    }
  }

  private void release() {

    mIdleState = null;
    mLoadingState = null;
    mCompletedState = null;
    mErrorState = null;

    mCurrentState = null;

    setOnReloadListener(null);
    setOnClickListener(null);
  }

  public interface OnReloadListener {

    void onReload();
  }

  public interface State {

    /**
     * 重置 EmptyLayout，进入 Idle 状态
     */
    void reset();

    /**
     * 进入加载中状态
     */
    void onLoading();

    /**
     * 进入错误状态
     *
     * @param imgResId 图片资源 Id
     * @param errorMsg 抛给用户的错误信息
     */
    void onError(int imgResId, String errorMsg);

    /**
     * 当前状态是否可以进入 loading 状态
     *
     * @return true 表示可以，false 则不能
     */
    boolean shouldReload();

    /**
     * 加载完成状态
     */
    void onCompleted();
  }
}
