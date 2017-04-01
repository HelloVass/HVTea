package info.hellovass.hv_tea.socialize.utils;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by hello on 2017/3/31.
 */

public class PeroShareConfigHelper {

  private PeroShareConfigHelper() {

  }

  public static void configWeChatPlatform(String appId, String appSecret) {

    PlatformConfig.setWeixin(appId, appSecret);
  }

  public static void configSinaPlatform(String appKey, String appSecret, String redirectUrl) {

    PlatformConfig.setSinaWeibo(appKey, appSecret, redirectUrl);
  }
}
