package info.hellovass.share_lib;

import android.app.Activity;
import android.content.Intent;
import info.hellovass.share_lib.listener.ShareListener;
import info.hellovass.share_lib.params.BaseShareParam;

/**
 * Created by hello on 2017/3/5.
 */

public final class PeroShare {

  private static final PeroShareDelegate sPeroShareDelegate = new PeroShareDelegate();

  private PeroShare() {

  }

  public static void init(PeroShareConfiguration config) {

    sPeroShareDelegate.init(config);
  }

  public static void share(Activity activity, SocializeMedia type, BaseShareParam params,
      ShareListener shareListener) {

    sPeroShareDelegate.share(activity, type, params, shareListener);
  }

  public static void onActivityResult(Activity activity, int requestCode, int resultCode,
      Intent data) {

    sPeroShareDelegate.onActivityResult(activity, requestCode, resultCode, data);
  }
}
