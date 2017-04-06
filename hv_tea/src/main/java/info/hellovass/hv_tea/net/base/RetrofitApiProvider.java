package info.hellovass.hv_tea.net.base;

/**
 * Created by hellovass on 2017/4/6.
 */

public interface RetrofitApiProvider {

  <T> T provideApi(Class<T> service);
}
