package vip.xuanhao.integration.model.network.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Xuanhao on 2016/8/30.
 */

public class ProgressIntercept implements Interceptor {


    private ProgressResponseBody.ProgressListener progressListener;


    public ProgressIntercept(ProgressResponseBody.ProgressListener progressListener) {

        this.progressListener = progressListener;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        Response build = response.newBuilder()
                .body(new ProgressResponseBody(response.body(), progressListener))
                .build();
        return build;
    }
}
