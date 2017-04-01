package info.hellovass.hv_tea.socialize;

import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;

/**
 * Created by hello on 2017/4/1.
 */

@SuppressWarnings("WeakerAccess") public class PeroShareParams {

  private String mText;

  private UMWeb mUMWeb;

  private UMEmoji mUMEmoji;

  private UMImage mUMImage;

  private UMusic mUMusic;

  private UMVideo mUMVideo;

  public PeroShareParams(Builder builder) {
    mText = builder.mText;
    mUMWeb = builder.mUMWeb;
    mUMEmoji = builder.mUMEmoji;
    mUMImage = builder.mUMImage;
    mUMusic = builder.mUMusic;
    mUMVideo = builder.mUMVideo;
  }

  public String getText() {
    return mText;
  }

  public UMWeb getUMWeb() {
    return mUMWeb;
  }

  public UMEmoji getUMEmoji() {
    return mUMEmoji;
  }

  public UMImage getUMImage() {
    return mUMImage;
  }

  public UMusic getUMusic() {
    return mUMusic;
  }

  public UMVideo getUMVideo() {
    return mUMVideo;
  }

  public static class Builder {

    private String mText;

    private UMWeb mUMWeb;

    private UMEmoji mUMEmoji;

    private UMImage mUMImage;

    private UMusic mUMusic;

    private UMVideo mUMVideo;

    public Builder setText(String text) {
      mText = text;
      return this;
    }

    public Builder setUMWeb(UMWeb UMWeb) {
      mUMWeb = UMWeb;
      return this;
    }

    public Builder setUMEmoji(UMEmoji UMEmoji) {
      mUMEmoji = UMEmoji;
      return this;
    }

    public Builder setUMImage(UMImage UMImage) {
      mUMImage = UMImage;
      return this;
    }

    public Builder setUMusic(UMusic UMusic) {
      mUMusic = UMusic;
      return this;
    }

    public Builder setUMVideo(UMVideo UMVideo) {
      mUMVideo = UMVideo;
      return this;
    }

    private void checkEmptyFields() {

    }

    public PeroShareParams build() {

      checkEmptyFields();

      return new PeroShareParams(this);
    }
  }
}
