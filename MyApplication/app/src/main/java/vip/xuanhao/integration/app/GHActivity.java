package vip.xuanhao.integration.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.app.fragments.OneFragment;
import vip.xuanhao.integration.app.fragments.SixFragment;

public class GHActivity extends AppCompatActivity {

    @BindView(R.id.tab_title)
    TabLayout tabTitle;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gh);
        ButterKnife.bind(this);


        initView();
    }

    private void initView() {
        vpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 1:
                        return new OneFragment();
                    case 0:
                    default:
                        return new SixFragment();
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "挂号";
                    case 1:
                    default:
                        return "说明";
                }
            }
        });
        tabTitle.setupWithViewPager(vpContent);
    }
}
