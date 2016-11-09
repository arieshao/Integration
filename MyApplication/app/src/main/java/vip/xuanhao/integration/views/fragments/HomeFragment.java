package vip.xuanhao.integration.views.fragments;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import net.lucode.hackware.magicindicator.MagicIndicator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import integration.xuanhao.vip.blurlibrary.BlurTransformation;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.HomePresenter;
import vip.xuanhao.integration.utils.AppBarLayoutHelper;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.IHomeView;
import vip.xuanhao.integration.views.ui.NormalHeaderView;
import vip.xuanhao.integration.views.ui.ScaleCircleNavigator;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class HomeFragment extends BaseFragment implements IHomeView, ViewPager.OnPageChangeListener, BaseHeaderView.OnRefreshListener, IOnRecycleViewItemClickListener {

    @BindView(R.id.hicvp)
    HorizontalInfiniteCycleViewPager mCycleViewPager;
    @BindView(R.id.img_home_banner_bg)
    ImageView imgHomeBannerBg;
    @BindView(R.id.banner_magic_indicator)
    MagicIndicator bannerMagicIndicator;
    @BindView(R.id.appbarlayout_home)
    AppBarLayout appbarlayoutHome;
    @BindView(R.id.ctl_titlebar)
    CollapsingToolbarLayout ctlTitlebar;
    @BindView(R.id.rec_home_content)
    RecyclerView recHomeContent;
    @BindView(R.id.home_refresh_header)
    NormalHeaderView homeRefreshHeader;

    @Inject
    HomePresenter homePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeRootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, homeRootView);
        return homeRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getFragmentComponent().inject(this);
        initData();
        initView();
        initEvent();
    }

    @Override
    public void initData() {
        homePresenter.getDataSource();
    }

    @Override
    public void initView() {

        // 初始化banner
        mCycleViewPager.setAdapter(homePresenter.getBannerAdapter());
        mCycleViewPager.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        mCycleViewPager.setScrollDuration(2500);
        mCycleViewPager.setInterpolator(AnimationUtils.loadInterpolator(mContext, android.R.anim.overshoot_interpolator));
        mCycleViewPager.setMediumScaled(false);
        mCycleViewPager.setMaxPageScale(0.8F);
        mCycleViewPager.setMinPageScale(0.5F);
        mCycleViewPager.setCenterPageScaleOffset(30.0F);
        mCycleViewPager.setMinPageScaleOffset(5.0F);
        mCycleViewPager.setMinPageScaleOffset(5.0F);

        //初始化指示器
        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(mContext);
        scaleCircleNavigator.setCircleCount(homePresenter.getBannerDataSize());
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        scaleCircleNavigator.setSelectedCircleColor(Color.WHITE);
        bannerMagicIndicator.setNavigator(scaleCircleNavigator);
        initContent();
    }

    private void initContent() {
        recHomeContent.setLayoutManager(new LinearLayoutManager(mContext));
        recHomeContent.setAdapter(homePresenter.getHomeAdapter(this));
    }


    @Override
    public void initEvent() {
        mCycleViewPager.addOnPageChangeListener(this);
        homeRefreshHeader.setOnRefreshListener(this);
        appbarlayoutHome.addOnOffsetChangedListener(new AppBarLayoutHelper.AppBarStateChangeListener() {
            @Override
            public void onOffsetChanged(int verticalOffset) {
                switch (verticalOffset) {
                    case AppBarLayoutHelper.AppBarStateChangeListener.STATE_EXPANDED:
                        mCycleViewPager.startAutoScroll(true);
                        //open
                        break;
                    case AppBarLayoutHelper.AppBarStateChangeListener.STATE_COLLAPSED:
                        mCycleViewPager.stopAutoScroll();
                        //closed
                        break;
                    case AppBarLayoutHelper.AppBarStateChangeListener.STATE_IDLE:
                        mCycleViewPager.stopAutoScroll();
                        //middle
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            mCycleViewPager.startAutoScroll(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mCycleViewPager.stopAutoScroll();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        bannerMagicIndicator.onPageScrolled(mCycleViewPager.getRealItem(), 0, 1);
    }

    @Override
    public void onPageSelected(int position) {
        bannerMagicIndicator.onPageSelected(mCycleViewPager.getRealItem());
        Glide.with(mContext).load(homePresenter.getDataSource().get(mCycleViewPager.getRealItem()))
                .transform(new BlurTransformation(mContext))
                .crossFade()
                .into(imgHomeBannerBg);
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(imgHomeBannerBg, "alpha", 0f, 1f);
        fadeAnim.setDuration(300);
        fadeAnim.start();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        bannerMagicIndicator.onPageScrollStateChanged(state);
    }

    @Override
    public void onItemClick(View view, int position) {
        homePresenter.onItemClick(position);
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO  request data
                homeRefreshHeader.stopRefresh();
            }
        }, 2000);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        homePresenter.release();
    }
}
