package info.hellovass.hv_tea.net.base;

/**
 * RetrofitWrapper 商店类，创建所需的 ApiService
 */
public abstract class RetrofitWrapperStore {

  protected abstract RetrofitWrapper createRetrofitWrapper();

  public abstract <T> T createApiService(Class<T> service);
}
