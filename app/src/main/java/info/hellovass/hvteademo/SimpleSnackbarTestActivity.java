package info.hellovass.hvteademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hellovass.hv_tea.snackbar.SimpleSnackbar;

/**
 * Created by hello on 2017/3/15.
 */

public class SimpleSnackbarTestActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_simplesnackbar);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.btn_show_snackbar) void showSnackbar() {

    SimpleSnackbar.show(this, "测试 SimpleSnackbar", Snackbar.LENGTH_SHORT);
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    SimpleSnackbar.release();
  }
}
