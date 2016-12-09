package vip.xuanhao.integration.views.activitys;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.lucode.hackware.magicindicator.MagicIndicator;

import butterknife.BindView;
import butterknife.OnClick;
import integration.xuanhao.vip.blurlibrary.BlurTransformation;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.GuidePresenter;
import vip.xuanhao.integration.utils.AnimatorHelper;
import vip.xuanhao.integration.utils.ImageLoaderHelper;
import vip.xuanhao.integration.views.BaseActivity;
import vip.xuanhao.integration.views.ui.ScaleCircleNavigator;

/**
 * Created by Xuanhao on 2016/9/9.
 */

public class GuideActivity extends BaseActivity<GuidePresenter> implements ViewPager.OnPageChangeListener {

    private static final String TAG = GuideActivity.class.getSimpleName();

    @BindView(R.id.gudie_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.img_guide_bg)
    ImageView imgGuideBg;
    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.btn_guide)
    Button btnGuide;



    public void initData() {
        presenter.getGuideData();
    }

    public void initView() {
        mViewPager.setAdapter(presenter.getAdapter());
        mViewPager.setOffscreenPageLimit(presenter.getDataSize());
        initMagicIndicator();
        ImageLoaderHelper.loadImage(this
                , presenter.getGuideData().get(0)
                , imgGuideBg
                , new BlurTransformation(this));
    }

    public void initEvent() {
        mViewPager.addOnPageChangeListener(this);
    }

    private void initMagicIndicator() {
        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(this);
        scaleCircleNavigator.setCircleCount(presenter.getDataSize());
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        scaleCircleNavigator.setSelectedCircleColor(Color.WHITE);
        scaleCircleNavigator.setCircleClickListener(new ScaleCircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(scaleCircleNavigator);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        magicIndicator.onPageSelected(position);
        btnGuide.clearAnimation();
        if (position == presenter.getDataSize() - 1) {
            btnGuide.setVisibility(View.VISIBLE);
            ObjectAnimator show = ObjectAnimator.ofFloat(btnGuide, "TranslationY", btnGuide.getHeight(), 0f);
            ObjectAnimator alpha_show = ObjectAnimator.ofFloat(btnGuide, "alpha", 0f, 1f);
            AnimatorHelper.playTogether(show, alpha_show);
        } else {
            ObjectAnimator hidden = ObjectAnimator.ofFloat(btnGuide, "TranslationY", 0f, btnGuide.getHeight());
            ObjectAnimator alpha_hidden = ObjectAnimator.ofFloat(btnGuide, "alpha", 1f, 0f);
            AnimatorHelper.playTogether(hidden, alpha_hidden);
            AnimatorHelper.bindView(hidden, btnGuide);
        }
        ImageLoaderHelper.loadImage(this
                , presenter.getGuideData().get(position)
                , imgGuideBg
                , new BlurTransformation(this));
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(imgGuideBg, "alpha", 0f, 1f);
        fadeAnim.setDuration(300);
        fadeAnim.start();
    }


    @Override
    public void onPageScrollStateChanged(int state) {
        magicIndicator.onPageScrollStateChanged(state);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.release();
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_guide;
    }

    @OnClick({R.id.btn_jump, R.id.btn_guide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:
            case R.id.btn_guide:
                presenter.jump();
                break;
        }
    }
}

