package vip.xuanhao.integration.views.fragments;

import android.support.design.widget.TabLayout;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.app.base.BaseFragment;
import vip.xuanhao.integration.presenters.CommunityPresenter;
import vip.xuanhao.integration.app.widget.ui.UnScrollViewPager;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class CommunityFragment extends BaseFragment<CommunityPresenter> {


    @BindView(R.id.tl_title)
    TabLayout tlCommunityChange;
    @BindView(R.id.vp_community_content)
    UnScrollViewPager vpCommunityContent;



    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        vpCommunityContent.setScrollable(false);
        vpCommunityContent.setAdapter(presenter.getAdapter(getChildFragmentManager()));
        tlCommunityChange.setupWithViewPager(vpCommunityContent);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_community;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }
}
