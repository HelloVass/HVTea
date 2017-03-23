package info.hellovass.hv_tea.fragment_navigator;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by hello on 2017/3/23.
 */

public final class FragmentNavigator {

  private static final int INVALID_POSITION = -1;

  private static final String EXTRAS_CURRENT_POSITION = "extras_current_position";

  private FragmentManager mFragmentManager;

  private FragmentNavigatorAdapter mAdapter;

  private @IdRes int mContainerId;

  private int mCurrentItemPosition = INVALID_POSITION;

  private int mDefaultPosition = 0;

  public static FragmentNavigator create(FragmentManager fragmentManager,
      FragmentNavigatorAdapter adapter, int containerId) {

    return new FragmentNavigator(fragmentManager, adapter, containerId, 0);
  }

  private FragmentNavigator(FragmentManager fragmentManager, FragmentNavigatorAdapter adapter,
      @IdRes int containerId, int currentItemPosition) {
    mFragmentManager = fragmentManager;
    mAdapter = adapter;
    mContainerId = containerId;
    mCurrentItemPosition = currentItemPosition;
  }

  public int getCurrentItemPosition() {
    return mCurrentItemPosition;
  }

  public void setDefaultPosition(int defaultPosition) {

    if (mCurrentItemPosition == INVALID_POSITION) {

      mCurrentItemPosition = defaultPosition;
    }

    mDefaultPosition = defaultPosition;
  }

  public void onCreate(Bundle savedInstanceState) {

    if (savedInstanceState != null) {
      mCurrentItemPosition = savedInstanceState.getInt(EXTRAS_CURRENT_POSITION, mDefaultPosition);
    }
  }

  public void navigateTo(int position) {

    showFragment(position, false);
  }

  public void reset() {

    resetFragments(mCurrentItemPosition);
  }

  public void onSaveInstanceState(Bundle outState) {

    outState.putInt(EXTRAS_CURRENT_POSITION, mCurrentItemPosition);
  }

  private void showFragment(int position, boolean reset) {

    showFragment(position, reset, false);
  }

  private void showFragment(int position, boolean reset, boolean allowingStateLoss) {

    mCurrentItemPosition = position;

    FragmentTransaction transaction = mFragmentManager.beginTransaction();

    for (int index = 0; index < mAdapter.getCount(); index++) {

      if (index == position) {

        if (reset) {
          performRemoveAction(position, transaction);
          performAddAction(position, transaction);
        } else {
          performShowAction(position, transaction);
        }
      } else {
        performHideAction(index, transaction);
      }
    }

    if (allowingStateLoss) {
      transaction.commitAllowingStateLoss();
    } else {
      transaction.commit();
    }
  }

  private void resetFragments(int currentItemPosition) {

    resetFragments(currentItemPosition, false);
  }

  private void resetFragments(int currentItemPosition, boolean allowingStateLoss) {

    mCurrentItemPosition = currentItemPosition;

    FragmentTransaction transaction = mFragmentManager.beginTransaction();
    performRemoveAllAction();
    performAddAction(currentItemPosition, transaction);

    if (allowingStateLoss) {
      transaction.commitAllowingStateLoss();
    } else {
      transaction.commit();
    }
  }

  private void performAddAction(int position, FragmentTransaction transaction) {

    String tag = mAdapter.provideTag(position);
    Fragment fragment = mAdapter.provideFragment(position);

    if (fragment != null) {
      transaction.add(mContainerId, fragment, tag);
    }
  }

  private void performRemoveAction(int position, FragmentTransaction transaction) {

    String tag = mAdapter.provideTag(position);
    Fragment fragment = mFragmentManager.findFragmentByTag(tag);

    if (fragment != null) {
      transaction.remove(fragment);
    }
  }

  private void performShowAction(int position, FragmentTransaction transaction) {

    String tag = mAdapter.provideTag(position);
    Fragment fragment = mFragmentManager.findFragmentByTag(tag);

    if (fragment == null) {
      performAddAction(position, transaction);
    } else {
      transaction.show(fragment);
    }
  }

  private void performHideAction(int index, FragmentTransaction transaction) {

    String tag = mAdapter.provideTag(index);
    Fragment fragment = mFragmentManager.findFragmentByTag(tag);

    if (fragment != null) {
      transaction.hide(fragment);
    }
  }

  private void performRemoveAllAction() {

    performRemoveAllAction(false);
  }

  private void performRemoveAllAction(boolean allowingStateLoss) {

    FragmentTransaction transaction = mFragmentManager.beginTransaction();

    for (int index = 0; index < mAdapter.getCount(); index++) {

      performRemoveAction(index, transaction);
    }

    if (allowingStateLoss) {
      transaction.commitAllowingStateLoss();
    } else {
      transaction.commit();
    }
  }
}
