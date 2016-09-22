package vip.xuanhao.integration.presenters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.flyco.tablayout.listener.CustomTabEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.domain.AppTab;
import vip.xuanhao.integration.presenters.exception.IMainPresenter;
import vip.xuanhao.integration.views.Iviews.IMainView;
import vip.xuanhao.integration.views.adapters.MainPagerAdapter;

/**
 * Created by Xuanhao on 2016/9/13.
 */

public class MainPresenter implements IMainPresenter {

    private IMainView iMainView;
    private Context mContext;
    private MainPagerAdapter mAdapter;


    private String tab_title[] = {"首页", "视频", "社区", "我"};
    private int tab_icon_n[] = {R.mipmap.home_n, R.mipmap.video_n, R.mipmap.commonty_n, R.mipmap.personal_n};
    private int tab_icon_p[] = {R.mipmap.home_p, R.mipmap.video_p, R.mipmap.commonty_p, R.mipmap.personal_p};


    public MainPresenter(Activity activity) {
        if (activity instanceof Context)
            this.mContext = activity;
        if (activity instanceof IMainView)
            this.iMainView = (IMainView) activity;
    }

    @Override
    public MainPagerAdapter getPagerAdapter(@NotNull FragmentManager fragmentManager) {
        if (mAdapter == null) {
            mAdapter = new MainPagerAdapter(fragmentManager);
        }
        return mAdapter;
    }

    @Override
    public ArrayList<CustomTabEntity> getTabData() {
        ArrayList<CustomTabEntity> tabs = new ArrayList<>();
        for (int i = 0; i < tab_title.length; i++) {
            tabs.add(new AppTab(tab_title[i], tab_icon_p[i], tab_icon_n[i]));
        }
        return tabs;
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

    }
}
