package info.hellovass.hv_tea.imageloader;

import android.content.Context;

/**
 * Created by hello on 2016/12/14.
 */

public interface ImageLoaderStrategy {

  void loadImage(Context context, PeroImageLoaderConfig config);

  void loadRoundImage(Context context, PeroImageLoaderConfig config);
}
