package info.hellovass.hv_tea.db;

import android.content.Context;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;

/**
 * Created by hello on 2017/3/25.
 */

public abstract class BaseDBService<T, K> implements DataSource<T> {

  private static final int DEFAULT_PAGE_SIZE = 10;

  protected AbstractDao<T, K> mAbstractDao;

  public BaseDBService(Context context, String dbName) {

    mAbstractDao = provideDBFactory().createDBEngine(context, dbName);
  }

  @Override public void add(T data) {

    mAbstractDao.insert(data);
  }

  @Override public void addAll(List<T> dataList) {

    mAbstractDao.insertInTx(dataList);
  }

  @Override public void delete(T data) {

    mAbstractDao.delete(data);
  }

  @Override public void deleteAll(List<T> dataList) {

    mAbstractDao.deleteInTx(dataList);
  }

  @Override public void update(T data) {

    mAbstractDao.update(data);
  }

  @Override public void updateAll(List<T> dataList) {

    mAbstractDao.updateInTx(dataList);
  }

  @Override public void queryAllByPage(int pageNum, QueryActionCallback<List<T>> callback) {

    List<T> dataList = mAbstractDao.queryBuilder()
        .offset(pageNum * DEFAULT_PAGE_SIZE)
        .limit(DEFAULT_PAGE_SIZE)
        .list();

    if (dataList == null || dataList.isEmpty()) {

      callback.noDataAvailable();
      return;
    }

    callback.onSucceed(dataList);
  }

  @Override public void queryAll(QueryActionCallback<List<T>> callback) {

    List<T> dataList = mAbstractDao.loadAll();

    if (dataList == null || dataList.isEmpty()) {

      callback.noDataAvailable();
      return;
    }

    callback.onSucceed(dataList);
  }

  @Override public void clear() {

    mAbstractDao.deleteAll();
  }

  public abstract DaoFactory<T, K> provideDBFactory();
}
