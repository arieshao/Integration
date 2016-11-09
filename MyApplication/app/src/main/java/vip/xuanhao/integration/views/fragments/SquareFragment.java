package vip.xuanhao.integration.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.daprlabs.aaron.swipedeck.layouts.SwipeFrameLayout;
import com.github.ybq.android.spinkit.SpinKitView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.SquarePresenter;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.Iviews.ISquareView;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class SquareFragment extends BaseFragment implements ISquareView {


    @BindView(R.id.swipe_deck)
    SwipeDeck swipeDeck;
    @BindView(R.id.spin_kit)
    SpinKitView spinKit;
    @BindView(R.id.swipeLayout)
    SwipeFrameLayout swipeLayout;
    @Inject
    SquarePresenter iSquarePresenter;


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
        getFragmentComponent().inject(this);
        initView();
        initEvent();
    }


    @Override
    public void initView() {
        swipeDeck.setAdapter(iSquarePresenter.getAdapter(iSquarePresenter.getDataSource()));
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        iSquarePresenter.release();
    }
}
