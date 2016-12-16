package vip.xuanhao.integration.views.fragments.zhihu;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import vip.xuanhao.integration.presenters.ipresenter.zhihu.impl.DailyPresenter;
import vip.xuanhao.integration.views.Iviews.zhihu.IDailyNewsView;

/**
 * Created by Xuanhao on 2016/12/13.
 */

public class DailyNewFragment extends ZhihuBaseFragment<DailyPresenter> implements IDailyNewsView {


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        zhihuNewsContent.setLayoutManager(linearLayoutManager);
        zhihuNewsContent.setItemAnimator(new DefaultItemAnimator());
        zhihuNewsContent.setAdapter(presenter.getAdapter(mContext, this));
    }

    @Override
    public void initData() {
        presenter.getDataSource();
    }


    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        presenter.getDataSource();
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        presenter.getDataSource();
    }

    @Override
    public void onItemClick(View view, int position) {
        presenter.onItemClick(mContext, view, position);
    }

    @Override
    public void showLoadDialog() {

    }

    @Override
    public void update() {
        presenter.notifyUpdate();
    }
}
