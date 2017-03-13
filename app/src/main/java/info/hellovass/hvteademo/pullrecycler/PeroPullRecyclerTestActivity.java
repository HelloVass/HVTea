package info.hellovass.hvteademo.pullrecycler;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.hellovass.hv_tea.adapter.recyclerview.CommonAdapter;
import info.hellovass.hv_tea.adapter.recyclerview.ViewHolder;
import info.hellovass.hv_tea.adapter.recyclerview.base.MultiViewTypeAdapter;
import info.hellovass.hv_tea.adapter.recyclerview.wrapper.LoadMoreAdapterWrapper;
import info.hellovass.hv_tea.pullrecycler.PullRecycler;
import info.hellovass.hv_tea.pullrecycler.layoutmanager.MyLinearLayoutManager;
import info.hellovass.hv_tea.pullrecycler.loadmore.DefaultLoadMoreView;
import info.hellovass.hv_tea.pullrecycler.loadmore.ILoadMoreHandler;
import info.hellovass.hv_tea.pullrecycler.refresh.IRefreshHandler;
import info.hellovass.hv_tea.pullrecycler.refresh.IRefreshUIHandler;
import info.hellovass.hvteademo.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hello on 2017/3/12.
 */

public class PeroPullRecyclerTestActivity extends AppCompatActivity {

  @BindView(R.id.pullrecycler) PullRecycler mPullRecycler;

  private LoadMoreAdapterWrapper<String> mLoadMoreAdapterWrapper;

  private List<String> mDataList = new ArrayList<>();

  private Random mRandom = new Random();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_peropullrecycler_test);
    ButterKnife.bind(this);

    initWidgets();
  }

  private void initWidgets() {

    mPullRecycler.setLayoutManager(new MyLinearLayoutManager(this));
    mPullRecycler.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); // 设置分割线

    mPullRecycler.setEnableRefresh(true); // 启用下拉刷新
    mPullRecycler.setRefreshHandler(new IRefreshHandler() { // 设置下拉刷新回调

      @Override public void onRefresh() {

        switch (mRandom.nextInt(2)) {

          case 0:
            simulateRefreshSucceed();
            break;

          case 1:
            simulateRefreshFailed();
            break;
        }
      }
    });

    mPullRecycler.enableLoadMore(true); // 启用 loadmore
    mPullRecycler.setLoadMoreHandler(new ILoadMoreHandler() { // 加载更多回调

      @Override public void onLoadMore() {

        switch (mRandom.nextInt(3)) {

          case 0:
            simulateLoadMoreSucceed();
            break;

          case 1:
            simulateLoadMoreFailed();
            break;

          case 2:
            simulateNoMoreData();
            break;
        }
      }
    });

    // 构造普通的 adapter
    CommonAdapter<String> adapter =
        new CommonAdapter<String>(this, R.layout.listitem_pullrecycler, provideDataSource()) {

          @Override protected void convert(ViewHolder holder, String title, int position) {

            holder.setText(R.id.tv_title, title);
          }
        };
    adapter.setOnItemClickListener(new MultiViewTypeAdapter.OnItemClickListener<String>() {

      @Override public void onItemClick(View view, String entity, int position) {

        Toast.makeText(PeroPullRecyclerTestActivity.this, "点击了第" + position + "项",
            Toast.LENGTH_SHORT).show();
      }
    });
    adapter.setOnItemLongClickListener(new MultiViewTypeAdapter.OnItemLongClickListener<String>() {

      @Override public boolean onItemOnLongClick(View view, String entity, int position) {

        Toast.makeText(PeroPullRecyclerTestActivity.this, "点击了第" + position + "项",
            Toast.LENGTH_SHORT).show();

        return true;
      }
    });

    mLoadMoreAdapterWrapper = new LoadMoreAdapterWrapper<>(adapter); // 用 loadMoreAdapter 装饰

    mPullRecycler.setAdapter(mLoadMoreAdapterWrapper);
    mPullRecycler.setLoadMoreUIHandler(new DefaultLoadMoreView(this)); // 默认 LoadMoreView
  }

  // 模拟刷新成功
  private void simulateRefreshSucceed() {

    new Handler().postDelayed(new Runnable() {

      @Override public void run() {

        mDataList.clear(); // 清除之前的数据

        for (int i = 'A'; i <= 'Z'; i++) {

          mDataList.add((char) i + "");
        }

        Toast.makeText(PeroPullRecyclerTestActivity.this, "获取最新数据成功", Toast.LENGTH_SHORT).show();

        mLoadMoreAdapterWrapper.notifyDataSetChanged();
        mPullRecycler.onRefreshSucceed(true);
      }
    }, 2 * 1000);
  }

  // 模拟刷新失败
  private void simulateRefreshFailed() {

    new Handler().postDelayed(new Runnable() {

      @Override public void run() {

        mPullRecycler.onRefreshFailed(new IRefreshUIHandler() {

          @Override public void onFailed() {

            Toast.makeText(PeroPullRecyclerTestActivity.this, "刷新失败", Toast.LENGTH_SHORT).show();
          }
        });
      }
    }, 2 * 1000);
  }

  // 模拟加载更多操作
  private void simulateLoadMoreSucceed() {

    new Handler().postDelayed(new Runnable() {

      @Override public void run() {

        mDataList.add("加载更多数据" + mRandom.nextInt());
        mLoadMoreAdapterWrapper.notifyDataSetChanged();

        mPullRecycler.onLoadMoreSucceed(true);
      }
    }, 2 * 1000);
  }

  // 模拟加载失败
  private void simulateLoadMoreFailed() {

    new Handler().postDelayed(new Runnable() {

      @Override public void run() {

        mPullRecycler.onLoadMoreFailed("服务器菌懵逼了，请点击重试,阿里嘎多...");
      }
    }, 2 * 1000);
  }

  // 模拟没有更多数据的情况
  private void simulateNoMoreData() {

    new Handler().postDelayed(new Runnable() {

      @Override public void run() {

        mDataList.add("加载更多数据" + mRandom.nextInt());
        mLoadMoreAdapterWrapper.notifyDataSetChanged();

        mPullRecycler.onLoadMoreSucceed(false);
      }
    }, 2 * 1000);
  }

  /**
   * 生产数据源
   */
  private List<String> provideDataSource() {

    for (int i = 'A'; i <= 'Z'; i++) {

      mDataList.add((char) i + "");
    }

    return mDataList;
  }
}
