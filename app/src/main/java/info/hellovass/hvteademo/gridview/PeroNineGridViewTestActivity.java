package info.hellovass.hvteademo.gridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.hellovass.hv_tea.imageloader.PeroImageLoader;
import info.hellovass.hv_tea.imageloader.PeroImageLoaderConfig;
import info.hellovass.hv_tea.nine_grid.BaseAdapter;
import info.hellovass.hv_tea.nine_grid.NineGridView;
import info.hellovass.hv_tea.nine_grid.ViewHolder;
import info.hellovass.hvteademo.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2016/12/27.
 */

public class PeroNineGridViewTestActivity extends AppCompatActivity {

  @BindView(R.id.nine_gridview) NineGridView mNineGridView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_peroninegridview_test);
    ButterKnife.bind(this);

    mNineGridView.setAdapter(new BaseAdapter<PeroPicture>(this, generatePictureList()) {

      @Override public void convert(ViewHolder holder, int position, PeroPicture peroPicture) {

        switch (peroPicture.provideItemLayoutResId()) {

          case R.layout.item_type_picture:

            ImageView picImageView = holder.getView(R.id.iv_picture);

            PeroImageLoader.getInstance()
                .loadImage(getContext(),
                    new PeroImageLoaderConfig.Builder().setUrl(peroPicture.mUrl)
                        .setPlaceHolderResId(R.mipmap.ic_launcher)
                        .setTarget(picImageView)
                        .build());
            break;

          default:
            break;
        }
      }
    });
  }

  private List<PeroPicture> generatePictureList() {

    List<PeroPicture> peroPictureList = new ArrayList<>();

    peroPictureList.add(new PeroPicture(
        "http://upload-images.jianshu.io/upload_images/175730-1a95fc67e263a088.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/format/jpg"));

    peroPictureList.add(new PeroPicture(
        "http://upload-images.jianshu.io/upload_images/175730-1a95fc67e263a088.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/format/jpg"));

    peroPictureList.add(new PeroPicture(
        "http://upload-images.jianshu.io/upload_images/175730-1a95fc67e263a088.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/format/jpg"));

    peroPictureList.add(new PeroPicture(
        "http://upload-images.jianshu.io/upload_images/175730-1a95fc67e263a088.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/format/jpg"));

    peroPictureList.add(new PeroPicture(
        "http://upload-images.jianshu.io/upload_images/175730-1a95fc67e263a088.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/format/jpg"));

    peroPictureList.add(new PeroPicture(
        "http://upload-images.jianshu.io/upload_images/175730-1a95fc67e263a088.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/format/jpg"));

    peroPictureList.add(new PeroPicture(
        "http://upload-images.jianshu.io/upload_images/175730-1a95fc67e263a088.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/format/jpg"));

    peroPictureList.add(new PeroPicture(
        "http://upload-images.jianshu.io/upload_images/175730-1a95fc67e263a088.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/format/jpg"));

    peroPictureList.add(new PeroPicture(
        "http://upload-images.jianshu.io/upload_images/175730-1a95fc67e263a088.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/format/jpg"));

    return peroPictureList;
  }
}
