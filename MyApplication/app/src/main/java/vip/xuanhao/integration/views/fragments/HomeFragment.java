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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import integration.xuanhao.vip.blurlibrary.BlurTransformation;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.HomePresenter;
import vip.xuanhao.integration.presenters.IHomePresenter;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.Iviews.IHomeView;
import vip.xuanhao.integration.views.adapters.HorizontalPagerAdapter;
import vip.xuanhao.integration.views.ui.ScaleCircleNavigator;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class HomeFragment extends BaseFragment implements IHomeView {

    @BindView(R.id.hicvp)
    HorizontalInfiniteCycleViewPager hicvp;
    @BindView(R.id.img_home_banner_bg)
    ImageView imgHomeBannerBg;
    @BindView(R.id.banner_magic_indicator)
    MagicIndicator bannerMagicIndicator;

    private HorizontalPagerAdapter adapter;

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
    }


    @Override
    public void initData() {
        final Integer img[] = {R.mipmap.bunner_01, R.mipmap.bunner_02, R.mipmap.bunner_03, R.mipmap.bunner_04, R.mipmap.bunner_05};
        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers, img);
        adapter = new HorizontalPagerAdapter(mContext, integers);
        hicvp.setAdapter(adapter);
//        hicvp.setScrollDuration(2000);
        hicvp.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);


        hicvp.setScrollDuration(2500);
        hicvp.setInterpolator(
                AnimationUtils.loadInterpolator(getContext(), android.R.anim.overshoot_interpolator)
        );
        hicvp.setMediumScaled(false);
        hicvp.setMaxPageScale(0.8F);
        hicvp.setMinPageScale(0.5F);
        hicvp.setCenterPageScaleOffset(30.0F);
        hicvp.setMinPageScaleOffset(5.0F);
        hicvp.setMinPageScaleOffset(5.0F);
//        hicvp.setOnInfiniteCyclePageTransformListener();

        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(mContext);
        scaleCircleNavigator.setCircleCount(integers.size());
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        scaleCircleNavigator.setSelectedCircleColor(Color.WHITE);
        bannerMagicIndicator.setNavigator(scaleCircleNavigator);

        hicvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                bannerMagicIndicator.onPageScrolled(hicvp.getRealItem(), 0, 1);
            }

            @Override
            public void onPageSelected(int position) {
                bannerMagicIndicator.onPageSelected(hicvp.getRealItem());

                Logger.w(hicvp.getRealItem() + "");
                Glide.with(mContext).load(img[hicvp.getRealItem()])
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
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            hicvp.startAutoScroll(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        hicvp.stopAutoScroll();
    }
}
