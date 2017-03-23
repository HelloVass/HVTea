package info.hellovass.hvteademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.hellovass.hv_tea.adapter.recyclerview.CommonAdapter;
import info.hellovass.hv_tea.adapter.recyclerview.ViewHolder;
import info.hellovass.hv_tea.adapter.recyclerview.base.MultiViewTypeAdapter;
import info.hellovass.hvteademo.bottom_navigation.PeroBottomBarTestActivity;
import info.hellovass.hvteademo.gridview.PeroNineGridViewTestActivity;
import info.hellovass.hvteademo.pullrecycler.PeroPullRecyclerTestActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hello on 2016/12/26.
 */

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.recyclerview) RecyclerView mRecyclerView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initWidgets();
  }

  private void initWidgets() {

    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    mRecyclerView.setHasFixedSize(true);

    CommonAdapter<String> adapter = provideAdapter();
    adapter.setOnItemClickListener(new MultiViewTypeAdapter.OnItemClickListener<String>() {

      @Override public void onItemClick(View view, String entity, int position) {

        switch (position) {

          case 0:
            startActivity(new Intent(MainActivity.this, PeroImageLoaderTestActivity.class));
            break;

          case 1:
            startActivity(new Intent(MainActivity.this, PeroTagLayoutTestActivity.class));

            break;

          case 2:
            startActivity(new Intent(MainActivity.this, PeroNineGridViewTestActivity.class));
            break;

          case 3:
            startActivity(new Intent(MainActivity.this, PeroDialogTestActivity.class));
            break;

          case 4:
            startActivity(new Intent(MainActivity.this, PeroPullRecyclerTestActivity.class));
            break;

          case 5:
            startActivity(new Intent(MainActivity.this, PeroEmptyLayoutTestActivity.class));
            break;

          case 6:
            startActivity(new Intent(MainActivity.this, SimpleSnackbarTestActivity.class));

            break;

          case 7:
            startActivity(new Intent(MainActivity.this, SupportSpringTestActivity.class));

            break;

          case 8:
            startActivity(new Intent(MainActivity.this, PeroBottomBarTestActivity.class));
            break;
        }
      }
    });
    mRecyclerView.setAdapter(adapter);
  }

  @NonNull private CommonAdapter<String> provideAdapter() {

    return new CommonAdapter<String>(this, android.R.layout.simple_list_item_1, provideOptions()) {

      @Override protected void convert(ViewHolder holder, String title, int position) {

        holder.setText(android.R.id.text1, title);
      }
    };
  }

  private List<String> provideOptions() {

    List<String> options = new ArrayList<>();

    options.add("PeroImageLoader 测试");
    options.add("PeroTagLayout 测试");
    options.add("PeroGridView 测试");
    options.add("PeroDialog 测试");
    options.add("PeroPullRecycler 测试");
    options.add("PeroEmptyLayout 测试");
    options.add("SimpleSnackbar 测试");
    options.add("弹簧动画测试");
    options.add("BottomNavigationView 以及 FragmentNavigator 测试");

    return options;
  }
}
