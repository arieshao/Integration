package vip.xuanhao.integration.model.network.net;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Xuanhao on 2016/6/30.
 */

public interface WebApiService {

    String BASE_URL = "http://api.chaiche.chexun.com";


    @GET("/chaiche/api/recommend/home")
    Observable<String> getHomeData();

}
