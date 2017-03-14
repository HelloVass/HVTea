package info.hellovass.hv_tea.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import info.hellovass.hv_tea.R;

/**
 * Created by hello on 2016/12/17.
 */

public class PermissionsActivity extends AppCompatActivity {

  private static final String EXTRAS_PERMISSIONS = "extras_permissions";

  public static final int REQUEST_CODE_PERMISSIONS = 1202; // 请求权限

  public static final int PERMISSIONS_GRANTED = 1200; // 权限授予

  public static final int PERMISSIONS_DENIED = 1201; // 权限拒绝

  private PermissionsChecker mPermissionsChecker;

  private boolean isRequireCheck = false;

  public static Intent getCallingIntent(Activity activity, String... permissions) {

    Intent intent = new Intent(activity, PermissionsActivity.class);
    intent.putExtra(EXTRAS_PERMISSIONS, permissions);
    return intent;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getIntent() == null || !getIntent().hasExtra(EXTRAS_PERMISSIONS)) {

      throw new RuntimeException("PermissionActivity 需要使用 getCallingIntent 方法启动");
    }

    setContentView(R.layout.activity_permissions);

    mPermissionsChecker = new PermissionsChecker(this);
    isRequireCheck = true;
  }

  @Override protected void onResume() {
    super.onResume();

    if (isRequireCheck) {

      String[] permissions = parseArgs();

      if (mPermissionsChecker.lacksPermissions(permissions)) { // 如果未获取所有需要的权限

        String[] deniedPermissions = mPermissionsChecker.fetchDeniedPermissions(permissions);
        requestPermissions(deniedPermissions);
      } else {
        allPermissionsGranted();
      }
    } else {
      isRequireCheck = true;
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (requestCode == REQUEST_CODE_PERMISSIONS && hasAllPermissionsGranted(grantResults)) {
      isRequireCheck = true;
      allPermissionsGranted();
    } else {
      isRequireCheck = false;
      showMissingPermissionDialog();
    }
  }

  private String[] parseArgs() {

    return getIntent().getStringArrayExtra(EXTRAS_PERMISSIONS);
  }

  private void requestPermissions(String... permissions) {

    ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSIONS);
  }

  private void allPermissionsGranted() {

    setResult(PERMISSIONS_GRANTED);
    finish();
  }

  private boolean hasAllPermissionsGranted(int[] grantResults) {

    for (int grantResult : grantResults) {

      if (grantResult == PackageManager.PERMISSION_DENIED) {

        return false;
      }
    }

    return true;
  }

  private void showMissingPermissionDialog() {

    new AlertDialog.Builder(this).setTitle(R.string.help)
        .setMessage(R.string.help_content)
        .setNegativeButton("退出", new DialogInterface.OnClickListener() {

          @Override public void onClick(DialogInterface dialog, int which) {

            setResult(PERMISSIONS_DENIED);
            finish();
          }
        })
        .setPositiveButton("去设置", new DialogInterface.OnClickListener() {

          @Override public void onClick(DialogInterface dialog, int which) {

            navigateToAppSettings(PermissionsActivity.this);
          }
        })
        .setCancelable(false)
        .show();
  }

  private void navigateToAppSettings(Context context) {

    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    intent.setData(Uri.parse("package:" + context.getPackageName()));
    context.startActivity(intent);
  }
}
