package vip.xuanhao.integration.views.fragments;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.app.base.BaseFragment;
import vip.xuanhao.integration.presenters.VideoPresenter;
import vip.xuanhao.integration.app.widget.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.IVideoViewView;
import vip.xuanhao.integration.app.widget.ui.ExpandFooterView;
import vip.xuanhao.integration.app.widget.ui.ExpandHeaderView;
import vip.xuanhao.integration.app.widget.ui.GridItemDecoration;


/**
 * Created by Xuanhao on 2016/9/14.
 */

public class VideoFragment extends BaseFragment<VideoPresenter> implements IVideoViewView, BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener, IOnRecycleViewItemClickListener {


    @BindView(R.id.rec_video_content)
    RecyclerView recVideoContent;
    @BindView(R.id.square_header)
    ExpandHeaderView headerView;
    @BindView(R.id.square_footer)
    ExpandFooterView footerView;

    @Override
    public void initView() {
        GridItemDecoration gridItemDecoration = new GridItemDecoration(26);
        recVideoContent.addItemDecoration(gridItemDecoration);
        recVideoContent.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recVideoContent.setAdapter(presenter.getAdapter());
    }


    @Override
    public void initData() {
        Logger.w("initData method is Running");
        presenter.getDataSource();
    }


    @Override
    public void initEvent() {
        headerView.setOnRefreshListener(this);
        footerView.setOnLoadListener(this);
        presenter.setListener(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        baseFooterView.postDelayed(new Runnable() {
            @Override
            public void run() {
                footerView.stopLoad();
            }
        }, 2000);
        presenter.getDataSource();
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
                headerView.stopRefresh();
            }
        }, 2000);
        presenter.getDataSource();
    }

    @Override
    public void onItemClick(View view, int position) {
        presenter.onItemClick(view, position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void updateUI() {
        presenter.UpdateUI();
    }
}
