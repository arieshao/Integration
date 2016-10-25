package vip.xuanhao.integration.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.VideoPresenter;
import vip.xuanhao.integration.presenters.ipresenter.IVideoPresenter;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.ui.GridItemDecoration;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class VideoFragment extends BaseFragment implements BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener, IOnRecycleViewItemClickListener {


    @BindView(R.id.rec_video_content)
    RecyclerView recVideoContent;

    private IVideoPresenter iVideoPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View videoRootView = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, videoRootView);
        return videoRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iVideoPresenter = new VideoPresenter();
        initView();
        initEvent();

    }

    @Override
    public void initView() {

        GridItemDecoration gridItemDecoration = new GridItemDecoration(16);
        recVideoContent.addItemDecoration(gridItemDecoration);
        recVideoContent.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recVideoContent.setAdapter(iVideoPresenter.getAdapter(mContext, iVideoPresenter.getDataSource()));
    }


    @Override
    public void initData() {
    }


    @Override
    public void initEvent() {
        iVideoPresenter.setListener(this);
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        baseFooterView.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 2000);

    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 2000);
    }

    @Override
    public void onItemClick(View view, int position) {
        iVideoPresenter.onItemClick(view, position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iVideoPresenter.release();
    }
}
