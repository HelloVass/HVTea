package info.hellovass.hv_tea.snackbar;

import android.view.View;

/**
 * Created by hello on 2017/3/15.
 */

public interface AnchorProvider {

  /**
   * 提供 Snackbar 需要的 View 锚点
   *
   * @return View
   */
  View provideAnchorView();
}
