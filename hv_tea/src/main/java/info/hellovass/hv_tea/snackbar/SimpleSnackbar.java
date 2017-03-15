package info.hellovass.hv_tea.snackbar;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

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

    IAnchorViewProvider anchorViewProvider;

    if (context instanceof Activity) {

      anchorViewProvider = new ActivityAnchorViewProviderAdapter((Activity) context);
    } else if (context instanceof Fragment) {

      anchorViewProvider = new FragmentAnchorViewProviderAdapter((android.app.Fragment) context);
    } else if (context instanceof android.app.Fragment) {

      anchorViewProvider = new SupportFragmentAnchorViewProviderAdapter((Fragment) context);
    } else {

      throw new IllegalStateException("not support context type");
    }

    mSnackbar = Snackbar.make(anchorViewProvider.provideAnchorView(), "", Snackbar.LENGTH_SHORT);
  }
}
