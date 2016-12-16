package vip.xuanhao.integration.presenters.ipresenter;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import vip.xuanhao.integration.model.domain.ZhihuDetailBean;
import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.presenters.BasePresenter;
import vip.xuanhao.integration.utils.RxUtils;
import vip.xuanhao.integration.views.Iviews.IDetailView;

/**
 * Created by Xuanhao on 2016/12/16.
 */

public class DetailPresenter extends BasePresenter<IDetailView> implements IDetailPresenter {


    private NetManager netManager;

    @Inject
    public DetailPresenter(NetManager netManager) {
        this.netManager = netManager;
    }


    public void getDataSource(int id) {

        Subscription subscription = netManager
                .getZhiHuApiService()
                .getDetailInfo(id)
                .compose(RxUtils.<ZhihuDetailBean>rxSchedulerHelper())
                .subscribe(new Observer<ZhihuDetailBean>() {
                    @Override
                    public void onCompleted() {
                        view.hiddenLoading();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZhihuDetailBean zhihuDetailBean) {
                        view.update(zhihuDetailBean);
                    }
                });
        addSubscriber(subscription);
    }
}
