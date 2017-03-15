package info.hellovass.hv_tea.snackbar;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import java.lang.ref.WeakReference;

/**
 * Created by hello on 2017/3/15.
 */

public class FragmentAnchorViewProviderAdapter implements IAnchorViewProvider {

  private WeakReference<Fragment> mFragmentWeakReference;

  public FragmentAnchorViewProviderAdapter(Fragment fragment) {

    mFragmentWeakReference = new WeakReference<>(fragment);
  }

  @Override public View provideAnchorView() {

    Fragment fragment = mFragmentWeakReference.get();

    if (fragment != null) {

      Activity activity = fragment.getActivity();

      if (activity != null) {

        return activity.getWindow().getDecorView().findViewById(android.R.id.content);
      }
    }

    return null;
  }
}
