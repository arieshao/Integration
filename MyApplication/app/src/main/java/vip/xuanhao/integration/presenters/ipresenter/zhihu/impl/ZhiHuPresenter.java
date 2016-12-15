package vip.xuanhao.integration.presenters.ipresenter.zhihu.impl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.presenters.BasePresenter;
import vip.xuanhao.integration.presenters.ipresenter.zhihu.IZhiHuPresenter;
import vip.xuanhao.integration.views.Iviews.zhihu.IZhiHuView;
import vip.xuanhao.integration.views.adapters.zhihu.ZhiHuPagerAdapter;
import vip.xuanhao.integration.views.fragments.zhihu.DailyNewFragment;
import vip.xuanhao.integration.views.fragments.zhihu.HotFragment2;
import vip.xuanhao.integration.views.fragments.zhihu.SpecialFragment;
import vip.xuanhao.integration.views.fragments.zhihu.ThemeFragment;

/**
 * Created by Xuanhao on 2016/12/13.
 */

public class ZhiHuPresenter extends BasePresenter<IZhiHuView> implements IZhiHuPresenter {


    private NetManager netManager;

    private ZhiHuPagerAdapter zhiHuPagerAdapter;

    private List<Fragment> fragments;


    @Inject
    public ZhiHuPresenter(NetManager netManager) {
        this.netManager = netManager;
    }

    public void getFragments() {
        fragments = new ArrayList<>();
        fragments.add(new DailyNewFragment());
        fragments.add(new ThemeFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new HotFragment2());
    }

    public int getPageSize() {
        return fragments.size();
    }

    public ZhiHuPagerAdapter getAdapter(FragmentManager fragmentManager) {
        if (zhiHuPagerAdapter == null) {
            zhiHuPagerAdapter = new ZhiHuPagerAdapter(fragmentManager, fragments);
        }
        return zhiHuPagerAdapter;
    }


}
