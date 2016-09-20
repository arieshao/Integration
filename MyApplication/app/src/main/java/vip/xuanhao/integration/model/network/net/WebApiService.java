package vip.xuanhao.integration.model.network.net;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Xuanhao on 2016/6/30.
 */

public interface WebApiService {

    String BASE_URL = "";


    @FormUrlEncoded
    @POST()
    Observable<String> submit();

}
