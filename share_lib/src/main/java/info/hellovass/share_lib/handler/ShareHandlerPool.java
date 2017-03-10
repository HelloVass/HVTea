package info.hellovass.share_lib.handler;

import info.hellovass.share_lib.SocializeMedia;
import info.hellovass.share_lib.handler.qq.QQChatShareHandler;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by hello on 2017/3/6.
 */

public final class ShareHandlerPool {

  private static ShareHandlerPool sShareHandlerPool = new ShareHandlerPool();

  private Map<SocializeMedia, IShareHandler> mShareHandlerMap = new EnumMap<>(SocializeMedia.class);

  private ShareHandlerPool() {

  }

  public static IShareHandler getShareHandler(SocializeMedia type) {

    IShareHandler shareHandler = sShareHandlerPool.mShareHandlerMap.get(type);

    if (shareHandler == null) {

      shareHandler = newShareHandler(type);
      sShareHandlerPool.mShareHandlerMap.put(type, shareHandler);
    }

    return shareHandler;
  }

  public static void remove(SocializeMedia type) {

    sShareHandlerPool.mShareHandlerMap.remove(type);
  }

  private static IShareHandler newShareHandler(SocializeMedia type) {

    switch (type) {

      case QZONE:

        return new QQChatShareHandler();

      default:
        return null;
    }
  }
}
