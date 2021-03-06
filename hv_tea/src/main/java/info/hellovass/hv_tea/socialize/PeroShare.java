package info.hellovass.hv_tea.socialize;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import info.hellovass.hv_tea.socialize.listeners.AuthListener;
import info.hellovass.hv_tea.socialize.listeners.ShareListener;

/**
 * Created by hello on 2017/3/31.
 */

public class PeroShare {

  private static final PeroShareDelegate sShareDelegate = new PeroShareDelegate();

  private PeroShare() {

  }

  public static void init(Context context, UMShareConfig config) {

    sShareDelegate.init(context, config);
  }

  public static void share(Activity activity, SHARE_MEDIA platform, PeroShareParams params,
      ShareListener listener) {

    sShareDelegate.share(activity, platform, params, listener);
  }

  public static void fetchPlatformInfo(Activity activity, SHARE_MEDIA platform,
      AuthListener authListener) {

    sShareDelegate.fetchPlatformInfo(activity, platform, authListener);
  }

  public static void onActivityResult(int requestCode, int resultCode, Intent data) {

    sShareDelegate.onActivityResult(requestCode, resultCode, data);
  }

  public static void fetchAuthResultWithBundle(Activity activity, Bundle savedInstanceState,
      UMAuthListener authListener) {

    sShareDelegate.fetchAuthResultWithBundle(activity, savedInstanceState, authListener);
  }

  public static void onSaveInstanceState(Bundle outState) {

    sShareDelegate.onSaveInstanceState(outState);
  }

  public static void onDestory() {

    sShareDelegate.onDestroy();
  }
}
