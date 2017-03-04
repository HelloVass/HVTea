package info.hellovass.hvteademo.dialog.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import info.hellovass.hvteademo.R;

/**
 * Created by hello on 2017/2/23.
 */

public class DialogHeaderView extends FrameLayout {

  public TextView mTitleTextView;

  public View mTitleDivider;

  public DialogHeaderView(Context context) {
    super(context);
    init();
  }

  public DialogHeaderView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public DialogHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {

    View.inflate(getContext(), R.layout.view_header_dialog, this);

    mTitleTextView = (TextView) findViewById(R.id.tv_title);
    mTitleDivider = findViewById(R.id.title_divider);
  }
}
