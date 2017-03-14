package info.hellovass.hv_tea.permissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2017/3/14.
 */

public final class PermissionsChecker {

  private final Context mContext;

  public PermissionsChecker(Context context) {
    mContext = context;
  }

  public boolean lacksPermissions(String... permissions) {

    for (String permission : permissions) {

      if (lacksPermission(permission)) {
        return true;
      }
    }

    return false;
  }

  private boolean lacksPermission(String permission) {

    return ContextCompat.checkSelfPermission(mContext, permission)
        == PackageManager.PERMISSION_DENIED;
  }

  public String[] fetchDeniedPermissions(String... permissions) {

    List<String> deniedPermissions = new ArrayList<>();

    for (String permission : permissions) {

      if (lacksPermission(permission)) {
        deniedPermissions.add(permission);
      }
    }

    return deniedPermissions.toArray(new String[deniedPermissions.size()]);
  }
}
