package info.hellovass.hvteademo;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by hellovass on 2017/4/3.
 */

public class App extends Application {

  private RefWatcher mRefWatcher;

  private static App sApp;

  @Override public void onCreate() {
    super.onCreate();

    sApp = this;

    mRefWatcher = LeakCanary.install(this);
  }

  public static App getInstance() {

    return sApp;
  }

  public RefWatcher getRefWatcher() {

    return mRefWatcher;
  }
}
