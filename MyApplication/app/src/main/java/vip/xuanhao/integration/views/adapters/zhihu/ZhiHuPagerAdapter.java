package vip.xuanhao.integration.views.adapters.zhihu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Xuanhao on 2016/12/13.
 */

public class ZhiHuPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    private String[] titles = new String[]{"日报", "主题", "专栏", "热门"};

    public ZhiHuPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null && !fragments.isEmpty() ? fragments.size() : 0;
    }
}
