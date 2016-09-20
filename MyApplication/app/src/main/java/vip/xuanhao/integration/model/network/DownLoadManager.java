package vip.xuanhao.integration.model.network;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Xuanhao on 2016/8/18.
 */

public class DownLoadManager {

    private static DownLoadManager downLoadManager = null;

    private static Retrofit retrofit;

    private DownLoadManager() {
        String baseUrl = "http://wap.shouji.com.cn";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public static IDownLoadApiService getDownloadService() {
        return retrofit.create(IDownLoadApiService.class);
    }

    public static DownLoadManager getInstance() {
        if (downLoadManager == null) {
            synchronized (DownLoadManager.class) {
                if (downLoadManager == null) {
                    downLoadManager = new DownLoadManager();
                }
            }
        }
        return downLoadManager;
    }

    public interface IDownLoadApiService {


        @GET("/wap/wdown/soft")
            //?id=30165"
        Observable<ResponseBody> downLoad(@Query("id") String id);

    }

    public interface ProgressListener {

        /**
         * 下载
         *
         * @param totle    总长度
         * @param progress 下载进度
         * @param finish   是否下载完成
         */
        void onProgress(long totle, long progress, boolean finish);
    }

}
