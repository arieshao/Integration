package vip.xuanhao.integration.views.fragments.zhihu;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.ipresenter.zhihu.impl.SpecialPresenter;
import vip.xuanhao.integration.views.Iviews.zhihu.ISpecialView;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public class SpecialFragment extends ZhihuBaseFragment<SpecialPresenter> implements ISpecialView {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_zhihu_news;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        zhihuNewsContent.setLayoutManager(gridLayoutManager);
        zhihuNewsContent.setItemAnimator(new DefaultItemAnimator());
        zhihuNewsContent.setAdapter(presenter.getAdapter(mContext, this));
    }

    @Override
    public void initData() {
        presenter.getDataSource();
    }

    @Override
    public void update() {
        presenter.notifyUpdate();
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

    }
}
