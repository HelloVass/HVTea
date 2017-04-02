package info.hellovass.hv_tea.snackbar;

import android.app.Fragment;
import android.view.View;

/**
 * Created by hello on 2017/3/15.
 */

public class FragmentAnchorProvider implements AnchorProvider {

  private ActivityAnchorProvider mActivityAnchorProvider;

  public FragmentAnchorProvider(Fragment context) {

    mActivityAnchorProvider = new ActivityAnchorProvider(context.getActivity());
  }

  @Override public View provideAnchorView() {

    return mActivityAnchorProvider.provideAnchorView();
  }
}
