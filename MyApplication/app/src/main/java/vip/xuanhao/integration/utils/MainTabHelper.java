package vip.xuanhao.integration.utils;

import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Xuanhao on 2016/9/22.
 */

public class MainTabHelper {

    public static void bind(@NotNull final ViewPager viewPager, @NotNull CommonTabLayout mTabLayout) {

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Logger.w(position + "");
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
