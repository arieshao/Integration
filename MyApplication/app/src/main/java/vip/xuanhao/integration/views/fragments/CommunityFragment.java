package vip.xuanhao.integration.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.adapters.CommunityContentAdapter;
import vip.xuanhao.integration.views.ui.UnScrollViewPager;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class CommunityFragment extends BaseFragment {


    @BindView(R.id.tl_title)
    TabLayout tlCommunityChange;
    @BindView(R.id.vp_community_content)
    UnScrollViewPager vpCommunityContent;

    CommunityContentAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View personalRootView = inflater.inflate(R.layout.fragment_community, container, false);
        ButterKnife.bind(this, personalRootView);
        return personalRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void initData() {
        mAdapter = new CommunityContentAdapter(getChildFragmentManager());
        vpCommunityContent.setScrollable(false);
        vpCommunityContent.setAdapter(mAdapter);
        tlCommunityChange.setupWithViewPager(vpCommunityContent);
    }
}
