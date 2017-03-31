package info.hellovass.hvteademo.bottom_navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.hellovass.hv_tea.adapter.viewgroup.CommonAdapter;
import info.hellovass.hv_tea.adapter.viewgroup.ViewHolder;
import info.hellovass.hv_tea.bottom_navigation.BottomNavigationView;
import info.hellovass.hv_tea.fragment_navigator.FragmentNavigator;
import info.hellovass.hv_tea.fragment_navigator.FragmentNavigatorAdapter;
import info.hellovass.hv_tea.snackbar.SimpleSnackbar;
import info.hellovass.hvteademo.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2017/3/22.
 */

public class PeroBottomBarTestActivity extends AppCompatActivity {

  @BindView(R.id.container) FrameLayout mContainer;

  @BindView(R.id.bottom_navigation_layout) BottomNavigationView mBottomNavigationLayout;

  private FragmentNavigator mFragmentNavigator;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_bottombar_pero);
    ButterKnife.bind(this);

    mFragmentNavigator =
        FragmentNavigator.create(getSupportFragmentManager(), provideFragmentNavigatorAdapter(),
            R.id.container);
    mFragmentNavigator.setDefaultPosition(0);
    mFragmentNavigator.onCreate(savedInstanceState);

    mBottomNavigationLayout.setAdapter(
        new CommonAdapter<Integer>(this, R.layout.bottombar_item_pero, provideBottomBarMenu()) {

          @Override protected void convert(ViewHolder holder, Integer resId, int position) {

            holder.setImageResource(R.id.iv_img, resId);
          }
        });

    mBottomNavigationLayout.setOnTabSelectedListener(
        new BottomNavigationView.OnTabSelectedListener() {

          @Override public void onTabSelected(int position) {

            SimpleSnackbar.show(PeroBottomBarTestActivity.this, "选中了第 " + position + " 个 BottomBar",
                Snackbar.LENGTH_SHORT);

            setCurrentTab(position);
          }
        });

    setCurrentTab(mFragmentNavigator.getCurrentItemPosition());
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    mFragmentNavigator.onSaveInstanceState(outState);
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    FragmentPool.clear();
  }

  private void setCurrentTab(int position) {

    mFragmentNavigator.navigateTo(position);
  }

  @NonNull private FragmentNavigatorAdapter provideFragmentNavigatorAdapter() {

    return new FragmentNavigatorAdapter() {

      @Override public Fragment provideFragment(int position) {

        return FragmentPool.getFragment(
            FragmentPool.Tab.values()[position % FragmentPool.Tab.values().length]);
      }

      @Override public String provideTag(int position) {

        return FragmentPool.Tab.values()[position % FragmentPool.Tab.values().length].getTitle();
      }

      @Override public int getCount() {

        return FragmentPool.Tab.values().length;
      }
    };
  }

  private List<Integer> provideBottomBarMenu() {

    List<Integer> result = new ArrayList<>();

    for (FragmentPool.Tab tab : FragmentPool.Tab.values()) {

      result.add(tab.getIconResId());
    }

    return result;
  }
}
