package info.hellovass.hv_tea.snackbar;

import android.app.Activity;
import android.view.View;
import java.lang.ref.WeakReference;

/**
 * Created by hello on 2017/3/15.
 */

public class ActivityAnchorProvider implements AnchorProvider {

  private WeakReference<Activity> mContextWeakReference;

  public ActivityAnchorProvider(Activity context) {

    mContextWeakReference = new WeakReference<>(context);
  }

  @Override public View provideAnchorView() {

    Activity context = mContextWeakReference.get();

    if (context == null) {

      return null;
    }

    return context.getWindow().getDecorView().findViewById(android.R.id.content);
  }
}
