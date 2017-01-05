package vip.xuanhao.integration.model.network.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import vip.xuanhao.integration.app.base.BaseApplication;

/**
 * Created by Xuanhao on 2016/12/1.
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Response response = chain.proceed(request);
        if (!isNetworkConnected()) {//无网络状态
            request
                    .newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        if (isNetworkConnected()) {
            String cacheControl = request.cacheControl().toString();
            return response
                    .newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();

        } else {
            int maxAge = 60 * 1000;
            return response
                    .newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();


        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) BaseApplication
                .getAppComponent()
                .context()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
