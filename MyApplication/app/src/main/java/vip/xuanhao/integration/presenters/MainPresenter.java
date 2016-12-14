package vip.xuanhao.integration.presenters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.flyco.tablayout.listener.CustomTabEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.domain.AppTab;
import vip.xuanhao.integration.presenters.ipresenter.IMainPresenter;
import vip.xuanhao.integration.utils.PreferenceHelper;
import vip.xuanhao.integration.views.Iviews.IMainView;
import vip.xuanhao.integration.views.adapters.MainPagerAdapter;
import vip.xuanhao.integration.views.fragments.CommunityFragment;
import vip.xuanhao.integration.views.fragments.PersonalFragment;
import vip.xuanhao.integration.views.fragments.VideoFragment;
import vip.xuanhao.integration.views.fragments.zhihu.ZhiHuFragment;

/**
 * Created by Xuanhao on 2016/9/13.
 */

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {

    private Context mContext;
    private MainPagerAdapter mAdapter;
    private PreferenceHelper preferenceHelper;


    private String tab_title[] = {"首页", "视频", "社区"}; //, "我"
    private int tab_icon_n[] = {R.mipmap.home_n, R.mipmap.video_n, R.mipmap.commonty_n}; //, R.mipmap.personal_n
    private int tab_icon_p[] = {R.mipmap.home_p, R.mipmap.video_p, R.mipmap.commonty_p}; //, R.mipmap.personal_p

    private List<Fragment> fragments;


    @Inject
    public MainPresenter(Activity activity, PreferenceHelper preferenceHelper) {
        this.mContext = activity;
        this.preferenceHelper = preferenceHelper;
        initPager();
    }


    private void initPager() {
        fragments = new ArrayList<>(4);
        fragments.add(new ZhiHuFragment());
        fragments.add(new VideoFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new PersonalFragment());
    }

    @Override
    public MainPagerAdapter getPagerAdapter(@NotNull FragmentManager fragmentManager) {
        if (mAdapter == null) {
            mAdapter = new MainPagerAdapter(fragmentManager ,fragments);
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
}
