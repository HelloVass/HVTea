package info.hellovass.hv_tea.fragment_navigator;

import android.support.v4.app.Fragment;

/**
 * Created by hello on 2017/3/22.
 */

public interface FragmentNavigatorAdapter {

  Fragment provideFragment(int position);

  String provideTag(int position);

  int getCount();
}
