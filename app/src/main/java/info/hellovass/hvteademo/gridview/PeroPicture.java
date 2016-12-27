package info.hellovass.hvteademo.gridview;

import info.hellovass.hv_tea.nine_grid.Multiply;
import info.hellovass.hvteademo.R;

/**
 * Created by hello on 2016/12/27.
 */

public class PeroPicture implements Multiply {

  public String mUrl;

  public PeroPicture(String url) {
    mUrl = url;
  }

  @Override public int provideItemLayoutResId() {
    return R.layout.item_type_picture;
  }
}
