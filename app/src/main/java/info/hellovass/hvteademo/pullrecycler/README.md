# PullRecycler

## 使用介绍

### 下拉刷新

Step1. 启用下拉刷新

```java

	mPullRecycler.setEnableRefresh(true); // 启用下拉刷新 
```

Step2. 设置相应回调

```java

	 mPullRecycler.setRefreshHandler(new IRefreshHandler() { // 下拉刷新回调

      @Override public void onRefresh() {

      }
    });

```

Step3. 在刷新成功或者失败之后，调用相应的 API


```java
	
	/**
   	* 刷新成功
   	*
   	* @param hasMore true 表示还有更多数据
   	*/
  	void onRefreshSucceed(boolean hasMore);

  	/**
   	* 刷新失败
  	*
   	* @param refreshUIHandler 刷新失败的回调接口
   	*/
  	void onRefreshFailed(IRefreshUIHandler refreshUIHandler);

```

具体如何使用请参考 Demo


### 加载更多

Step1. 启用加载更多

```java

	mPullRecycler.enableLoadMore(true); // 启用 loadmore

```

Step2. 设置相应回调

```java

	mPullRecycler.setLoadMoreHandler(new ILoadMoreHandler() { // 加载更多回调

      @Override public void onLoadMore() {

      }
    });

```

Step3. 设置 loadMoreView

Note：因为实现上的一些考虑，setLoadMoreUIHandler 方法需要在 setAdapter 后调用，否则代码会抛出运行时异常，注意检查。

```java


    // 构造一个 adapter
    CommonAdapter<String> adapter =
        new CommonAdapter<String>(this, R.layout.listitem_pullrecycler, provideDataSource()) {

          @Override protected void convert(ViewHolder holder, String title, int position) {

            holder.setText(R.id.tv_title, title);
          }
        };

    mLoadMoreAdapterWrapper = new LoadMoreAdapterWrapper<>(adapter); // 使用 LoadMoreAdapterWrapper 装饰它

    mPullRecycler.setAdapter(mLoadMoreAdapterWrapper); // set Adapter
    mPullRecycler.setLoadMoreUIHandler(new DefaultLoadMoreView(this)); // 设置默认 LoadMoreView，注意在 setAdapter 后调用
```

Step4. 调用相应的 API


```java

 	/**
   	* 加载更多成功
   	*
   	* @param hasMore true 表示还有更多数据
   	*/
  	void onLoadMoreSucceed(boolean hasMore);

  	/**
   	* 加载更多失败
   	*
   	* @param errorMsg 错误信息
   	*/
  	void onLoadMoreFailed(String errorMsg);
```

具体使用还是请参考 demo 


### 支持点击事件,由 adapter 库提供

```java

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

```




