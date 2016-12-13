package vip.xuanhao.integration.views.activitys;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.jaeger.library.StatusBarUtil;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.MainPresenter;
import vip.xuanhao.integration.utils.MainTabHelper;
import vip.xuanhao.integration.views.BaseActivity;
import vip.xuanhao.integration.views.Iviews.IMainView;
import vip.xuanhao.integration.views.ui.UnScrollViewPager;


public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.viewpager_main)
    UnScrollViewPager viewpagerMain;
    @BindView(R.id.main_tablayout)
    CommonTabLayout mTabLayout;
    private Drawer drawer;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }


    @Override
    @TargetApi(19)
    protected void setStatusBar() {
        StatusBarUtil.setColor(this,getResources().getColor(R.color.color_primary_dark),0);
    }

    @Override
    public void initView() {
        viewpagerMain.setScrollable(false);
        viewpagerMain.setAdapter(presenter.getPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setTabData(presenter.getTabData());
        viewpagerMain.setOffscreenPageLimit(presenter.getTabData().size());
        MainTabHelper.bind(viewpagerMain, mTabLayout);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .build();
        drawer.getDrawerLayout().setStatusBarBackgroundColor(getResources().getColor(R.color.color_primary_dark));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }


    public static void resetFragmentView(Fragment fragment, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View contentView = activity.findViewById(android.R.id.content);
            if (contentView != null) {
                ViewGroup rootView;
                rootView = (ViewGroup) ((ViewGroup) contentView).getChildAt(0);
                if (rootView.getPaddingTop() != 0) {
                    rootView.setPadding(0, 0, 0, 0);
                }
            }
            if (fragment.getView() != null)
                fragment.getView().setPadding(0, getStatusBarHeight(activity), 0, 0);
        }
    }

    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(resourceId);

        Logger.w(dimensionPixelSize + "O");
        return dimensionPixelSize;
    }


    @Override
    public void updateUI() {

    }
}
