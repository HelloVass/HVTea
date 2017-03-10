package info.hellovass.share_lib;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import info.hellovass.share_lib.downloader.DefaultImageDownloader;
import info.hellovass.share_lib.downloader.IImageDownloader;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hello on 2017/3/5.
 */

public class PeroShareConfiguration {

  private String mImageCachePath;

  private int mDefaultShareImageResId;

  private String mSinaRedirectUrl;

  private String mSinaScope;

  private Executor mExecutor;

  private IImageDownloader mIImageDownloader;

  public PeroShareConfiguration(Builder builder) {

    mImageCachePath = builder.mImageCachePath;
    mDefaultShareImageResId = builder.mDefaultShareImageResId;
    mSinaRedirectUrl = builder.mSinaRedirectUrl;
    mSinaScope = builder.mSinaScope;
    mIImageDownloader = builder.mIImageDownloader;
    mExecutor = Executors.newCachedThreadPool();
  }

  public String getImageCachePath() {

    return mImageCachePath;
  }

  public int getDefaultShareImageResId() {

    return mDefaultShareImageResId;
  }

  public String getSinaRedirectUrl() {

    return mSinaRedirectUrl;
  }

  public String getSinaScope() {

    return mSinaScope;
  }

  public Executor getExecutor() {

    return mExecutor;
  }

  public IImageDownloader getIImageDownloader() {

    return mIImageDownloader;
  }

  public static class Builder {

    private static final String IMAGE_CACHE_FILE_NAME = "shareImage";

    private Context mContext;

    private String mImageCachePath;

    private int mDefaultShareImageResId = -1;

    private String mSinaRedirectUrl;

    private String mSinaScope;

    private IImageDownloader mIImageDownloader;

    public Builder(Context context) {

      mContext = context.getApplicationContext();
    }

    public Builder setImageCachePath(String imageCachePath) {
      mImageCachePath = imageCachePath;
      return this;
    }

    public Builder setDefaultShareImageResId(int defaultShareImageResId) {
      mDefaultShareImageResId = defaultShareImageResId;
      return this;
    }

    public Builder setSinaRedirectUrl(String sinaRedirectUrl) {
      mSinaRedirectUrl = sinaRedirectUrl;
      return this;
    }

    public Builder setSinaScope(String sinaScope) {
      mSinaScope = sinaScope;
      return this;
    }

    public Builder setIImageDownloader(IImageDownloader IImageDownloader) {
      mIImageDownloader = IImageDownloader;
      return this;
    }

    public PeroShareConfiguration build() {

      checkEmptyFields();

      return new PeroShareConfiguration(this);
    }

    private void checkEmptyFields() {

      File imageCacheFile = null;

      if (!TextUtils.isEmpty(mImageCachePath)) {

        imageCacheFile = new File(mImageCachePath);

        if (!imageCacheFile.isDirectory()) {

          imageCacheFile = null;
        } else if (!imageCacheFile.exists() && !imageCacheFile.mkdirs()) {

          imageCacheFile = null;
        }
      }

      if (imageCacheFile == null) {

        mImageCachePath = provideDefaultImageCacheFile(mContext);
      }

      if (mIImageDownloader == null) {

        mIImageDownloader = provideDefaultImageDownloader();
      }

      //TODO: 设置默认的分享图片
      if (mDefaultShareImageResId == -1) {

        mDefaultShareImageResId = 1;
      }

      // TODO:设置默认的新浪重定向URl
      if (TextUtils.isEmpty(mSinaRedirectUrl)) {

        mSinaRedirectUrl = "";
      }

      // TODO: 设置默认的新浪SCope
      if (TextUtils.isEmpty(mSinaScope)) {

      }
    }

    private String provideDefaultImageCacheFile(Context context) {

      String imageCachePath = null;

      File extCacheFile = context.getExternalCacheDir();

      if (extCacheFile == null) {

        extCacheFile = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
      }

      if (extCacheFile != null) {

        imageCachePath = extCacheFile.getAbsolutePath()
            + File.separator
            + IMAGE_CACHE_FILE_NAME
            + File.separator;
        File imageCacheFile = new File(imageCachePath);

        imageCacheFile.mkdirs();
      }

      return imageCachePath;
    }

    // TODO: 创建默认的图片下载器
    private IImageDownloader provideDefaultImageDownloader() {

      return new DefaultImageDownloader();
    }
  }
}
