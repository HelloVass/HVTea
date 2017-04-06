package info.hellovass.hv_tea.net.base;

/**
 * RetrofitWrapper 商店类
 */
public abstract class RetrofitWrapperStore implements RetrofitApiProvider {

  protected abstract RetrofitWrapper createRetrofitWrapper();
}
