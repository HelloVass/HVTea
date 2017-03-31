package info.hellovass.hv_tea.socialize;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

/**
 * Created by hello on 2017/3/31.
 */

public class PeroShareDelegate {

  private UMShareAPI mUMShareAPI;

  PeroShareDelegate() {

  }

  public void init(Context context, UMShareConfig config) {

    mUMShareAPI = UMShareAPI.get(context);
    mUMShareAPI.setShareConfig(config);
  }

  public void share(Activity activity, SHARE_MEDIA platform, UMWeb params,
      UMShareListener shareListener) {

    new ShareAction(activity).setPlatform(platform)
        .withMedia(params)
        .setCallback(shareListener)
        .share();
  }

  public void share(Activity activity, SHARE_MEDIA platform, String text,
      UMShareListener shareListener) {

    new ShareAction(activity).setPlatform(platform)
        .withText(text)
        .setCallback(shareListener)
        .share();
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {

    mUMShareAPI.onActivityResult(requestCode, resultCode, data);
  }

  public void fetchAuthResultWithBundle(Activity activity, Bundle savedInstanceState,
      UMAuthListener authListener) {

    mUMShareAPI.fetchAuthResultWithBundle(activity, savedInstanceState, authListener);
  }

  public void onSaveInstanceState(Bundle outState) {

    mUMShareAPI.onSaveInstanceState(outState);
  }

  public void onDestroy() {

    mUMShareAPI.release();
  }
}
