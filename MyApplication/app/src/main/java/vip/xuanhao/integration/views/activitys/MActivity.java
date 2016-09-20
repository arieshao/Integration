package vip.xuanhao.integration.views.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.views.fragments.OneFragment;
import vip.xuanhao.integration.views.fragments.SevenFragment;

public class MActivity extends AppCompatActivity {

    @BindView(R.id.titlebar)
    Toolbar titlebar;
    @BindView(R.id.viewpager_content)
    ViewPager viewpagerContent;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        ButterKnife.bind(this);
        setSupportActionBar(titlebar);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewpagerContent.setAdapter(myPagerAdapter);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 1:
                    return new SevenFragment();
                case 0:
                default:
                    return new OneFragment();
            }
        }


        @Override
        public int getCount() {
            return 2;
        }
    }
}
