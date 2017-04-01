package info.hellovass.hv_tea.socialize.listeners;

import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;

/**
 * Created by hello on 2017/4/1.
 */

public interface AuthListener {

  void onStart(SHARE_MEDIA platform);

  void onSucceed(SHARE_MEDIA platform, int action, Map<String, String> data);

  void onError(SHARE_MEDIA platform, int action, Throwable error);

  void onCancel(SHARE_MEDIA platform, int action);
}
