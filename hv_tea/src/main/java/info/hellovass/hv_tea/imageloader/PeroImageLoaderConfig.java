package info.hellovass.hv_tea.imageloader;

import android.text.TextUtils;
import android.widget.ImageView;

/**
 * Created by hello on 2016/12/14.
 */

public final class PeroImageLoaderConfig {

  private final String mUrl;

  private final ImageView mTarget;

  private final int mPlaceHolderResId;

  private PeroImageLoaderConfig(Builder builder) {
    mUrl = builder.mUrl;
    mTarget = builder.mTarget;
    mPlaceHolderResId = builder.mPlaceHolderResId;
  }

  public String getUrl() {
    return mUrl;
  }

  public ImageView getTarget() {
    return mTarget;
  }

  public int getPlaceHolderResId() {
    return mPlaceHolderResId;
  }

  public static final class Builder {

    private static final int INVALID_RES_ID = -1;

    private String mUrl;

    private ImageView mTarget;

    private int mPlaceHolderResId = INVALID_RES_ID;

    public Builder() {

    }

    public Builder setUrl(String url) {
      this.mUrl = url;
      return this;
    }

    public Builder setTarget(ImageView target) {
      this.mTarget = target;
      return this;
    }

    public Builder setPlaceHolderResId(int placeHolderResId) {
      this.mPlaceHolderResId = placeHolderResId;
      return this;
    }

    public PeroImageLoaderConfig build() {
      checkEmptyFields();
      return new PeroImageLoaderConfig(this);
    }

    private void checkEmptyFields() {

      if (mUrl == null || TextUtils.isEmpty(mUrl)) {
        mUrl = "";
      }

      if (mTarget == null) {
        throw new IllegalArgumentException("target can't be null");
      }

      if (mPlaceHolderResId == INVALID_RES_ID) {
        throw new IllegalArgumentException("placeHolderResId can't be -1");
      }
    }
  }
}
