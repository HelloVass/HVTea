package info.hellovass.hv_tea.snackbar;

import android.app.Activity;
import android.view.View;
import java.lang.ref.WeakReference;

/**
 * Created by hello on 2017/3/15.
 */

public class ActivityAnchorViewProviderAdapter implements IAnchorViewProvider {

  private WeakReference<Activity> mActivityWeakReference;

  public ActivityAnchorViewProviderAdapter(Activity activity) {
    mActivityWeakReference = new WeakReference<>(activity);
  }

  @Override public View provideAnchorView() {

    Activity activity = mActivityWeakReference.get();

    if (activity != null) {

      return activity.getWindow().getDecorView().findViewById(android.R.id.content);
    }

    return null;
  }
}
