package vip.xuanhao.integration.views.fragments;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.orhanobut.logger.Logger;

import net.lucode.hackware.magicindicator.MagicIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import integration.xuanhao.vip.blurlibrary.BlurTransformation;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.HomePresenter;
import vip.xuanhao.integration.presenters.ipresenter.IHomePresenter;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.Iviews.IHomeView;
import vip.xuanhao.integration.views.ui.ScaleCircleNavigator;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class HomeFragment extends BaseFragment implements IHomeView, ViewPager.OnPageChangeListener {

    @BindView(R.id.hicvp)
    HorizontalInfiniteCycleViewPager mCycleViewPager;
    @BindView(R.id.img_home_banner_bg)
    ImageView imgHomeBannerBg;
    @BindView(R.id.banner_magic_indicator)
    MagicIndicator bannerMagicIndicator;


    private IHomePresenter homePresenter;

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
        homePresenter = new HomePresenter(mContext);
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
        mCycleViewPager.setAdapter(homePresenter.getBannerAdapter());
        mCycleViewPager.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        mCycleViewPager.setScrollDuration(2500);
        mCycleViewPager.setInterpolator(AnimationUtils.loadInterpolator(getContext(), android.R.anim.overshoot_interpolator));
        mCycleViewPager.setMediumScaled(false);
        mCycleViewPager.setMaxPageScale(0.8F);
        mCycleViewPager.setMinPageScale(0.5F);
        mCycleViewPager.setCenterPageScaleOffset(30.0F);
        mCycleViewPager.setMinPageScaleOffset(5.0F);
        mCycleViewPager.setMinPageScaleOffset(5.0F);
        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(mContext);
        scaleCircleNavigator.setCircleCount(homePresenter.getBannerDataSize());
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        scaleCircleNavigator.setSelectedCircleColor(Color.WHITE);
        bannerMagicIndicator.setNavigator(scaleCircleNavigator);
    }


    @Override
    public void initEvent() {
        mCycleViewPager.addOnPageChangeListener(this);
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
    public void onDestroy() {
        super.onDestroy();
        homePresenter.release();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        bannerMagicIndicator.onPageScrolled(mCycleViewPager.getRealItem(), 0, 1);
    }

    @Override
    public void onPageSelected(int position) {
        bannerMagicIndicator.onPageSelected(mCycleViewPager.getRealItem());
        Logger.w(mCycleViewPager.getRealItem() + "");
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
}
