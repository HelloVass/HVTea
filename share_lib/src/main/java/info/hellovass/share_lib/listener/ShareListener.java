package info.hellovass.share_lib.listener;

import info.hellovass.share_lib.SocializeMedia;

/**
 * Created by hello on 2017/3/5.
 */

public interface ShareListener {

  void onStart(SocializeMedia type);

  void onProgress(SocializeMedia type, String progressDescription);

  void onSuccess(SocializeMedia type, int code);

  void onError(SocializeMedia type, int code, Throwable error);

  void onCancel(SocializeMedia type);
}
