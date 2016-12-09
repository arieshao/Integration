package vip.xuanhao.integration.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;

import org.jetbrains.annotations.NotNull;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.views.adapters.MainPagerAdapter;

/**
 * Created by Xuanhao on 2016/9/22.
 */

public class MainTabHelper {

    private static boolean isFullScreen = false;

    public static void bind(final Activity activity, @NotNull final ViewPager viewPager, @NotNull CommonTabLayout mTabLayout) {

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                Logger.w(position + "");
                switch (position) {
                    case 0:
                        isFullScreen = true;
//                        StatusBarUtil.setTranslucentForImageViewInFragment(activity, null);
                        StatusBarUtil.setTranslucentForImageView(activity, 0, null);
                        break;
                    default:
                        if (isFullScreen) {
                            Fragment fragment = ((MainPagerAdapter) viewPager.getAdapter()).getItem(position);
                            resetFragmentView(fragment,activity);
                        }
                        StatusBarUtil.setColor(activity, activity.getResources().getColor(R.color.color_primary_dark), 0);
                        break;
                }

                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }


    public static void resetFragmentView(Fragment fragment,Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View contentView = activity.findViewById(android.R.id.content);
            if (contentView != null) {
                ViewGroup rootView;
                rootView = (ViewGroup) ((ViewGroup) contentView).getChildAt(0);
                if (rootView.getPaddingTop() != 0) {
                    rootView.setPadding(0, 0, 0, 0);
                }
            }
            if (fragment.getView() != null)
                fragment.getView().setPadding(0, getStatusBarHeight(activity), 0, 0);
        }
    }

    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
