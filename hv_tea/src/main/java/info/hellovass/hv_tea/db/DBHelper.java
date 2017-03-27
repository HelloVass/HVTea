package info.hellovass.hv_tea.db;

import android.content.Context;
import info.hellovass.hv_tea.db.greendao.DaoMaster;
import info.hellovass.hv_tea.db.greendao.DaoSession;

/**
 * Created by hello on 2017/3/25.
 */

public final class DBHelper {

  private static DBHelper sDBHelper;

  private DaoMaster mDaoMaster;

  private DaoSession mDaoSession;

  public static DBHelper getInstance(Context context, String dbName) {

    if (sDBHelper == null) {
      sDBHelper = new DBHelper(context, dbName);
    }

    return sDBHelper;
  }

  public DaoSession getDaoSession() {

    return mDaoSession;
  }

  private DBHelper(Context context, String dbName) {

    DaoMaster.DevOpenHelper devOpenHelper =
        new DaoMaster.DevOpenHelper(context.getApplicationContext(), dbName, null);
    mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
    mDaoSession = mDaoMaster.newSession();
  }
}
