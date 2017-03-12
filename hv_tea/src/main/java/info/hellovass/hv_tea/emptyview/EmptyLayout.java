package info.hellovass.hv_tea.emptyview;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import info.hellovass.hv_tea.R;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;
import info.hellovass.hv_tea.emptyview.state.CompletedState;
import info.hellovass.hv_tea.emptyview.state.ErrorState;
import info.hellovass.hv_tea.emptyview.state.LoadingState;

/**
 * Created by hello on 2017/3/11.
 */

public class EmptyLayout extends LinearLayout {

  private State mLoadingState;

  private State mErrorState;

  private State mCompletedState;

  private State mCurrentState;

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
    mErrorState = new ErrorState(holder);
  }

  public void setCurrentState(State currentState) {

    mCurrentState = currentState;
  }

  public void onLoading() {

    setCurrentState(mLoadingState);
    mCurrentState.onLoading();
  }

  public void onCompleted(int imgResId, String errorMsg) {

    setCurrentState(mCompletedState);
    mCompletedState.onCompleted();
  }

  public void onError(int imageResId, String errorMsg) {

    setCurrentState(mErrorState);
    mErrorState.onError(imageResId, errorMsg);
  }

  public interface State {

    void onLoading();

    void onError(int imgResId, String errorMsg);

    void onCompleted();
  }
}
