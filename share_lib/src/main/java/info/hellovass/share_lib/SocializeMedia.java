package info.hellovass.share_lib;

/**
 * Created by hello on 2017/3/5.
 */

public enum SocializeMedia {

  QZONE("qzone");

  private String mTitle;

  SocializeMedia(String title) {

    mTitle = title;
  }

  @Override public String toString() {

    return mTitle;
  }
}
