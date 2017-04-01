package info.hellovass.hv_tea.socialize.listeners;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by hello on 2017/4/1.
 */

public interface ShareListener {

  void onStart(SHARE_MEDIA target);

  void onSuccess(SHARE_MEDIA target);

  void onError(SHARE_MEDIA target, Throwable error);

  void onCancel(SHARE_MEDIA target);
}
