package integration.xuanhao.vip.testdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Xuanhao on 2016/12/2.
 */

public class TowFragment extends Fragment {

    private ViewPager innerViewPager;

    private Integer[] imgs = {R.mipmap.bunner_01, R.mipmap.bunner_02, R.mipmap.bunner_03};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tow_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        innerViewPager = (ViewPager) view.findViewById(R.id.inner);
        innerViewPager.setAdapter(new InnerViewPagerAdapter(getChildFragmentManager()));
    }


    public class InnerViewPagerAdapter extends FragmentStatePagerAdapter {

        public InnerViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    return new FourFragment();
                default:
                    return new ThreeFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
