package vip.xuanhao.integration.views.activitys.zhihu;

import android.content.Intent;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import vip.xuanhao.integration.app.common.Commons;
import vip.xuanhao.integration.presenters.ipresenter.zhihu.impl.ThemeDipolePresenter;
import vip.xuanhao.integration.views.Iviews.zhihu.IThemeDipoleView;


/**
 * Created by Xuanhao on 2016/12/15.
 * 知乎二级列表
 */

public class ThemeDipoleListActivity extends ZhihuBaseActivity<ThemeDipolePresenter> implements IThemeDipoleView {


    private int id;
    private String name;



    @Override
    public void getIntentData() {
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        name = intent.getStringExtra("name");
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
    public void onLoad(BaseFooterView baseFooterView) {
        presenter.getDataSource(id);
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        presenter.getDataSource(id);
    }

    @Override
    public void hiddenLoading() {

    }

    @Override
    public void update() {
        presenter.notifyUpDate();
    }

    @Override
    public void onItemClick(View view, int position) {
        presenter.onItemClick(this, view, position);
    }

}
