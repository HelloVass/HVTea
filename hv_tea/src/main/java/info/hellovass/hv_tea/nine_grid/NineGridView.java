package info.hellovass.hv_tea.nine_grid;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hello on 2016/12/19.
 */

public class NineGridView extends ViewGroup {

  private static final String TAG = NineGridView.class.getSimpleName();

  private static final int DEFAULT_MAX_SIZE = 9; // 默认最多 item 个数

  private int mRowCount = 0; // 当前行数

  private int mColumnCount = 3; // 当前列数

  private int mSpacing = dp2px(8.0F); // 默认间隙 8dp

  private int mItemSize; // item 的大小

  private int mItemCount = 0; // item 的个数

  private BaseAdapter mAdapter;

  public NineGridView(Context context) {
    this(context, null);
  }

  public NineGridView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    //   nothing to do
  }

  public void setAdapter(BaseAdapter adapter) {

    if (adapter == null) {
      throw new IllegalArgumentException("adapter can't be null");
    }

    if (adapter.getCount() > DEFAULT_MAX_SIZE) {
      mItemCount = DEFAULT_MAX_SIZE;
    } else {
      mItemCount = adapter.getCount();
    }

    removeAllViews();
    calculateRowCount();

    for (int index = 0; index < mItemCount; index++) {
      View childView = adapter.getView(this, index);
      addView(childView);
    }

    mAdapter = adapter;
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec);

    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int heightSize = MeasureSpec.getSize(heightMeasureSpec);

    int actualWidth = widthSize - getPaddingLeft() - getPaddingRight();
    int actualHeight = 0;

    if (getChildCount() > 0) {
      mItemSize = (actualWidth - mSpacing * (mColumnCount - 1)) / mColumnCount;
      actualHeight = mItemSize * mRowCount + mSpacing * (mRowCount - 1);
      measureChildren(MeasureSpec.makeMeasureSpec(mItemSize, MeasureSpec.EXACTLY),
          MeasureSpec.makeMeasureSpec(mItemSize, MeasureSpec.EXACTLY));
    }

    setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : actualWidth,
        heightMode == MeasureSpec.EXACTLY ? heightSize : actualHeight);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {

    for (int index = 0; index < getChildCount() && mAdapter != null; index++) {

      View childView = getChildAt(index);

      int rowNum = index / mColumnCount;
      int columnNum = index % mColumnCount;

      int left = getPaddingLeft() + (mItemSize + mSpacing) * columnNum;
      int top = getPaddingTop() + (mItemSize + mSpacing) * rowNum;
      int right = left + mItemSize;
      int bottom = top + mItemSize;

      childView.layout(left, top, right, bottom);
      mAdapter.convert(ViewHolder.get(childView), index);
    }
  }

  private void calculateRowCount() {
    mRowCount = mItemCount / 3 + (mItemCount % 3 == 0 ? 0 : 1);
  }

  private int dp2px(float dp) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
        getResources().getDisplayMetrics());
  }
}
