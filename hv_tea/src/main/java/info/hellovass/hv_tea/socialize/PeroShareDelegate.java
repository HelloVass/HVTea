package info.hellovass.hv_tea.socialize;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import info.hellovass.hv_tea.socialize.listeners.AuthListener;
import info.hellovass.hv_tea.socialize.listeners.ShareListener;
import java.util.Map;

/**
 * Created by hello on 2017/3/31.
 */

@SuppressWarnings("WeakerAccess") public class PeroShareDelegate {

  private UMShareAPI mUMShareAPI;

  private ShareListener mShareListener;

  private AuthListener mAuthListener;

  private UMShareListener mUMShareListener = new UMShareListener() {

    @Override public void onStart(SHARE_MEDIA share_media) {

      if (mShareListener != null) {

        mShareListener.onStart(share_media);
      }
    }

    @Override public void onResult(SHARE_MEDIA share_media) {

      if (mShareListener != null) {

        mShareListener.onSuccess(share_media);
      }
    }

    @Override public void onError(SHARE_MEDIA share_media, Throwable throwable) {

      if (mShareListener != null) {

        mShareListener.onError(share_media, throwable);
      }
    }

    @Override public void onCancel(SHARE_MEDIA share_media) {

      if (mShareListener != null) {

        mShareListener.onCancel(share_media);
      }
    }
  };

  private UMAuthListener mUMAuthListener = new UMAuthListener() {

    @Override public void onStart(SHARE_MEDIA share_media) {

      if (mAuthListener != null) {

        mAuthListener.onStart(share_media);
      }
    }

    @Override public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

      if (mAuthListener != null) {

        mAuthListener.onSucceed(share_media, i, map);
      }
    }

    @Override public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

      if (mAuthListener != null) {

        mAuthListener.onError(share_media, i, throwable);
      }
    }

    @Override public void onCancel(SHARE_MEDIA share_media, int i) {

      if (mAuthListener != null) {

        mAuthListener.onCancel(share_media, i);
      }
    }
  };

  PeroShareDelegate() {

  }

  public void init(Context context, UMShareConfig config) {

    mUMShareAPI = UMShareAPI.get(context);
    mUMShareAPI.setShareConfig(config);
  }

  /**
   * 开始分享的大门
   *
   * @param activity Activity 的引用
   * @param platform 要分享到哪个平台
   * @param params PeroShareParams
   * @param listener ShareListener
   */
  public void share(Activity activity, SHARE_MEDIA platform, PeroShareParams params,
      final ShareListener listener) {

    ShareAction shareAction = new ShareAction(activity);

    shareAction.setPlatform(platform); // 设置分享平台

    if (!TextUtils.isEmpty(params.getText())) {

      shareAction.withText(params.getText());
    }

    if (params.getUMWeb() != null) {

      shareAction.withMedia(params.getUMWeb());
    }

    if (params.getUMImage() != null) {

      shareAction.withMedia(params.getUMImage());
    }

    if (params.getUMEmoji() != null) {

      shareAction.withMedia(params.getUMEmoji());
    }

    if (params.getUMVideo() != null) {

      shareAction.withMedia(params.getUMVideo());
    }

    mShareListener = listener;
    shareAction.setCallback(mUMShareListener);
    shareAction.share();
  }

  /**
   * 获取第三方平台的信息
   *
   * @param activity Activity 的引用
   * @param platform 第三方平台
   * @param authListener 回调接口
   */
  public void fetchPlatformInfo(Activity activity, SHARE_MEDIA platform,
      AuthListener authListener) {

    mAuthListener = authListener;
    mUMShareAPI.getPlatformInfo(activity, platform, mUMAuthListener);
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
