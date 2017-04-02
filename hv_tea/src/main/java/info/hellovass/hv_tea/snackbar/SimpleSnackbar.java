package info.hellovass.hv_tea.snackbar;

import android.app.Activity;
import android.support.design.widget.Snackbar;

/**
 * Created by hello on 2017/3/15.
 */

public final class SimpleSnackbar {

  private static long mNextTimeInMillis = 0L;

  private static Snackbar mSnackbar;

  private SimpleSnackbar() {

  }

  public static void show(Object context, String msg, int duration) {

    long currentTimeMillis = System.currentTimeMillis();

    if (currentTimeMillis < mNextTimeInMillis) {

      return;
    }

    init(context);

    mSnackbar.setText(msg);
    mSnackbar.setDuration(duration);
    mNextTimeInMillis = currentTimeMillis + (duration == Snackbar.LENGTH_SHORT ? 2000 : 3500);
    mSnackbar.show();
  }

  @SuppressWarnings("ConstantConditions") private static void init(Object context) {

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

      return;
    }

    mSnackbar = Snackbar.make(anchorProvider.provideAnchorView(), "", Snackbar.LENGTH_SHORT);
  }

  private static void isSuitableContext(Object context) {

    boolean isActivityContext = context instanceof Activity;

    boolean isSupportFragment = context instanceof android.support.v4.app.Fragment;

    boolean isFragment = context instanceof android.app.Fragment;

    if (isActivityContext || isSupportFragment || isFragment) {

      return;
    }

    throw new IllegalArgumentException("sorry,not support this context type");
  }
}
