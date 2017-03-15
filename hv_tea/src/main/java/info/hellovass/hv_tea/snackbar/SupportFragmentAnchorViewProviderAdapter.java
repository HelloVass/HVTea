package info.hellovass.hv_tea.snackbar;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import java.lang.ref.WeakReference;

/**
 * Created by hello on 2017/3/15.
 */

public class SupportFragmentAnchorViewProviderAdapter implements IAnchorViewProvider {

  private WeakReference<Fragment> mSupportFragmentWeakReference;

  public SupportFragmentAnchorViewProviderAdapter(Fragment fragment) {

    mSupportFragmentWeakReference = new WeakReference<>(fragment);
  }

  @Override public View provideAnchorView() {

    Fragment fragment = mSupportFragmentWeakReference.get();

    if (fragment != null) {

      Activity activity = (Activity) fragment.getContext();

      if (activity != null) {

        return activity.getWindow().getDecorView().findViewById(android.R.id.content);
      }
    }

    return null;
  }
}
