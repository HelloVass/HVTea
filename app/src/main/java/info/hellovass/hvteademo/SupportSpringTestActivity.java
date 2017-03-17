package info.hellovass.hvteademo;

import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hello on 2017/3/16.
 */

public class SupportSpringTestActivity extends AppCompatActivity {

  @BindView(R.id.iv_img) ImageView mTestImageView;

  @BindView(R.id.tv_friction) TextView mFrictionTextView;
  @BindView(R.id.sb_friction) SeekBar mFrictionSeekBar; // 张力控制条

  @BindView(R.id.tv_tension) TextView mTensionTextView;
  @BindView(R.id.sb_tension) SeekBar mTensionSeekBar; // 摩擦系数控制条

  private VelocityTracker mVelocityTracker;

  private float mDownX, mDownY;

  private float mMaxVelocity;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_supportspring_test);
    ButterKnife.bind(this);

    initWidgets();
  }

  private void initWidgets() {

    mMaxVelocity = ViewConfiguration.get(this).getScaledMaximumFlingVelocity();

    mTestImageView.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {

        acquireVelocityTracker(event);

        switch (event.getAction()) {

          case MotionEvent.ACTION_DOWN:

            mDownX = event.getX();
            mDownY = event.getY();
            return true;

          case MotionEvent.ACTION_MOVE:

            mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
            mTestImageView.setTranslationX(event.getX() - mDownX);
            mTestImageView.setTranslationY(event.getY() - mDownY);
            return true;

          case MotionEvent.ACTION_UP:
          case MotionEvent.ACTION_CANCEL:

            if (mTestImageView.getTranslationX() != 0) {

              SpringAnimation animationX =
                  new SpringAnimation(mTestImageView, SpringAnimation.TRANSLATION_X, 0);
              animationX.getSpring().setStiffness(getTension()); // 设置张力
              animationX.getSpring().setDampingRatio(getFriction()); // 设置阻尼
              animationX.setStartVelocity(mVelocityTracker.getXVelocity());
              animationX.start();
            }

            if (mTestImageView.getTranslationY() != 0) {

              SpringAnimation animationY =
                  new SpringAnimation(mTestImageView, SpringAnimation.TRANSLATION_Y, 0);
              animationY.getSpring().setStiffness(getTension());  // 设置张力
              animationY.getSpring().setDampingRatio(getFriction());  // 设置阻尼
              animationY.setStartVelocity(mVelocityTracker.getYVelocity());
              animationY.start();
            }

            releaseVelocityTracker();
            return true;
        }

        return false;
      }
    });

    mTensionSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        mTensionTextView.setText("张力:" + getTension());
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });

    mFrictionSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        mFrictionTextView.setText("摩擦系数:" + getFriction());
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }

  private void acquireVelocityTracker(final MotionEvent event) {

    if (mVelocityTracker == null) {

      mVelocityTracker = VelocityTracker.obtain();
    }

    mVelocityTracker.addMovement(event);
  }

  private void releaseVelocityTracker() {

    if (mVelocityTracker != null) {
      mVelocityTracker.clear();
      mVelocityTracker.recycle();
      mVelocityTracker = null;
    }
  }

  /**
   * 获取张力
   */
  private float getTension() {

    return Math.max(mTensionSeekBar.getProgress(), 1.0F);
  }

  /**
   * 获取阻尼
   */
  private float getFriction() {

    return Math.max(mFrictionSeekBar.getProgress() / 100.0F, 0.01F);
  }
}
