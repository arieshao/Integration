package vip.xuanhao.integration.views.fragments.zhihu;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.ipresenter.zhihu.impl.ZhiHuPresenter;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.Iviews.zhihu.IZhiHUView;

/**
 * Created by Xuanhao on 2016/12/13.
 */

public class ZhiHuFragment extends BaseFragment<ZhiHuPresenter> implements IZhiHUView {


    @BindView(R.id.zhihu_tab)
    TabLayout zhihuTab;
    @BindView(R.id.vp_zhihu_content)
    ViewPager vpZhihuContent;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        vpZhihuContent.setAdapter(presenter.getAdapter(getChildFragmentManager()));
        vpZhihuContent.setOffscreenPageLimit(presenter.getPageSize());
        zhihuTab.setupWithViewPager(vpZhihuContent);
    }

    @Override
    public void initData() {
        presenter.getFragments();
    }

    @Override
    public void initEvent() {

    }
}
