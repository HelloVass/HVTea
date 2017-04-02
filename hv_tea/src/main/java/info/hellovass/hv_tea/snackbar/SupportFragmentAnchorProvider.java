package info.hellovass.hv_tea.snackbar;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by hello on 2017/3/15.
 */

public class SupportFragmentAnchorProvider implements AnchorProvider {

  private ActivityAnchorProvider mActivityAnchorProvider;

  public SupportFragmentAnchorProvider(Fragment context) {

    mActivityAnchorProvider = new ActivityAnchorProvider(context.getActivity());
  }

  @Override public View provideAnchorView() {

    return mActivityAnchorProvider.provideAnchorView();
  }
}
