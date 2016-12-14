package vip.xuanhao.integration.presenters.ipresenter.zhihu.impl;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import vip.xuanhao.integration.model.domain.DailyListBean;
import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.presenters.BasePresenter;
import vip.xuanhao.integration.utils.RxUtils;
import vip.xuanhao.integration.views.Iviews.zhihu.IDailyNewsView;
import vip.xuanhao.integration.views.adapters.zhihu.DailyNewsAdapter;

/**
 * Created by Xuanhao on 2016/12/13.
 */

public class DailyPresenter extends BasePresenter<IDailyNewsView> {


    private NetManager netManager;
    private DailyNewsAdapter dailyNewsAdapter;
    private List<DailyListBean.StoriesBean> mList = new ArrayList<>();
    private List<DailyListBean.TopStoriesBean> mTopList = new ArrayList<>();

    private boolean firstRequesttag = false;

    @Inject
    public DailyPresenter(NetManager netManager) {
        this.netManager = netManager;
    }


    public DailyNewsAdapter getAdapter(Context context) {
        if (dailyNewsAdapter == null) {
            dailyNewsAdapter = new DailyNewsAdapter(context, mList, mTopList);
        }
        return dailyNewsAdapter;
    }


    public void getDataSource() {
        Subscription subscription = netManager
                .getZhiHuApiService()
                .getDailyList()
                .compose(RxUtils.<DailyListBean>rxSchedulerHelper())
                .doOnNext(new Action1<DailyListBean>() {
                    @Override
                    public void call(DailyListBean dailyListBean) {
                        mList.clear();
                        mTopList.clear();
                    }
                })
                .subscribe(new Observer<DailyListBean>() {
                    @Override
                    public void onCompleted() {
                        if (firstRequesttag) {
                            view.stopRefresh();
                            view.stopLoad();
                        }
                        firstRequesttag = true;
                        view.update();
                        view.hiddenLoading();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        mList.addAll(dailyListBean.getStories());
                        mTopList.addAll(dailyListBean.getTop_stories());
                    }
                });

        addSubscriber(subscription);
    }


    public void notifyUpdate() {
        if (dailyNewsAdapter != null) {
            dailyNewsAdapter.notifyDataSetChanged();
        }
    }
}
