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
import vip.xuanhao.integration.model.domain.SectionListBean;
import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.presenters.BasePresenter;
import vip.xuanhao.integration.presenters.ipresenter.zhihu.ISpecialPresenter;
import vip.xuanhao.integration.utils.RxUtils;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.zhihu.ISpecialView;
import vip.xuanhao.integration.views.activitys.zhihu.SpecialDipoleActivity;
import vip.xuanhao.integration.views.adapters.zhihu.SpecialAdapter;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public class SpecialPresenter extends BasePresenter<ISpecialView> implements ISpecialPresenter {

    private NetManager netManager;
    private SpecialAdapter specialAdapter;
    private List<SectionListBean.DataBean> mList = new ArrayList<>();

    @Inject
    public SpecialPresenter(NetManager netManager) {
        this.netManager = netManager;
    }


    public SpecialAdapter getAdapter(Context mContext, IOnRecycleViewItemClickListener clickListener) {
        if (specialAdapter == null) {
            specialAdapter = new SpecialAdapter(mContext, mList, clickListener);
        }
        return specialAdapter;
    }


    public void notifyUpdate() {
        if (specialAdapter != null) {
            specialAdapter.notifyDataSetChanged();
        }
    }

    public void getDataSource() {

        Subscription subscription = netManager
                .getZhiHuApiService()
                .getSectionList()
                .compose(RxUtils.<SectionListBean>rxSchedulerHelper())
                .doOnNext(new Action1<SectionListBean>() {
                    @Override
                    public void call(SectionListBean sectionListBean) {
                        if (!mList.isEmpty())
                            mList.clear();

                    }
                }).subscribe(new Observer<SectionListBean>() {
                    @Override
                    public void onCompleted() {
                        view.update();
                        view.stopRefresh();
                        view.stopLoad();
                        view.hiddenLoading();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SectionListBean sectionListBean) {
                        mList.addAll(sectionListBean.getData());
                    }
                });
        addSubscriber(subscription);
    }

    public void onItemClick(Context mContext, View view, int position) {
        Logger.w(mList.get(position).toString());
        Intent intent = new Intent(mContext, SpecialDipoleActivity.class);
        intent.putExtra("id", mList.get(position).getId());
        intent.putExtra("name", mList.get(position).getName());
        mContext.startActivity(intent);
    }
}
