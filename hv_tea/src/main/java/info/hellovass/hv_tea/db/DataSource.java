package info.hellovass.hv_tea.db;

import java.util.List;

/**
 * Created by hello on 2017/3/25.
 */

public interface DataSource<T> {

  interface QueryActionCallback<T> {

    void onSucceed(T data);

    void noDataAvailable();
  }

  void add(T data);

  void addAll(List<T> dataList);

  void delete(T data);

  void deleteAll(List<T> dataList);

  void update(T T);

  void updateAll(List<T> POList);

  void queryAllByPage(int pageNum, QueryActionCallback<List<T>> callback);

  void queryAll(QueryActionCallback<List<T>> callback);

  void clear();
}
