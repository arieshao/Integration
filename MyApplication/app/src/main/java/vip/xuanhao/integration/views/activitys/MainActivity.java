package vip.xuanhao.integration.views.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;
import com.mikepenz.materialdrawer.Drawer;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.MainPresenter;
import vip.xuanhao.integration.views.BaseActivity;
import vip.xuanhao.integration.views.Iviews.IMainView;
import vip.xuanhao.integration.views.adapters.MainPagerAdapter;
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
    protected void setStatusBar() {
        isFullScreen = true;

        StatusBarUtil.setTranslucentForImageViewInFragment(this,0,null);
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.color_primary_dark),0);
    }

    @Override
    public void initView() {
        viewpagerMain.setScrollable(false);
        viewpagerMain.setAdapter(presenter.getPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setTabData(presenter.getTabData());
        viewpagerMain.setOffscreenPageLimit(presenter.getTabData().size());
//        MainTabHelper.bind(this,viewpagerMain, mTabLayout);




//        drawer = new DrawerBuilder()
//                .withDisplayBelowStatusBar(false)
//                .withActivity(this)
//                .withFullscreen(true)
//                .build();

    }

    @Override
    public void initData() {

    }
    private boolean isFullScreen = false;
    @Override
    public void initEvent() {

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewpagerMain.setCurrentItem(position);
                switch (position) {
                    case 0:
                        isFullScreen = true;
//                        StatusBarUtil.setTranslucentForImageViewInFragment(activity, null);
                        StatusBarUtil.setTranslucentForImageView(MainActivity.this, 0, null);
                        break;
                    default:
                        if (isFullScreen) {
                            Fragment fragment = ((MainPagerAdapter) viewpagerMain.getAdapter()).getItem(position);
                            resetFragmentView(fragment,MainActivity.this);
                        }
                        StatusBarUtil.setColor(MainActivity.this, MainActivity.this.getResources().getColor(R.color.color_primary_dark), 0);
                        break;
                }


            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    public static void resetFragmentView(Fragment fragment,Activity activity) {
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
        return context.getResources().getDimensionPixelSize(resourceId);
    }


    @Override
    public void updateUI() {

    }
}
