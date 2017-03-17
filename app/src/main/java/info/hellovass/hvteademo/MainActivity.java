package info.hellovass.hvteademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hellovass.hvteademo.gridview.PeroNineGridViewTestActivity;
import info.hellovass.hvteademo.pullrecycler.PeroPullRecyclerTestActivity;

/**
 * Created by hello on 2016/12/26.
 */

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.btn_peroimageloader_test) void turnToPeroImageLoaderTestActivity() {
    startActivity(new Intent(this, PeroImageLoaderTestActivity.class));
  }

  @OnClick(R.id.btn_perotaglayout_test) void turnToPeroTagLayoutTestActivity() {
    startActivity(new Intent(this, PeroTagLayoutTestActivity.class));
  }

  @OnClick(R.id.btn_peroninegridview_test) void turnToPeroNineGridViewTestActivity() {
    startActivity(new Intent(this, PeroNineGridViewTestActivity.class));
  }

  @OnClick(R.id.btn_perodialog_test) void turnToPeroDialogTestActivity() {
    startActivity(new Intent(this, PeroDialogTestActivity.class));
  }

  @OnClick(R.id.btn_peropullrecycler_test) void turnToPeroPullRecyclerTestActivity() {
    startActivity(new Intent(this, PeroPullRecyclerTestActivity.class));
  }

  @OnClick(R.id.btn_peroemptylayout_test) void turnToPeroEmptyLayoutTestActivity() {
    startActivity(new Intent(this, PeroEmptyLayoutTestActivity.class));
  }

  @OnClick(R.id.btn_simplesnackbar_test) void turnToSimpleSnackbarTestActivity() {
    startActivity(new Intent(this, SimpleSnackbarTestActivity.class));
  }

  @OnClick(R.id.btn_spring_test) void turnToSpringTestActivity() {
    startActivity(new Intent(this, SupportSpringTestActivity.class));
  }
}
