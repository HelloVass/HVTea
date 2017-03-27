package info.hellovass.hv_tea.db;

import android.content.Context;
import org.greenrobot.greendao.AbstractDao;

/**
 * Created by hello on 2017/3/27.
 */

public interface DaoFactory<T, K> {

  AbstractDao<T, K> createDBEngine(Context context,String dbName);
}
