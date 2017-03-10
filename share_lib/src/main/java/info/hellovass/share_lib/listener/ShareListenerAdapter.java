package info.hellovass.share_lib.listener;

import info.hellovass.share_lib.SocializeMedia;
import info.hellovass.share_lib.error.PeroShareStatusCode;

/**
 * Created by hello on 2017/3/6.
 */

public abstract class ShareListenerAdapter implements ShareListener {

  public void onStart(SocializeMedia type) {

  }

  @Override public void onProgress(SocializeMedia type, String progressDescription) {

  }

  @Override public final void onSuccess(SocializeMedia type, int code) {

    onComplete(type, PeroShareStatusCode.CODE_SUCCEED, null);
  }

  @Override public final void onError(SocializeMedia type, int code, Throwable error) {

    onComplete(type, PeroShareStatusCode.CODE_ERROR, error);
  }

  @Override public final void onCancel(SocializeMedia type) {

    onComplete(type, PeroShareStatusCode.CODE_CANCEL, null);
  }

  protected abstract void onComplete(SocializeMedia type, int code, Throwable error);
}

