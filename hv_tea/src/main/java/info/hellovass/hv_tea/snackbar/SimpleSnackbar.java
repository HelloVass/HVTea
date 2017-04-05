package info.hellovass.hv_tea.snackbar;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;

/**
 * Created by hello on 2017/3/15.
 */

public final class SimpleSnackbar {

  private static long mNextTimeInMillis = 0L;

  private SimpleSnackbar() {

  }

  /**
   * 显示一个 Snackbar
   *
   * @param context Activity,Fragment
   * @param text 显示在 Snackbar 的提示信息
   * @param duration Snackbar 显示的时间
   */
  public static void show(Object context, @NonNull CharSequence text, int duration) {

    long currentTimeMillis = System.currentTimeMillis();

    if (currentTimeMillis < mNextTimeInMillis) {

      return;
    }

    Snackbar snackbar = createSnackbarInstance(context);

    if (snackbar == null) { // 如果未创建 Snackbar，return

      return;
    }

    snackbar.setText(text);
    snackbar.setDuration(duration);
    mNextTimeInMillis = currentTimeMillis + (duration == Snackbar.LENGTH_SHORT ? 2000 : 3500);
    snackbar.show();
  }

  @SuppressWarnings("ConstantConditions")
  private static Snackbar createSnackbarInstance(Object context) {

    isSuitableContext(context); // 判断是否是支持的 Context 类型

    AnchorProvider anchorProvider = null;

    if (context instanceof Activity) {

      anchorProvider = new ActivityAnchorProvider((Activity) context);
    } else if (context instanceof android.support.v4.app.Fragment) {

      anchorProvider = new SupportFragmentAnchorProvider((android.support.v4.app.Fragment) context);
    } else if (context instanceof android.app.Fragment) {

      anchorProvider = new FragmentAnchorProvider((android.app.Fragment) context);
    }

    if (anchorProvider.provideAnchorView() == null) {

      return null;
    }

    return Snackbar.make(anchorProvider.provideAnchorView(), "", Snackbar.LENGTH_SHORT);
  }

  /**
   * 判断传入的 Context 是否合适
   *
   * @param context 上下文
   */
  private static void isSuitableContext(Object context) {

    boolean isActivityContext = context instanceof Activity;

    boolean isSupportFragment = context instanceof android.support.v4.app.Fragment;

    boolean isFragment = context instanceof android.app.Fragment;

    if (isActivityContext || isSupportFragment || isFragment) {

      return;
    }

    throw new IllegalArgumentException("sorry, not support this context type");
  }
}
