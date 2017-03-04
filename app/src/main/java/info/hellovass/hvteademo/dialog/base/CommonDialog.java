package info.hellovass.hvteademo.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import info.hellovass.hv_tea.adapter.recyclerview.CommonAdapter;
import info.hellovass.hv_tea.adapter.recyclerview.ItemViewDelegate;
import info.hellovass.hv_tea.adapter.recyclerview.MultiViewTypeAdapter;
import info.hellovass.hv_tea.adapter.recyclerview.ViewHolder;
import info.hellovass.hvteademo.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hello on 2017/2/23.
 *
 * 通用的 Dialog
 */

public class CommonDialog extends Dialog {

  protected View mContent;

  protected DialogHeaderView mDialogHeaderView;

  protected FrameLayout mContainer;

  protected View mButtonAreaDivider;

  protected TextView mNegativeBtn;

  protected TextView mPositiveBtn;

  private final int mContentPadding = dp2px(8.0F);

  private DialogInterface.OnClickListener mDismissClickListener = new OnClickListener() {

    @Override public void onClick(DialogInterface dialog, int which) {

      dialog.dismiss();
    }
  };

  public CommonDialog(Context context) {
    this(context, R.style.CommonDialog);
  }

  public CommonDialog(Context context, int themeResId) {
    super(context, themeResId);
    init(context);
  }

  protected CommonDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
    super(context, cancelable, cancelListener);
    init(context);
  }

  private void init(Context context) {

    setCancelable(false);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    mContent = LayoutInflater.from(context).inflate(R.layout.dialog_common, null);

    mDialogHeaderView = (DialogHeaderView) mContent.findViewById(R.id.dialog_header);

    mContainer = (FrameLayout) mContent.findViewById(R.id.content_container);
    mButtonAreaDivider = mContent.findViewById(R.id.button_area_divider);
    mNegativeBtn = (TextView) mContent.findViewById(R.id.btn_negative);
    mPositiveBtn = (TextView) mContent.findViewById(R.id.btn_positive);

    super.setContentView(mContent);
  }

  @Override public void setTitle(int titleId) {

    setTitle(getContext().getResources().getString(titleId));
  }

  @Override public void setTitle(CharSequence title) {

    if (!TextUtils.isEmpty(title)) {
      mDialogHeaderView.mTitleTextView.setText(title);
      mDialogHeaderView.setVisibility(View.VISIBLE);
    } else {
      mDialogHeaderView.setVisibility(View.GONE);
    }
  }

  public void setItems(String[] items,
      MultiViewTypeAdapter.OnItemClickListener<String> onItemClickListener) {

    RecyclerView recyclerView = new RecyclerView(mContent.getContext());
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(mContent.getContext()));
    recyclerView.setPadding(mContentPadding, mContentPadding, mContentPadding, mContentPadding);

    SimpleAdapter adapter = new SimpleAdapter(mContent.getContext(), Arrays.asList(items));
    adapter.setOnItemClickListener(onItemClickListener);
    recyclerView.setAdapter(adapter);

    setContent(recyclerView, 0);
  }

  public <T> void setItems(CommonAdapter<T> adapter,
      MultiViewTypeAdapter.OnItemClickListener<T> onItemClickListener) {

    RecyclerView recyclerView = new RecyclerView(mContent.getContext());
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(mContent.getContext()));
    recyclerView.setPadding(mContentPadding, mContentPadding, mContentPadding, mContentPadding);
    recyclerView.setAdapter(adapter);
    adapter.setOnItemClickListener(onItemClickListener);

    setContent(recyclerView, 0);
  }

  public void setMessage(int messageResId) {
    setMessage(getContext().getResources().getString(messageResId));
  }

  public void setMessage(String message) {

    if (TextUtils.isEmpty(message)) {

      return;
    }

    ScrollView scrollView = new ScrollView(getContext());

    TextView messageTextView = new TextView(getContext());
    messageTextView.setText(message);
    messageTextView.setPadding(mContentPadding, mContentPadding, mContentPadding, mContentPadding);
    messageTextView.setTextColor(Color.BLACK);
    messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0F);
    messageTextView.setLineSpacing(0.0F, 1.3F);

    scrollView.addView(messageTextView,
        new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,
            ScrollView.LayoutParams.WRAP_CONTENT));

    setContent(scrollView, 0);
  }

  public void setContent(View view) {

    setContent(view, mContentPadding);
  }

  public void setContent(View view, int padding) {

    mContainer.removeAllViews();
    mContainer.setPadding(padding, padding, padding, padding);
    FrameLayout.LayoutParams layoutParams =
        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT);
    mContainer.addView(view, layoutParams);
  }

  public void setNegativeButton(int negativeButtonTextResId,
      DialogInterface.OnClickListener listener) {

    setNegativeButton(getContext().getString(negativeButtonTextResId), listener);
  }

  public void setNegativeButton(String negativeButtonText,
      final DialogInterface.OnClickListener listener) {

    if (TextUtils.isEmpty(negativeButtonText)) {

      mNegativeBtn.setVisibility(View.GONE);
      mButtonAreaDivider.setVisibility(View.GONE);
      return;
    }

    mNegativeBtn.setText(negativeButtonText);
    mNegativeBtn.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {

        if (listener != null) {
          listener.onClick(CommonDialog.this, 0);
        } else {
          mDismissClickListener.onClick(CommonDialog.this, 0);
        }
      }
    });
    mNegativeBtn.setVisibility(View.VISIBLE);

    if (mPositiveBtn.getVisibility() == View.VISIBLE
        || mNegativeBtn.getVisibility() == View.VISIBLE) {

      mButtonAreaDivider.setVisibility(View.VISIBLE);
    } else {

      mButtonAreaDivider.setVisibility(View.GONE);
    }
  }

  public void setPositiveButton(int positiveButtonTextResId,
      DialogInterface.OnClickListener listener) {

    setPositiveButton(getContext().getResources().getString(positiveButtonTextResId), listener);
  }

  public void setPositiveButton(String positiveButtonText,
      final DialogInterface.OnClickListener listener) {

    if (TextUtils.isEmpty(positiveButtonText)) {

      mPositiveBtn.setVisibility(View.GONE);
      mButtonAreaDivider.setVisibility(View.GONE);
      return;
    }

    mPositiveBtn.setText(positiveButtonText);
    mPositiveBtn.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {

        if (listener != null) {
          listener.onClick(CommonDialog.this, 0);
        } else {

          mDismissClickListener.onClick(CommonDialog.this, 0);
        }
      }
    });
    mPositiveBtn.setVisibility(View.VISIBLE);

    if (mPositiveBtn.getVisibility() == View.VISIBLE
        || mNegativeBtn.getVisibility() == View.VISIBLE) {

      mButtonAreaDivider.setVisibility(View.VISIBLE);
    } else {

      mButtonAreaDivider.setVisibility(View.GONE);
    }
  }

  @Override public void show() {

    Window window = getWindow();

    WindowManager.LayoutParams layoutParams = window.getAttributes();
    layoutParams.width = (int) (getScreenWidth(getContext()) * 0.8F);
    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
    window.setAttributes(layoutParams);

    super.show();
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    this.dismiss();
  }

  private int dp2px(float dp) {

    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
        getContext().getResources().getDisplayMetrics());
  }

  /**
   * 得到屏幕的宽度
   *
   * @param context 上下文
   * @return 屏幕的宽度，单位像素
   */
  private static int getScreenWidth(Context context) {

    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return displayMetrics.widthPixels;
  }

  /**
   * 得到屏幕的高度
   *
   * @param context 上下文
   * @return 屏幕的高度，单位像素
   */
  private static int getScreenHeight(Context context) {

    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return displayMetrics.heightPixels;
  }

  private static class SimpleAdapter extends MultiViewTypeAdapter<String> {

    protected Context mContext;

    protected List<String> mDataList;

    protected LayoutInflater mLayoutInflater;

    public SimpleAdapter(Context context, List<String> dataList) {
      super(context, dataList);

      mContext = context;
      mLayoutInflater = LayoutInflater.from(context);
      mDataList = dataList == null ? new ArrayList<String>() : dataList;

      addItemViewDelegate(provideDefaultItemViewDelegate());
    }

    private ItemViewDelegate<String> provideDefaultItemViewDelegate() {

      return new ItemViewDelegate<String>() {

        @Override public int provideItemViewLayoutResId() {

          return R.layout.listitem_dialog_common;
        }

        @Override public boolean isForViewType(String item, int position) {

          return true;
        }

        @Override public void convert(ViewHolder holder, String entity, int position) {

          holder.setText(R.id.tv_title, entity);
        }
      };
    }
  }
}
