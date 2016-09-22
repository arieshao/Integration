package vip.xuanhao.integration.presenters;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.ipresenter.IHomePresenter;
import vip.xuanhao.integration.views.adapters.HorizontalPagerAdapter;

/**
 * Created by Xuanhao on 2016/9/20.
 */

public class HomePresenter implements IHomePresenter {


    private Context context;
    private List<Integer> integers;
    private HorizontalPagerAdapter mAdapter;
    final Integer img[] = {R.mipmap.bunner_01, R.mipmap.bunner_02, R.mipmap.bunner_03, R.mipmap.bunner_04, R.mipmap.bunner_05};


    public HomePresenter(Context context) {
        this.context = context;
    }

    @Override
    public boolean checkUser() {
        return false;
    }

    @Override
    public void onResume(Context mContext, String pageName) {

    }

    @Override
    public void onPause(Context mContext, String pageName) {

    }

    @Override
    public void release() {
        integers.clear();
        integers = null;
        context = null;
        mAdapter = null;
    }

    @Override
    public List<Integer> getDataSource() {
        integers = new ArrayList<>();
        Collections.addAll(integers, img);
        return integers;
    }

    @Override
    public HorizontalPagerAdapter getBannerAdapter() {
        mAdapter = new HorizontalPagerAdapter(context, integers);
        return mAdapter;
    }

    @Override
    public int getBannerDataSize() {
        return integers.size();
    }
}
