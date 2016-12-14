package vip.xuanhao.integration.model.datasources;

import org.jetbrains.annotations.NotNull;

import rx.Observer;
import vip.xuanhao.integration.model.network.net.apiservice.WebApiService;

/**
 * Created by Xuanhao on 2016/11/10.
 * 网络数据
 */

public interface NetDataSourceHelper {

    class HomeDataSource {

        WebApiService webApiService;

        public HomeDataSource(@NotNull WebApiService webApiService) {
            this.webApiService = webApiService;
        }


        public Observer<String> getHomeNetData() {


            webApiService.getHomeData().subscribe(new Observer<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {

                }
            });


            return null;
        }
    }

}
