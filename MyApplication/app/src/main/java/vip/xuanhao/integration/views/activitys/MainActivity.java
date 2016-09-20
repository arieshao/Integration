package vip.xuanhao.integration.views.activitys;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.views.BaseActivity;
import vip.xuanhao.integration.views.Iviews.IMainView;
import vip.xuanhao.integration.views.adapters.MainPagerAdapter;
import vip.xuanhao.integration.views.ui.UnScrollViewPager;


public class MainActivity extends BaseActivity implements IMainView, ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager_main)
    UnScrollViewPager viewpagerMain;
    @BindView(R.id.ntb_horizontal)
    NavigationTabBar ntbHorizontal;
    private MainPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }


    @Override
    public void showNotifyCount(int position, boolean hasNotify, int notifyCount) {

    }

    @Override
    public void initView() {
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewpagerMain.setScrollable(false);
        viewpagerMain.setAdapter(pagerAdapter);

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.home_n),
                        Color.parseColor("#cc33475f"))
                        .selectedIcon(getResources().getDrawable(R.mipmap.home_p))
                        .title("首页")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.video_n),
                        Color.parseColor("#cc33475f"))
                        .selectedIcon(getResources().getDrawable(R.mipmap.video_p))
                        .title("视频")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.commonty_n),
                        Color.parseColor("#cc33475f"))
                        .selectedIcon(getResources().getDrawable(R.mipmap.commonty_p))
                        .title("社区")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.personal_n),
                        Color.parseColor("#cc33475f"))
                        .selectedIcon(getResources().getDrawable(R.mipmap.personal_p))
                        .title("我")
                        .build()
        );

        ntbHorizontal.setModels(models);
        ntbHorizontal.setViewPager(viewpagerMain);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        ntbHorizontal.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {// "#3F51B5" "#303F9F"
        if (position == 3) {
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), Color.parseColor("#3F51B5"), Color.parseColor("#303F9F"));
            colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    int color = (int) animator.getAnimatedValue();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(color);
                    }
                }
            });
            colorAnimation.start();
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
