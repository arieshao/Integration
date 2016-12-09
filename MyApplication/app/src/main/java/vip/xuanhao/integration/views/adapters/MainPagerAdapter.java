package vip.xuanhao.integration.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Xuanhao on 2016/9/13.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> fragments;


    public MainPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }
}
