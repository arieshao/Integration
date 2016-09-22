package vip.xuanhao.integration.views.activitys;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.lucode.hackware.magicindicator.MagicIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import integration.xuanhao.vip.blurlibrary.BlurTransformation;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.GuidePresenter;
import vip.xuanhao.integration.presenters.ipresenter.IGuidePresenter;
import vip.xuanhao.integration.utils.AnimatorHelper;
import vip.xuanhao.integration.utils.ImageLoaderHelper;
import vip.xuanhao.integration.views.ui.ScaleCircleNavigator;

/**
 * Created by Xuanhao on 2016/9/9.
 */

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

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
        ImageLoaderHelper.loadImage(this
                , iGuidePresenter.getGuideData().get(0)
                , imgGuideBg
                , new BlurTransformation(this));
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
            AnimatorHelper.playTogether(show, alphashow);
        } else {
            ObjectAnimator hidden = ObjectAnimator.ofFloat(btnGuide, "TranslationY", 0f, btnGuide.getHeight());
            ObjectAnimator alphahidden = ObjectAnimator.ofFloat(btnGuide, "alpha", 1f, 0f);
            AnimatorHelper.playTogether(hidden, alphahidden);
            AnimatorHelper.bindView(hidden, btnGuide);
        }
        ImageLoaderHelper.loadImage(this
                , iGuidePresenter.getGuideData().get(position)
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
        iGuidePresenter.onResume(this, TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        iGuidePresenter.onPause(this, TAG);
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

    @OnClick({R.id.btn_jump, R.id.btn_guide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:
            case R.id.btn_guide:
                iGuidePresenter.jump();
                break;
        }
    }
}

