package vip.xuanhao.integration.model.network.net;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

/**
 * Created by Xuanhao on 2016/6/30.
 */

public interface WebApiService {

    String BASE_URL = "http://api.chaiche.chexun.com";

    String IMAGE_BASE_URL = "http://mt.juemei.com";


    @GET("/chaiche/api/recommend/home")
    Observable<String> getHomeData();


    @Headers("Cache-Control: public, max-age=36000")
    @GET("/mm")
    Observable<ResponseBody> getImageData();


}
