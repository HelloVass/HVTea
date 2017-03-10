package info.hellovass.share_lib.handler.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import info.hellovass.share_lib.SocializeMedia;
import info.hellovass.share_lib.handler.IShareHandler;
import info.hellovass.share_lib.listener.ShareListener;
import info.hellovass.share_lib.params.BaseShareParam;

/**
 * Created by hello on 2017/3/6.
 */

public class QQChatShareHandler implements IShareHandler {

  @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

  }

  @Override public void onActivityNewIntent(Activity activity, Intent intent) {

  }

  @Override
  public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

  }

  @Override public void onActivityDestroyed() {

  }

  @Override public void share(BaseShareParam params, ShareListener shareListener) throws Exception {

  }

  @Override public void release() {

  }

  @Override public Context getContext() {
    return null;
  }

  @Override public SocializeMedia getShareMedia() {
    return null;
  }

  @Override public boolean disposable() {
    return false;
  }
}
