package vip.xuanhao.integration.views.activitys.zhihu;

import android.content.Intent;
import android.view.View;

import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import vip.xuanhao.integration.presenters.ipresenter.zhihu.impl.SpecialDipolePresenter;
import vip.xuanhao.integration.views.Iviews.zhihu.ISpecialView;

/**
 * Created by Xuanhao on 2016/12/15.
 */

public class SpecialDipoleActivity extends ZhihuBaseActivity<SpecialDipolePresenter> implements ISpecialView {

    private int id;
    private String name;


    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        presenter.getDataSource(id);
    }


    @Override
    public void getIntentData() {
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        name = intent.getExtras().getString("name");
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        presenter.getDataSource(id);
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        setToolBar(toolbarAllpage, name);
        zhihuNewsContent.setAdapter(presenter.getAdapter(this, this));
    }

    @Override
    public void initData() {
        presenter.getDataSource(id);
    }

    @Override
    public void onItemClick(View view, int position) {
        presenter.onItemClick(this, view, position);
    }

    @Override
    public void hiddenLoading() {

    }

    @Override
    public void update() {
        presenter.notifyUpdata();
    }
}
