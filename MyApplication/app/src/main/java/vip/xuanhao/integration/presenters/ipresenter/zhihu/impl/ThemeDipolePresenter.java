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
import vip.xuanhao.integration.model.domain.ThemeChildListBean;
import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.presenters.BasePresenter;
import vip.xuanhao.integration.presenters.ipresenter.zhihu.IThemeDipolePresenter;
import vip.xuanhao.integration.utils.RxUtils;
import vip.xuanhao.integration.app.widget.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.zhihu.IThemeDipoleViewView;
import vip.xuanhao.integration.views.activitys.DetailActivity;
import vip.xuanhao.integration.views.adapters.zhihu.ThemeChildAdapter;

/**
 * Created by Xuanhao on 2016/12/15.
 */

public class ThemeDipolePresenter extends BasePresenter<IThemeDipoleViewView> implements IThemeDipolePresenter {


    private NetManager netManager;
    private List<ThemeChildListBean.StoriesBean> mList = new ArrayList<>();
    private ThemeChildAdapter themeChildAdapter;


    @Inject
    public ThemeDipolePresenter(NetManager netManager) {
        this.netManager = netManager;
    }


    public void getDataSource(int id) {

        Subscription subscription = netManager
                .getZhiHuApiService()
                .getThemeChildList(id)
                .compose(RxUtils.<ThemeChildListBean>rxSchedulerHelper())
                .doOnNext(new Action1<ThemeChildListBean>() {
                    @Override
                    public void call(ThemeChildListBean themeChildListBean) {
                        if (!mList.isEmpty()) {
                            mList.clear();
                        }
                    }
                })
                .subscribe(new Observer<ThemeChildListBean>() {
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
                    public void onNext(ThemeChildListBean themeChildListBean) {
                        mList.addAll(themeChildListBean.getStories());
                    }
                });
        addSubscriber(subscription);
    }


    public ThemeChildAdapter getAdapter(Context mContext, IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        if (themeChildAdapter == null) {
            themeChildAdapter = new ThemeChildAdapter(mContext, mList, iOnRecycleViewItemClickListener);
        }
        return themeChildAdapter;
    }

    public void onItemClick(Context context, View v, int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id", mList.get(position).getId());
        context.startActivity(intent);

    }

    public void notifyUpDate() {
        if (themeChildAdapter != null) {
            themeChildAdapter.notifyDataSetChanged();
        }
    }
}
