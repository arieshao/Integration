package vip.xuanhao.integration.views.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.flyco.tablayout.CommonTabLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.MainPresenter;
import vip.xuanhao.integration.utils.MainTabHelper;
import vip.xuanhao.integration.views.BaseActivity;
import vip.xuanhao.integration.views.Iviews.IMainView;
import vip.xuanhao.integration.views.ui.UnScrollViewPager;


public class MainActivity extends BaseActivity implements IMainView {

    @BindView(R.id.viewpager_main)
    UnScrollViewPager viewpagerMain;
    @BindView(R.id.main_tablayout)
    CommonTabLayout mTabLayout;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        initView();
        initEvent();
    }


    @Override
    public void initView() {
        viewpagerMain.setScrollable(false);
        viewpagerMain.setAdapter(presenter.getPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setTabData(presenter.getTabData());
        MainTabHelper.bind(viewpagerMain, mTabLayout);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void showNotifyCount(int position, boolean hasNotify, int notifyCount) {

    }

}
