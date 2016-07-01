package vip.xuanhao.integration.net;

import android.os.Environment;

import com.orhanobut.logger.BuildConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Xuanhao on 2016/6/30.
 *
 * @author Xuanhao
 * @version 1.0
 *          网络请求统一管理
 */


public class NetManager {

    private static NetManager manager = null;

    private NetManager() {

    }

    public static NetManager getInstance() {
        if (manager == null) {
            synchronized (NetManager.class) {
                if (manager == null) {
                    manager = new NetManager();
                }
            }
        }
        return manager;
    }

    /**
     * OkHttp config
     *
     * @return
     */
    private OkHttpClient client;

    private OkHttpClient getClent() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        if (client == null) {
            synchronized (NetManager.class) {
                if (client == null) {
                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(Environment.getDataDirectory(), "HttpCache"), 1024 * 1024 * 100);
                    client = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(loggingInterceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(10, TimeUnit.SECONDS)// 连接超时时间设置
                            .readTimeout(10, TimeUnit.SECONDS) // 读取超时时间设置
                            .cookieJar(new CookieJar() {// 这里我们使用host name作为cookie保存的key

                                private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                                @Override
                                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                                    cookieStore.put(HttpUrl.parse(url.host()), cookies);
                                }

                                @Override
                                public List<Cookie> loadForRequest(HttpUrl url) {
                                    List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
                                    return cookies != null ? cookies : new ArrayList<Cookie>();
                                }
                            })
                            .build();
                }
            }
        }

        return client;
    }


    /**
     * retrofit Config
     *
     * @param isUseRxJava
     * @return
     */
    private Retrofit retrofitConfig(boolean isUseRxJava) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(WebApiService.BASE_URL)
                .client(getClent())
                .addConverterFactory(JacksonConverterFactory.create());
        if (isUseRxJava)
            builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return builder.build();
    }


    public WebApiService getWebApiService(boolean useRxJava) {

        return retrofitConfig(useRxJava).create(WebApiService.class);
    }


}
