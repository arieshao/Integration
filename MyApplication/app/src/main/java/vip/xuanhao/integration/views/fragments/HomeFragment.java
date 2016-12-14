package vip.xuanhao.integration.views.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.orhanobut.logger.Logger;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.HomePresenter;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.IHomeView;
import vip.xuanhao.integration.views.ui.NormalHeaderView;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView, OnItemClickListener, BaseHeaderView.OnRefreshListener, IOnRecycleViewItemClickListener {

//    @BindView(R.id.convenientBanner)
//    ConvenientBanner convenientBanner;
//    @BindView(R.id.appbarlayout_home)
//    AppBarLayout appbarlayoutHome;
    @BindView(R.id.rec_home_content)
    RecyclerView recHomeContent;
    @BindView(R.id.home_refresh_header)
    NormalHeaderView homeRefreshHeader;
//    @BindView(R.id.home_root)
//    View homeroot;
    private boolean isOpen = true;
    private boolean isVisable = false;

    private boolean isPref = false;

    @Override
    public void initData() {
        Logger.w("initData method is Running");
        presenter.getDataSource();
    }

    @Override
    public void initView() {
        initContent();
        isPref = true;
    }


    /**
     * 初始化banner
     */
    private void initBanner() {
//        convenientBanner.setPages(new BannerHelper(), presenter.getBannerDatas())
//                .setPageIndicator(new int[]{R.drawable.dot_n, R.drawable.dot_p})
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnItemClickListener(this);
    }

    private void initContent() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recHomeContent.setLayoutManager(linearLayoutManager);
        recHomeContent.setAdapter(presenter.getHomeAdapter(this));
    }


    @Override
    public void initEvent() {
        homeRefreshHeader.setOnRefreshListener(this);
//        appbarlayoutHome.addOnOffsetChangedListener(new AppBarLayoutHelper.AppBarStateChangeListener() {
//            @Override
//            public void onOffsetChanged(int verticalOffset) {
//                switch (verticalOffset) {
//                    case AppBarLayoutHelper.AppBarStateChangeListener.STATE_EXPANDED://open
//                        isOpen = true;
//                        convenientBanner.startTurning(3000);
//                        break;
//                    case AppBarLayoutHelper.AppBarStateChangeListener.STATE_COLLAPSED:
//                    case AppBarLayoutHelper.AppBarStateChangeListener.STATE_IDLE:
//                        isOpen = false;
//                        convenientBanner.stopTurning();
//                        break;
//                }
//            }
//        });
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        if (isPref)
//            if (isVisibleToUser)
//                convenientBanner.startTurning(3000);
//            else
//                convenientBanner.stopTurning();

    }


    @Override
    public int getLayoutResId() {
        isPref = true;
        return R.layout.fragment_home;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        presenter.onItemClick(position);
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
                homeRefreshHeader.stopRefresh();
            }
        }, 2000);

        presenter.getDataSource();
    }


    @Override
    public void upDataUI() {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        presenter.onItemClick(position);
    }
}
