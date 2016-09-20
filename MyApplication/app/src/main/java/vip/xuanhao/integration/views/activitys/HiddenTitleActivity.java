package vip.xuanhao.integration.views.activitys;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.views.fragments.FiveFragment;
import vip.xuanhao.integration.views.fragments.FourFragment;
import vip.xuanhao.integration.views.fragments.OneFragment;
import vip.xuanhao.integration.views.fragments.ThreeFragment;
import vip.xuanhao.integration.views.fragments.TowFragment;

/**
 * Created by Xuanhao on 2016/8/8.
 */

public class HiddenTitleActivity extends FragmentActivity {


    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_01);
        ButterKnife.bind(this);

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 4:
                        return new FiveFragment();
                    case 3:
                        return new FourFragment();
                    case 2:
                        return new ThreeFragment();
                    case 1:
                        return new TowFragment();
                    case 0:
                    default:
                        return new OneFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 4:
                        return "FiveFragment()";
                    case 3:
                        return "FourFragment()";
                    case 2:
                        return "ThreeFragment()";
                    case 1:
                        return "TowFragment()";
                    case 0:
                    default:
                        return "OneFragment()";
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        tabs.setupWithViewPager(viewpager);
    }
}
