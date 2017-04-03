package info.hellovass.hv_tea.snackbar;

import android.app.Activity;
import android.view.View;

/**
 * Created by hello on 2017/3/15.
 */

public class ActivityAnchorProvider implements AnchorProvider {

  private Activity mContext;

  public ActivityAnchorProvider(Activity context) {

    mContext = context;
  }

  @Override public View provideAnchorView() {

    if (mContext == null) {

      return null;
    }

    return mContext.getWindow().getDecorView().findViewById(android.R.id.content);
  }
}
