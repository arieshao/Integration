package vip.xuanhao.integration.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.daprlabs.aaron.swipedeck.layouts.SwipeFrameLayout;
import com.github.ybq.android.spinkit.SpinKitView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.SquarePresenter;
import vip.xuanhao.integration.presenters.ipresenter.ISquarePresenter;
import vip.xuanhao.integration.views.BaseFragment;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class SquareFragment extends BaseFragment {


    @BindView(R.id.swipe_deck)
    SwipeDeck swipeDeck;
    @BindView(R.id.spin_kit)
    SpinKitView spinKit;
    @BindView(R.id.swipeLayout)
    SwipeFrameLayout swipeLayout;
    private ISquarePresenter iSquarePresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View personalRootView = inflater.inflate(R.layout.fragment_square, container, false);
        ButterKnife.bind(this, personalRootView);
        return personalRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iSquarePresenter = new SquarePresenter();
        initView();
        initEvent();
    }


    @Override
    public void initView() {
        swipeDeck.setAdapter(iSquarePresenter.getAdapter(mContext, iSquarePresenter.getDataSource()));
    }


    @Override
    public void initEvent() {
        swipeDeck.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long stableId) {

            }

            @Override
            public void cardSwipedRight(long stableId) {

            }
        });

    }
}
