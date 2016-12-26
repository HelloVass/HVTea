package info.hellovass.hvteademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.hellovass.hv_tea.imageloader.PeroImageLoader;
import info.hellovass.hv_tea.imageloader.PeroImageLoaderConfig;

/**
 * Created by hello on 2016/12/26.
 */
public class PeroImageLoaderTestActivity extends AppCompatActivity {

  private static final String TEST_MY_AVATAR =
      "http://upload-images.jianshu.io/upload_images/175730-1a95fc67e263a088.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/format/jpg";

  private static final String TEST_MY_JIANSHU_BLOG_PICTURE =
      "http://upload-images.jianshu.io/upload_images/175730-de039861011b45ba.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/format/jpg";

  @BindView(R.id.iv_avatar) ImageView mAvatarImageView;

  @BindView(R.id.iv_blog) ImageView mBlogImageView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_peroimageloader_test);
    ButterKnife.bind(this);

    PeroImageLoader.getInstance()
        .loadRoundImage(this, new PeroImageLoaderConfig.Builder().setUrl(TEST_MY_AVATAR)
            .setPlaceHolderResId(R.mipmap.ic_launcher)
            .setTarget(mAvatarImageView)
            .build());

    PeroImageLoader.getInstance()
        .loadImage(this, new PeroImageLoaderConfig.Builder().setUrl(TEST_MY_JIANSHU_BLOG_PICTURE)
            .setPlaceHolderResId(R.mipmap.ic_launcher)
            .setTarget(mBlogImageView)
            .build());
  }
}
