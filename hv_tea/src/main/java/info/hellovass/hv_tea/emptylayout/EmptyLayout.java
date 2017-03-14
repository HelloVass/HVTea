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

    mLoadingState = new LoadingState(holder);
    mCompletedState = new CompletedState(holder);
    mErrorState = new ErrorState(holder);

    mCurrentState = new IdleState(holder); // 设置初始化状态
    mCurrentState.idle();

    setOnClickListener(this);
  }

  public void setCurrentState(State currentState) {

    mCurrentState = currentState;
  }

  public void setOnReloadListener(OnReloadListener onReloadListener) {

    if (onReloadListener == null) {

      throw new IllegalArgumentException("onReloadListener can't be null");
    }

    mOnReloadListener = onReloadListener;
  }

  public void onLoading() {

    setCurrentState(mLoadingState);
    mCurrentState.onLoading();
  }

  public void onCompleted() {

    setCurrentState(mCompletedState);
    mCompletedState.onCompleted();
  }

  public void onError(int imageResId, String errorMsg) {

    setCurrentState(mErrorState);
    mErrorState.onError(imageResId, errorMsg);
  }

  @Override public void onClick(View v) {

    // 只有“当前状态允许转换到 loading 状态”并且 OnReloadListener 不为空，才可以执行 onReload 方法
    if (mCurrentState.shouldReload() && mOnReloadListener != null) {

      mOnReloadListener.onReload();
    }
  }

  private void release() {

    mLoadingState = null;
    mCompletedState = null;
    mErrorState = null;
    mCurrentState = null;
    mOnReloadListener = null;
    setOnClickListener(null);
  }

  public interface OnReloadListener {

    void onReload();
  }

  public interface State {

    void idle();

    void onLoading();

    void onError(int imgResId, String errorMsg);

    /**
     * 当前状态是否可以进入 loading 状态
     *
     * @return true 表示可以，false 则不能
     */
    boolean shouldReload();

    void onCompleted();
  }
}
