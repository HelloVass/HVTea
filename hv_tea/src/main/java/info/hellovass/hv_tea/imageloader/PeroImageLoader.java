package info.hellovass.hv_tea.imageloader;

import android.content.Context;
import info.hellovass.hv_tea.imageloader.glide.GlideImageLoaderStrategy;

/**
 * Created by hello on 2016/12/14.
 */

public class PeroImageLoader {

  private ImageLoaderStrategy mImageLoaderStrategy;

  private static class PeroImageLoaderHolder {
    private static final PeroImageLoader sPeroImageLoader = new PeroImageLoader();
  }

  private PeroImageLoader() {
    mImageLoaderStrategy = new GlideImageLoaderStrategy();
  }

  public static PeroImageLoader getInstance() {
    return PeroImageLoaderHolder.sPeroImageLoader;
  }

  public void loadImage(Context context, PeroImageLoaderConfig config) {
    mImageLoaderStrategy.loadImage(context, config);
  }

  public void loadRoundImage(Context context, PeroImageLoaderConfig config) {
    mImageLoaderStrategy.loadRoundImage(context, config);
  }
}
