package info.hellovass.hv_tea.net.base;

import retrofit2.Retrofit;

/**
 * 产品类，包装了一个 Retrofit
 */
public abstract class RetrofitWrapper {

  protected Retrofit mRetrofit;

  public RetrofitWrapper(RetrofitWrapperFactory retrofitWrapperFactory) {

    mRetrofit = new Retrofit.Builder().baseUrl(retrofitWrapperFactory.provideBaseUrl())
        .client(retrofitWrapperFactory.createOkHttpClient())
        .addConverterFactory(retrofitWrapperFactory.createGsonConverterFactory())
        .build();
  }

  public Retrofit getRetrofit() {

    return mRetrofit;
  }
}
