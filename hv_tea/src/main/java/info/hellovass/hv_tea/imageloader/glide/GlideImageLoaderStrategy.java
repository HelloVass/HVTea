package info.hellovass.hv_tea.imageloader.glide;

import android.content.Context;
import com.bumptech.glide.Glide;
import info.hellovass.hv_tea.imageloader.ImageLoaderStrategy;
import info.hellovass.hv_tea.imageloader.PeroImageLoaderConfig;

/**
 * Created by hello on 2016/12/14.
 */

public class GlideImageLoaderStrategy implements ImageLoaderStrategy {

  @Override public void loadImage(Context context, PeroImageLoaderConfig config) {
    Glide.with(context)
        .load(config.getUrl())
        .placeholder(config.getPlaceHolderResId())
        .into(config.getTarget());
  }

  @Override public void loadRoundImage(Context context, PeroImageLoaderConfig config) {
    Glide.with(context)
        .load(config.getUrl())
        .placeholder(config.getPlaceHolderResId())
        .bitmapTransform(new CropCircleTransformation(context))
        .into(config.getTarget());
  }
}
