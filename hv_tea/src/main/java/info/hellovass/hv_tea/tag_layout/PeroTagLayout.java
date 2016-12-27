package info.hellovass.hv_tea.tag_layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hello on 2016/12/19.
 */

public class PeroTagLayout extends ViewGroup {

  private BaseAdapter mAdapter;

  public PeroTagLayout(Context context) {
    this(context, null);
  }

  public PeroTagLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setAdapter(BaseAdapter adapter) {

    if (adapter == null) {
      throw new IllegalArgumentException("adapter can't be null");
    }

    removeAllViews();

    int count = adapter.getCount();
    for (int index = 0; index < count; index++) {
      View childView = adapter.getView(this, index);
      addView(childView, index);
    }

    mAdapter = adapter;
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    int widthSize = MeasureSpec.getSize(widthMeasureSpec);
    int widthMode = MeasureSpec.getMode(widthMeasureSpec);

    int heightSize = MeasureSpec.getSize(heightMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);

    // wrap_content 时候的宽高
    int actualWidth = 0;
    int actualHeight = 0;

    int lineWidth = 0; // 测量时每一行的宽度

    int lineHeight = 0; // 测量时每一行的高度

    for (int index = 0; index < getChildCount(); index++) { // 遍历子元素

      View childView = getChildAt(index);

      measureChild(childView, widthMeasureSpec, heightMeasureSpec); // 测量每一个子 View 的宽和高

      // 获取测量到的宽、高
      int childWidth = childView.getMeasuredWidth();
      int childHeight = childView.getMeasuredHeight();

      MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();

      // 实际的宽、高
      int actualChildWidth =
          marginLayoutParams.leftMargin + childWidth + marginLayoutParams.rightMargin;
      int actualChildHeight =
          marginLayoutParams.topMargin + childHeight + marginLayoutParams.bottomMargin;

      if ((lineWidth + actualChildWidth)
          > widthSize - getPaddingLeft() - getPaddingRight()) { // 需要换行

        actualWidth = Math.max(lineWidth, actualWidth); // 取宽度的最大值
        lineWidth = actualChildWidth; // 另起一行

        actualHeight += lineHeight;
        lineHeight = actualChildHeight;
      } else {

        lineWidth += actualChildWidth;
        lineHeight = Math.max(lineHeight, actualChildHeight);
      }

      int lastIndex = getChildCount() - 1;

      if (index == lastIndex) {
        actualWidth = Math.max(lineWidth, actualWidth);
        actualHeight += lineHeight;
      }
    }

    setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize
            : getPaddingLeft() + actualWidth + getPaddingRight(),
        heightMode == MeasureSpec.EXACTLY ? heightSize
            : getPaddingBottom() + actualHeight + getPaddingTop());
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {

    int width = getWidth();

    int childLeft = getPaddingLeft();
    int childTop = getPaddingTop();

    for (int index = 0; index < getChildCount(); index++) {

      View childView = getChildAt(index);

      if (childView.getVisibility() == GONE) {
        continue;
      }

      int childWidth = childView.getMeasuredWidth();
      int childHeight = childView.getMeasuredHeight();

      MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();

      int actualChildWidth =
          marginLayoutParams.leftMargin + childWidth + marginLayoutParams.rightMargin;
      int actualChildHeight =
          marginLayoutParams.topMargin + childHeight + marginLayoutParams.bottomMargin;

      if (childLeft + actualChildWidth > width - getPaddingLeft() - getPaddingRight()) {
        childLeft = getPaddingLeft();
        childTop += actualChildHeight;
      }

      int left = marginLayoutParams.leftMargin + childLeft;
      int top = marginLayoutParams.topMargin + childTop;
      int right = left + childWidth;
      int bottom = top + childHeight;

      childView.layout(left, top, right, bottom);

      childLeft += actualChildWidth;
    }
  }

  @Override public LayoutParams generateLayoutParams(AttributeSet attrs) {
    return new MarginLayoutParams(getContext(), attrs);
  }

  @Override protected LayoutParams generateLayoutParams(LayoutParams p) {
    return new MarginLayoutParams(p);
  }

  @Override protected LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
  }
}
