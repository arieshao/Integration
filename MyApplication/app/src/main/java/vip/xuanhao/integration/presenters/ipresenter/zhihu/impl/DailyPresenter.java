package vip.xuanhao.integration.presenters.ipresenter.zhihu.impl;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.orhanobut.logger.Logger;

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
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.zhihu.IDailyNewsView;
import vip.xuanhao.integration.views.activitys.DetailActivity;
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


    public DailyNewsAdapter getAdapter(Context context, IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        if (dailyNewsAdapter == null) {
            dailyNewsAdapter = new DailyNewsAdapter(context, mList, mTopList, iOnRecycleViewItemClickListener);
        }
        return dailyNewsAdapter;
    }


    public void getDataSource() {

        Logger.w("getDataSource is running");
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
                        view.update();
                        view.hiddenLoading();
                        if (firstRequesttag) {
                            view.stopRefresh();
                            view.stopLoad();
                        }
                        firstRequesttag = true;
                        Logger.w("onCompleted is running");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.w("onError is running" + e.toString());
                    }

                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        Logger.w("onNext is running" + dailyListBean.toString());
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


    public void onItemClick(Context mContext, View view, int position) {
        Logger.w(mList.get(position).toString());

        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("id", mList.get(position).getId());
        mContext.startActivity(intent);
    }
}
