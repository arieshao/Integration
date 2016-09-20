package vip.xuanhao.integration.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vip.xuanhao.integration.views.fragments.GroupFragment;
import vip.xuanhao.integration.views.fragments.SquareFragment;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class CommunityContentAdapter extends FragmentPagerAdapter {

    public CommunityContentAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 1:
                return "小组";
            case 0:
            default:
                return "广场";
        }
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 1:
                return new GroupFragment();
            case 0:
            default:
                return new SquareFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
