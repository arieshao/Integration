package integration.xuanhao.vip.testdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {


    private ParentViewPager viewPager;

    private Button btn01, btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        initEvent();
    }

    private void initEvent() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
    }

    private void initView() {

        btn01 = (Button) findViewById(R.id.btn_1);
        btn02 = (Button) findViewById(R.id.btn_2);
        viewPager = (ParentViewPager) findViewById(R.id.vp_content);
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPager.setControlScroll(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Toast.makeText(this,"1111",Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.btn_2:
                Toast.makeText(this,"1111",Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(1, false);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:

                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 1:
                    return new TowFragment();
                default:
                    return new OneFragment();
            }
        }
    }
}
