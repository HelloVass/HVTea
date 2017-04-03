package info.hellovass.hvteademo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.hellovass.hv_tea.emptylayout.EmptyLayout;
import info.hellovass.hv_tea.emptylayout.OnRetryCallback;
import java.util.Random;

/**
 * Created by hello on 2017/3/13.
 */

public class PeroEmptyLayoutTestActivity extends AppCompatActivity {

  @BindView(R.id.emptylayout) EmptyLayout mEmptyLayout;

  @BindView(R.id.btn_simulate_loading) Button mSimulateLoadingBtn;

  @BindView(R.id.tv_simulate_state) TextView mSimulateStateTxtView;

  private Random mRandom = new Random();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_peroemptylayout_test);
    ButterKnife.bind(this);

    initWidgets();
  }

  private void initWidgets() {

    mEmptyLayout.setOnRetryCallback(new OnRetryCallback() {

      @Override public void onRetry() {

        simulateLoadingState();
      }
    });

    mSimulateLoadingBtn.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {

        simulateLoadingState();
      }
    });
  }

  private void simulateLoadingState() {

    switch (mRandom.nextInt(2)) {

      case 0:
        onNormal();
        break;

      case 1:
        onException();
        break;
    }
  }

  private void onNormal() {

    mSimulateStateTxtView.setText("模拟正常加载");
    mEmptyLayout.onLoading(); // 进入加载状态

    new Handler().postDelayed(new Runnable() {

      @Override public void run() {

        Toast.makeText(PeroEmptyLayoutTestActivity.this, "成功加载数据", Toast.LENGTH_SHORT).show();

        mEmptyLayout.onSucceed();
      }
    }, 3 * 1000);
  }

  private void onException() {

    mSimulateStateTxtView.setText("模拟异常加载");
    mEmptyLayout.onLoading();

    new Handler().postDelayed(new Runnable() {

      @Override public void run() {

        mEmptyLayout.onError(R.mipmap.ic_tip_fail, "加载出错，点击重新加载...");
      }
    }, 3 * 1000);
  }
}
