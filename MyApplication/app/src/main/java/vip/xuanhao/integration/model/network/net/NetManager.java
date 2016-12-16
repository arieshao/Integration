package vip.xuanhao.integration.model.network.net;

import android.content.Context;
import android.os.Environment;

import com.orhanobut.logger.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vip.xuanhao.integration.model.network.net.apiservice.WebApiService;
import vip.xuanhao.integration.model.network.net.apiservice.ZhiHuApiService;

/**
 * Created by Xuanhao on 2016/6/30.
 *
 * @author Xuanhao
 * @version 1.0
 *          网络请求统一管理
 */


public class NetManager {


    private Context mContext;

    @Inject
    public NetManager(Context mContext) {
        initClient();
        this.mContext = mContext;
//        Logger.w(mContext.toString());

        File cacheDir = mContext.getCacheDir();

//        Logger.w(cacheDir.getAbsolutePath());
    }

    /**
     * OkHttp config
     *
     * @return
     */
    private OkHttpClient client;

    private OkHttpClient initClient() {


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        if (client == null) {
            synchronized (NetManager.class) {
                if (client == null) {
                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(Environment.getExternalStorageDirectory(), "HttpCache"), 1024 * 1024 * 100);
                    client = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(loggingInterceptor)
                            .addNetworkInterceptor(new CacheInterceptor())
                            .connectTimeout(10, TimeUnit.SECONDS)// 连接超时时间设置
                            .readTimeout(10, TimeUnit.SECONDS) // 读取超时时间设置
                            .retryOnConnectionFailure(true)
//                            .cookieJar(new CookieJar() {// 这里我们使用host name作为cookie保存的key
//
//                                private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
//
//                                @Override
//                                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                                    cookieStore.put(HttpUrl.parse(url.host()), cookies);
//                                }
//
//                                @Override
//                                public List<Cookie> loadForRequest(HttpUrl url) {
//                                    List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
//                                    return cookies != null ? cookies : new ArrayList<Cookie>();
//                                }
//                            })
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
                .client(client)
                .addConverterFactory(GsonConverterFactory.create());
        if (isUseRxJava)
            builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return builder.build();
    }


    public Retrofit getMTRetrofitConfig() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(WebApiService.IMAGE_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return builder.build();
    }


    public WebApiService getWebApiService(boolean useRxJava) {

        return retrofitConfig(useRxJava).create(WebApiService.class);
    }


    public WebApiService getWebApiService() {

        return getMTRetrofitConfig().create(WebApiService.class);
    }


    //__________________________________________________________

    private Retrofit getZhiHuRetrofitConfig() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ZhiHuApiService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return builder.build();
    }



    public ZhiHuApiService getZhiHuApiService() {
        return getZhiHuRetrofitConfig().create(ZhiHuApiService.class);
    }

}
