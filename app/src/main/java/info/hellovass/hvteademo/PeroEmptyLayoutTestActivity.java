package info.hellovass.hvteademo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hellovass.hv_tea.emptylayout.EmptyLayout;

/**
 * Created by hello on 2017/3/13.
 */

public class PeroEmptyLayoutTestActivity extends AppCompatActivity {

  @BindView(R.id.emptylayout) EmptyLayout mEmptyLayout;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_peroemptylayout_test);
    ButterKnife.bind(this);

    initWidgets();
  }

  private void initWidgets() {

    mEmptyLayout.setOnReloadListener(new EmptyLayout.OnReloadListener() {
      @Override public void onReload() {

        simulateReload();
      }
    });
  }

  @OnClick(R.id.simulate_normal_loading) void onNormalLoading() {

    mEmptyLayout.onLoading(); // 进入加载状态

    new Handler().postDelayed(new Runnable() {

      @Override public void run() {

        Toast.makeText(PeroEmptyLayoutTestActivity.this, "成功加载", Toast.LENGTH_SHORT).show();

        mEmptyLayout.onCompleted();
      }
    }, 3 * 1000);
  }

  @OnClick(R.id.simulate_exception) void onException() {

    mEmptyLayout.onLoading();

    new Handler().postDelayed(new Runnable() {

      @Override public void run() {

        mEmptyLayout.onError(R.mipmap.ic_tip_fail, "加载出错，点击重新加载...");
      }
    }, 3 * 1000);
  }

  private void simulateReload() {

    mEmptyLayout.onLoading();

    new Handler().postDelayed(new Runnable() {

      @Override public void run() {

        Toast.makeText(PeroEmptyLayoutTestActivity.this, "重新加载成功", Toast.LENGTH_SHORT).show();

        mEmptyLayout.onCompleted();
      }
    }, 5 * 1000);
  }
}
