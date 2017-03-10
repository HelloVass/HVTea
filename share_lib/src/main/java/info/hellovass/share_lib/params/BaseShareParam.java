package info.hellovass.share_lib.params;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hello on 2017/3/5.
 */

public abstract class BaseShareParam {

  protected String mTitle;

  protected String mContent;

  protected String mTargetUrl;

  private Map<String, Object> mExtraMap = new HashMap<>();
}
