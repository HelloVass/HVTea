package info.hellovass.share_lib.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by hello on 2017/3/5.
 */

public interface IActivityLifecycleCallbacks {

  void onActivityCreated(Activity activity, Bundle savedInstanceState);

  void onActivityNewIntent(Activity activity, Intent intent);

  void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data);

  void onActivityDestroyed();
}
