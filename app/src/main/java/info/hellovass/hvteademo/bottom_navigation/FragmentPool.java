package info.hellovass.hvteademo.bottom_navigation;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import info.hellovass.hvteademo.R;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by hello on 2017/3/23.
 */

public final class FragmentPool {

  private static Map<Tab, Fragment> sFragmentMap = new EnumMap<>(Tab.class);

  private FragmentPool() {

  }

  public static Fragment getFragment(Tab tab) {

    if (!sFragmentMap.containsKey(tab)) {
      Fragment fragment = newFragment(tab);
      sFragmentMap.put(tab, fragment);
    }

    return sFragmentMap.get(tab);
  }

  public static void clear() {

    sFragmentMap.clear();
  }

  private static Fragment newFragment(Tab tab) {

    Fragment fragment = null;

    switch (tab) {

      case Following:
        fragment = new FollowingFragment();
        break;

      case Explore:
        fragment = new ExploreFragment();
        break;
    }

    return fragment;
  }

  public enum Tab {

    Explore("explore", R.drawable.bg_bottombar_explore), Following("following",
        R.drawable.bg_bottombar_following);

    private String mTitle;

    private @IdRes int mIconResId;

    Tab(String title, int iconResId) {
      mTitle = title;
      mIconResId = iconResId;
    }

    public String getTitle() {
      return mTitle;
    }

    public int getIconResId() {
      return mIconResId;
    }
  }
}
