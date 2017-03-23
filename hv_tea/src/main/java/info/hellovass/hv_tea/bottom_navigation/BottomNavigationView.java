package info.hellovass.hv_tea.bottom_navigation;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import info.hellovass.hv_tea.adapter.viewgroup.base.ViewGroupAdapter;

/**
 * Created by hello on 2017/3/22.
 */

public class BottomNavigationView extends FrameLayout implements PagerIndicator {

  private ViewGroupAdapter mAdapter;

  private LinearLayout mTabContainer;

  private int mCurrentItemPosition = 0;

  private OnTabReselectedListener mOnTabReselectedListener;

  private OnTabSelectedListener mOnTabSelectedListener;

  private final OnClickListener mTabOnClickListener = new OnClickListener() {

    @Override public void onClick(View v) {

      final int oldSelectedPosition = mCurrentItemPosition;
      final int newSelectedPosition = mTabContainer.indexOfChild(v);
      mCurrentItemPosition = newSelectedPosition;

      if (mOnTabSelectedListener != null) {

        mOnTabSelectedListener.onTabSelected(newSelectedPosition);
      }

      if (oldSelectedPosition == newSelectedPosition && mOnTabReselectedListener != null) {

        mOnTabReselectedListener.onTabReselected(newSelectedPosition);
      }

      update(newSelectedPosition);
    }
  };

  public BottomNavigationView(@NonNull Context context) {
    this(context, null);
  }

  public BottomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public BottomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
    mOnTabSelectedListener = onTabSelectedListener;
  }

  public void setOnTabReselectedListener(OnTabReselectedListener onTabReselectedListener) {
    mOnTabReselectedListener = onTabReselectedListener;
  }

  @Override public void setAdapter(ViewGroupAdapter adapter) {

    if (adapter == null) {

      throw new IllegalArgumentException("adapter can't be null");
    }

    mAdapter = adapter;
    notifyDataSetChanged();
  }

  @Override public void notifyDataSetChanged() {

    if (mTabContainer.getChildCount() > 0) {

      mTabContainer.removeAllViews();
    }

    for (int index = 0; index < mAdapter.getCount(); index++) {
      View childView = mAdapter.getView(this, index);
      childView.setOnClickListener(mTabOnClickListener);
      LinearLayout.LayoutParams layoutParams =
          new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0F);
      mTabContainer.addView(childView, index, layoutParams);
    }

    update(mCurrentItemPosition);
  }

  private void init(Context context, AttributeSet attrs) {

    mTabContainer = new LinearLayout(getContext());
    mTabContainer.setGravity(Gravity.CENTER_VERTICAL);
    mTabContainer.setOrientation(LinearLayout.HORIZONTAL);
    FrameLayout.LayoutParams layoutParams =
        new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
    layoutParams.gravity = Gravity.CENTER_VERTICAL;
    addView(mTabContainer, layoutParams);
  }

  private void update(int currentItemPosition) {

    for (int index = 0; index < mTabContainer.getChildCount(); index++) {

      boolean isSelected = (index == currentItemPosition);
      mTabContainer.getChildAt(index).setSelected(isSelected);
    }
  }

  private int dp2px(Context context, float dp) {

    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
        context.getResources().getDisplayMetrics());
  }

  public interface OnTabReselectedListener {

    void onTabReselected(int position);
  }

  public interface OnTabSelectedListener {

    void onTabSelected(int position);
  }
}
