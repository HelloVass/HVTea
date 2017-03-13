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
 */

public class EmptyLayout extends LinearLayout implements View.OnClickListener {

  private State mLoadingState;

  private State mErrorState;

  private State mCompletedState;

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

  private void init(Context context, AttributeSet attrs) {

    ViewHolder holder =
        ViewHolder.create(context, View.inflate(context, R.layout.layout_empty, this));

    mLoadingState = new LoadingState(holder);
    mCompletedState = new CompletedState(holder);
    mErrorState = new ErrorState(holder, mOnReloadListener);

    // 设置初始化状态
    mCurrentState = new IdleState(holder);
    mCurrentState.idle();

    setOnClickListener(this);
  }

  public void setCurrentState(State currentState) {

    mCurrentState = currentState;
  }

  public void setOnReloadListener(OnReloadListener onReloadListener) {
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

    if (!mCurrentState.shouldReload()) {

      return;
    }

    if (mOnReloadListener != null) {

      mOnReloadListener.onReload();
    }
  }

  public interface OnReloadListener {

    void onReload();
  }

  public interface State {

    void idle();

    void onLoading();

    void onError(int imgResId, String errorMsg);

    boolean shouldReload();

    void onCompleted();
  }
}
