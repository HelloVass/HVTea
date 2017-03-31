package info.hellovass.hv_tea.socialize;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

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

  public static void share(Activity activity, SHARE_MEDIA platform, UMWeb params,
      UMShareListener shareListener) {

    sShareDelegate.share(activity, platform, params, shareListener);
  }

  public static void share(Activity activity, SHARE_MEDIA platform, String text,
      UMShareListener shareListener) {

    sShareDelegate.share(activity, platform, text, shareListener);
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
