package vip.xuanhao.integration.views.activitys;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.lucode.hackware.magicindicator.MagicIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import integration.xuanhao.vip.blurlibrary.BlurTransformation;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.GuidePresenter;
import vip.xuanhao.integration.presenters.IGuidePresenter;
import vip.xuanhao.integration.views.ui.ScaleCircleNavigator;

/**
 * Created by Xuanhao on 2016/9/9.
 */

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.gudie_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.btn_guide)
    Button btnGuide;
    @BindView(R.id.img_guide_bg)
    ImageView imgGuideBg;
    private IGuidePresenter iGuidePresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        iGuidePresenter = new GuidePresenter(this);
        initData();
        initView();
        initEvent();
    }

    private void initEvent() {
        mViewPager.addOnPageChangeListener(this);
    }

    private void initData() {
        iGuidePresenter.getGuideData();
    }

    private void initView() {
        mViewPager.setAdapter(iGuidePresenter.getAdapter());
        mViewPager.setOffscreenPageLimit(iGuidePresenter.getDataSize());
        initMagicIndicator();
        Glide.with(this).load(iGuidePresenter.getGuideData().get(0))
                .transform(new BlurTransformation(this))
                .crossFade()
                .into(imgGuideBg);
    }


    private void initMagicIndicator() {
        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(this);
        scaleCircleNavigator.setCircleCount(iGuidePresenter.getDataSize());
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
        if (position == iGuidePresenter.getDataSize() - 1) {
            btnGuide.setVisibility(View.VISIBLE);
            ObjectAnimator show = ObjectAnimator.ofFloat(btnGuide, "TranslationY", btnGuide.getHeight(), 0f);
            ObjectAnimator alphashow = ObjectAnimator.ofFloat(btnGuide, "alpha", 0f, 1f);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(200);
            animatorSet.play(show).with(alphashow);
            animatorSet.start();
        } else {
            ObjectAnimator hidden = ObjectAnimator.ofFloat(btnGuide, "TranslationY", 0f, btnGuide.getHeight());
            ObjectAnimator alphahidden = ObjectAnimator.ofFloat(btnGuide, "alpha", 1f, 0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(200);
            animatorSet.play(hidden).with(alphahidden);
            animatorSet.start();
            hidden.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    btnGuide.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }

        Glide.with(this).load(iGuidePresenter.getGuideData().get(position))
                .transform(new BlurTransformation(this))
                .crossFade()
                .into(imgGuideBg);
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(imgGuideBg, "alpha", 0f, 1f);
        fadeAnim.setDuration(300);
        fadeAnim.start();
    }


    @Override
    public void onPageScrollStateChanged(int state) {
        magicIndicator.onPageScrollStateChanged(state);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        iGuidePresenter.release();
    }

    @OnClick(R.id.btn_guide)
    public void onClick() {
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
    }

}

