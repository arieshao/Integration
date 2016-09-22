package vip.xuanhao.integration.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

import net.lucode.hackware.magicindicator.MagicIndicator;

/**
 * Created by Xuanhao on 2016/9/22.
 */

@Deprecated
public class ViewPagerHelper {


    public static void GuidePagerHelper(ViewPager viewPager, View... views) {


        MagicIndicator view = (MagicIndicator) views[0];


        if (viewPager == null) {
            return;
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
