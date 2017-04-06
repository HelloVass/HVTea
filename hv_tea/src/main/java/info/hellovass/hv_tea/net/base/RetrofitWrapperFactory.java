package info.hellovass.hv_tea.net.base;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * Created by hellovass on 2017/4/6.
 */

/**
 * RetrofitWrapper 抽象工厂，定义 RetrofitWrapper 需要的原料
 */
public interface RetrofitWrapperFactory {

  String provideBaseUrl();

  OkHttpClient createOkHttpClient();

  Converter.Factory createGsonConverterFactory();

  CallAdapter.Factory createRxJavaCallAdapter();
}
