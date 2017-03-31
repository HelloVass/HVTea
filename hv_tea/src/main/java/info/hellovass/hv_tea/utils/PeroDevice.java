package info.hellovass.hv_tea.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

/**
 * Created by hello on 2017/3/31.
 */

public class PeroDevice {

  public static void copyTextToClipboard(Context context, String content) {

    if (TextUtils.isEmpty(content)) {

      return;
    }

    ClipboardManager clipboardManager =
        (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    ClipData clipData = ClipData.newPlainText("copy_content", content);
    clipboardManager.setPrimaryClip(clipData);
  }
}
