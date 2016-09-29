package vip.xuanhao.integration.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.ui.ExpandFooterView;
import vip.xuanhao.integration.views.ui.ExpandHeaderView;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class VideoFragment extends BaseFragment implements BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener {


    @BindView(R.id.square_header)
    ExpandHeaderView squareHeader;
    @BindView(R.id.square_footer)
    ExpandFooterView squareFooter;
    @BindView(R.id.rec_video_content)
    RecyclerView recVideoContent;

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

        initView();
        initEvent();

    }

    @Override
    public void initView() {
    }


    @Override
    public void initData() {
        super.initData();
    }


    @Override
    public void initEvent() {
        squareHeader.setOnRefreshListener(this);
        squareFooter.setOnLoadListener(this);
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        baseFooterView.postDelayed(new Runnable() {
            @Override
            public void run() {
                squareFooter.stopLoad();
            }
        }, 2000);

    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
                squareHeader.stopRefresh();
            }
        }, 2000);
    }
}
