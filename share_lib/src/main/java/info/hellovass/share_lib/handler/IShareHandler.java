package info.hellovass.share_lib.handler;

import android.content.Context;
import info.hellovass.share_lib.listener.ShareListener;
import info.hellovass.share_lib.SocializeMedia;
import info.hellovass.share_lib.params.BaseShareParam;

/**
 * Created by hello on 2017/3/5.
 */

public interface IShareHandler extends IActivityLifecycleCallbacks {

  void share(BaseShareParam params, ShareListener shareListener) throws Exception;

  void release();

  Context getContext();

  SocializeMedia getShareMedia();

  boolean disposable();

  interface InnerShareListener extends ShareListener {

    void onProgress(SocializeMedia type, String progressDescription);
  }
}
