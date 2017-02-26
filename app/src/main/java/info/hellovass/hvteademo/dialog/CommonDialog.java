package info.hellovass.hvteademo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import info.hellovass.hvteademo.R;

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

  private final int mContentPadding = dp2px(16.0F);

  private final float DIALOG_WIDTH_RATIO = 0.8F;

  private final float DIALOG_HEIGHT_RATIO = 0.6F;

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

  public void setMessage(int messageResId) {
    setMessage(getContext().getResources().getString(messageResId));
  }

  public void setMessage(String message) {

    if (TextUtils.isEmpty(message)) {

      return;
    }

    ScrollView scrollView = new ScrollView(getContext());
    scrollView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT));

    TextView messageTextView = new TextView(getContext());
    messageTextView.setLayoutParams(
        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT));
    messageTextView.setText(message);
    messageTextView.setPadding(mContentPadding, mContentPadding, mContentPadding, mContentPadding);
    messageTextView.setTextColor(Color.BLACK);
    messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0F);
    messageTextView.setLineSpacing(0.0F, 1.3F);

    ScrollView.LayoutParams layoutParams =
        new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,
            ScrollView.LayoutParams.WRAP_CONTENT);
    scrollView.addView(messageTextView, layoutParams);

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
            FrameLayout.LayoutParams.MATCH_PARENT);
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
    layoutParams.width = (int) (getScreenWidth(getContext()) * DIALOG_WIDTH_RATIO);
    layoutParams.height = (int) (getScreenHeight(getContext()) * DIALOG_HEIGHT_RATIO);
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
}
