package vip.xuanhao.integration.views.activitys;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import integration.xuanhao.vip.blurlibrary.BlurTransformation;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.utils.ScreenUtils;
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

    /**
     * 原始图片
     */
    private Bitmap mTempBitmap;
    /**
     * 模糊后的图片
     */
    private Bitmap mFinalBitmap;

    private List<View> views = new ArrayList<>();
    private LayoutInflater inflater;

    private int screeheight;

    private int ImageResure[] = {R.mipmap.guide_bg, R.mipmap.guide_bg_01, R.mipmap.guide_bg_02, R.mipmap.guide_bg_03, R.mipmap.guide_bg_04};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(this);
        initData();
        initView();
        initEvent();
    }

    private void initEvent() {
        mViewPager.addOnPageChangeListener(this);
    }

    private void initData() {
        screeheight = ScreenUtils.getScreenHeight(this);

        for (int i = 0; i < ImageResure.length; i++) {
            View pagerItem = inflater.inflate(R.layout.item_guide, null, false);
            ImageView img_item = (ImageView) pagerItem.findViewById(R.id.img_guide_item);
            Glide.with(this).load(ImageResure[i]).into(img_item);
            views.add(img_item);

        }
    }

    private void initView() {
        mViewPager.setAdapter(new MyViewPagerAdapter());
        mViewPager.setOffscreenPageLimit(views.size());
        initMagicIndicator();

        Glide.with(this).load(ImageResure[0])
                .transform(new BlurTransformation(this))
                .crossFade()
                .into(imgGuideBg);
    }


    private void initMagicIndicator() {
        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(this);
        scaleCircleNavigator.setCircleCount(views.size());
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        scaleCircleNavigator.setSelectedCircleColor(Color.WHITE);
        scaleCircleNavigator.setCircleClickListener(new ScaleCircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(scaleCircleNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        btnGuide.clearAnimation();


        if (position == views.size() - 1) {
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
//            btnGuide.setVisibility(View.GONE);
        }

        Glide.with(this).load(ImageResure[position])
                .transform(new BlurTransformation(this))
                .crossFade()
                .into(imgGuideBg);
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(imgGuideBg, "alpha", 0f, 1f);
        fadeAnim.setDuration(300);
        fadeAnim.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.btn_guide)
    public void onClick() {
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }
    }


}

