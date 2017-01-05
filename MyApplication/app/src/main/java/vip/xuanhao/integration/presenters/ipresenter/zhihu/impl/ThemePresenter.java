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
import vip.xuanhao.integration.model.domain.ThemeListBean;
import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.presenters.BasePresenter;
import vip.xuanhao.integration.presenters.ipresenter.zhihu.IThemePresenter;
import vip.xuanhao.integration.utils.RxUtils;
import vip.xuanhao.integration.app.widget.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.zhihu.IThemeViewView;
import vip.xuanhao.integration.views.activitys.zhihu.ThemeDipoleListActivity;
import vip.xuanhao.integration.views.adapters.zhihu.ThemeAdapter;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public class ThemePresenter extends BasePresenter<IThemeViewView> implements IThemePresenter {

    private NetManager netManager;

    private ThemeAdapter themeAdapter;

    private List<ThemeListBean.OthersBean> mList = new ArrayList<>();

    @Inject
    public ThemePresenter(NetManager netManager) {
        this.netManager = netManager;
    }


    public ThemeAdapter getAdapter(Context mContext, IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        if (themeAdapter == null) {
            themeAdapter = new ThemeAdapter(mContext, mList, iOnRecycleViewItemClickListener);
        }
        return themeAdapter;
    }

    public void getDataSource() {
        Subscription subscription = netManager
                .getZhiHuApiService()
                .getThemeList()
                .compose(RxUtils.<ThemeListBean>rxSchedulerHelper())
                .doOnNext(new Action1<ThemeListBean>() {
                    @Override
                    public void call(ThemeListBean themeListBean) {
                        if (!mList.isEmpty()) {
                            mList.clear();
                        }
                    }
                })
                .subscribe(new Observer<ThemeListBean>() {
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
                    public void onNext(ThemeListBean themeListBean) {
                        mList.addAll(themeListBean.getOthers());

                    }
                });
        addSubscriber(subscription);
    }


    public void notifyUpdate() {
        if (themeAdapter != null) {
            themeAdapter.notifyDataSetChanged();
        }
    }


    public void onItemClick(Context mContext, View v, int position) {
        Intent intent = new Intent(mContext, ThemeDipoleListActivity.class);
        intent.putExtra("id", mList.get(position).getId());
        intent.putExtra("name", mList.get(position).getName());
        mContext.startActivity(intent);

        Logger.w(mList.get(position).toString());
    }


    @Override
    public void release() {
        super.release();
        netManager = null;
        themeAdapter = null;
        mList.clear();
        mList = null;
    }
}
