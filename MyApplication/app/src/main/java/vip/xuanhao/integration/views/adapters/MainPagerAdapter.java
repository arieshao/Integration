package vip.xuanhao.integration.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vip.xuanhao.integration.views.fragments.CommunityFragment;
import vip.xuanhao.integration.views.fragments.HomeFragment;
import vip.xuanhao.integration.views.fragments.PersonalFragment;
import vip.xuanhao.integration.views.fragments.VideoFragment;

/**
 * Created by Xuanhao on 2016/9/13.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {


    private static final int HOMEFRAGMENT = 0;
    private static final int VIDEOFRAGMENT = 1;
    private static final int COMMUNITYFRAGMENT = 2;
    private static final int PERSONALFRAGMENT = 3;


    private static final int TOTLEPAGESIZE = 4;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case PERSONALFRAGMENT:
                return new PersonalFragment();
            case COMMUNITYFRAGMENT:
                return new CommunityFragment();
            case VIDEOFRAGMENT:
                return new VideoFragment();
            case HOMEFRAGMENT:
            default:
                return new HomeFragment();
        }
    }




    @Override
    public int getCount() {
        return TOTLEPAGESIZE;
    }
}
