package info.hellovass.hv_tea.socialize;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by hello on 2017/3/31.
 */

public class ConfigHelper {

  private ConfigHelper() {

  }

  public static void configWeChatPlatform(String appId, String appSecret) {

    PlatformConfig.setWeixin(appId, appSecret);
  }

  public static void configSinaPlatform(String appKey, String appSecret) {

    PlatformConfig.setSinaWeibo(appKey, appSecret, "");
  }
}
