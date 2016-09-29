package vip.xuanhao.integration.presenters;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.ipresenter.IHomePresenter;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.adapters.HomeAdapter;
import vip.xuanhao.integration.views.adapters.HomeContentListAdapter;
import vip.xuanhao.integration.views.adapters.HorizontalPagerAdapter;

/**
 * Created by Xuanhao on 2016/9/20.
 */

public class HomePresenter implements IHomePresenter {


    private Context context;
    private List<Integer> integers;
    private HorizontalPagerAdapter mAdapter; //引导页adapter;
    private HomeContentListAdapter homeContentListAdapter; //ListView adapter

    private HomeAdapter homeAdapter;

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
        homeContentListAdapter = null;
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

    @Override
    public void onItemPageClick(int pos) {
    }

    @Override
    public HomeContentListAdapter getContentListAdapter() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add(" " + i);
        }

//        HomeData homeData = new HomeData();
//        homeData.setContexts(strings);

        homeContentListAdapter = new HomeContentListAdapter(context, strings);
        return homeContentListAdapter;
    }

    @Override
    public HomeAdapter getHomeAdapter(IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add(" " + i);
        }
        homeAdapter = new HomeAdapter(context, strings);
//        homeAdapter.setiOnRecycleViewItemClickListener(iOnRecycleViewItemClickListener);
        return homeAdapter;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(context, "position    " + position, Toast.LENGTH_SHORT).show();
    }
}
