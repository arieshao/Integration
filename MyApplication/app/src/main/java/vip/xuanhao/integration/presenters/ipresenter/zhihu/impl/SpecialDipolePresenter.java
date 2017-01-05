package vip.xuanhao.integration.presenters.ipresenter.zhihu.impl;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import vip.xuanhao.integration.model.domain.SectionChildListBean;
import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.presenters.BasePresenter;
import vip.xuanhao.integration.utils.RxUtils;
import vip.xuanhao.integration.app.widget.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.zhihu.IZhiHuBaseViewView;
import vip.xuanhao.integration.views.activitys.DetailActivity;
import vip.xuanhao.integration.views.activitys.zhihu.SpecialDipoleActivity;
import vip.xuanhao.integration.views.adapters.zhihu.SpecialChildAdapter;

/**
 * Created by Xuanhao on 2016/12/15.
 */

public class SpecialDipolePresenter extends BasePresenter<IZhiHuBaseViewView> {


    private NetManager netManager;

    private List<SectionChildListBean.StoriesBean> mList = new ArrayList<>();

    private SpecialChildAdapter specialChildAdapter;

    @Inject
    public SpecialDipolePresenter(NetManager netManager) {
        this.netManager = netManager;
    }

    public void getDataSource(int id) {
        Subscription subscription = netManager
                .getZhiHuApiService()
                .getSectionChildList(id)
                .compose(RxUtils.<SectionChildListBean>rxSchedulerHelper())
                .doOnNext(new Action1<SectionChildListBean>() {
                    @Override
                    public void call(SectionChildListBean sectionChildListBean) {

                        if (!mList.isEmpty())
                            mList.clear();
                    }
                })
                .subscribe(new Observer<SectionChildListBean>() {
                    @Override
                    public void onCompleted() {
                        view.update();
                        view.stopLoad();
                        view.stopRefresh();
                        view.hiddenLoading();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SectionChildListBean sectionChildListBean) {
                        mList.addAll(sectionChildListBean.getStories());
                    }
                });
        addSubscriber(subscription);
    }


    public void notifyUpdata() {
        if (specialChildAdapter != null) {
            specialChildAdapter.notifyDataSetChanged();
        }
    }

    public SpecialChildAdapter getAdapter(Context mContext, IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {

        if (specialChildAdapter == null) {
            specialChildAdapter = new SpecialChildAdapter(mContext, mList, iOnRecycleViewItemClickListener);
        }
        return specialChildAdapter;
    }

    public void onItemClick(SpecialDipoleActivity specialDipoleActivity, View view, int position) {
        Intent intent = new Intent(specialDipoleActivity, DetailActivity.class);
        intent.putExtra("id", mList.get(position).getId());
        specialDipoleActivity.startActivity(intent);

    }
}
