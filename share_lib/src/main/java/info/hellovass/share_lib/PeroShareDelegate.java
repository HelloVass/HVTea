package info.hellovass.share_lib;

import android.app.Activity;
import android.content.Intent;
import info.hellovass.share_lib.error.PeroShareStatusCode;
import info.hellovass.share_lib.error.ShareException;
import info.hellovass.share_lib.handler.IShareHandler;
import info.hellovass.share_lib.handler.ShareHandlerPool;
import info.hellovass.share_lib.listener.ShareListener;
import info.hellovass.share_lib.params.BaseShareParam;

/**
 * Created by hello on 2017/3/5.
 */

class PeroShareDelegate {

  private IShareHandler mIShareHandler;

  private PeroShareConfiguration mPeroShareConfiguration;

  private ShareListener mShareListener;

  private IShareHandler.InnerShareListener mInnerShareListener =
      new IShareHandler.InnerShareListener() {

        @Override public void onProgress(SocializeMedia type, String progressDescription) {

        }

        @Override public void onStart(SocializeMedia type) {

          if (mShareListener != null) {

            mShareListener.onStart(type);
          }
        }

        @Override public void onSuccess(SocializeMedia type, int code) {

        }

        @Override public void onError(SocializeMedia type, int code, Throwable error) {

        }

        @Override public void onCancel(SocializeMedia type) {

        }
      };

  public void init(PeroShareConfiguration config) {

    mPeroShareConfiguration = config;
  }

  public void share(Activity activity, SocializeMedia type, BaseShareParam params,
      ShareListener shareListener) {

    if (mPeroShareConfiguration == null) {
      throw new IllegalArgumentException("PeroShareConfiguration can't be null");
    }

    if (params == null) {

      throw new IllegalStateException("share params can'be null");
    }

    if (mIShareHandler != null) {

      release(mIShareHandler.getShareMedia());
    }

    mIShareHandler = ShareHandlerPool.getShareHandler(type);

    if (mIShareHandler != null) {

      try {

        mShareListener = shareListener;

        mInnerShareListener.onStart(type);
        mIShareHandler.share(params, mInnerShareListener);
      } catch (ShareException e) {

      } catch (Exception e) {

      }
    } else {

      mInnerShareListener.onError(type, PeroShareStatusCode.CODE_ERROR_UNEXPLAINED,
          new Exception("unknown ShareMedia "));
    }
  }

  public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

    if (mIShareHandler != null) {

      mIShareHandler.onActivityResult(activity, requestCode, resultCode, data);
    }
  }

  private void release(SocializeMedia type) {

    mShareListener = null;

    if (mIShareHandler != null) {

      mIShareHandler.release();
    }

    mIShareHandler = null;
    ShareHandlerPool.remove(type);
  }
}
