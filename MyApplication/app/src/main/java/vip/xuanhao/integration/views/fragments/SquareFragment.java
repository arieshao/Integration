package vip.xuanhao.integration.views.fragments;

import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.daprlabs.aaron.swipedeck.layouts.SwipeFrameLayout;
import com.github.ybq.android.spinkit.SpinKitView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.SquarePresenter;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.Iviews.ISquareView;
import vip.xuanhao.integration.views.adapters.SwipeDeckAdapter;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class SquareFragment extends BaseFragment<SquarePresenter> implements ISquareView {


    @BindView(R.id.swipe_deck)
    SwipeDeck swipeDeck;
    @BindView(R.id.spin_kit)
    SpinKitView spinKit;
    @BindView(R.id.swipeLayout)
    SwipeFrameLayout swipeLayout;


    @Override
    public void initData() {
//        presenter.getDataSource();

    }

    @Override
    public void initView() {

        Logger.w("SquareFragment 's  initView method is running");

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i + "");
        }

        SwipeDeckAdapter swipeDeckAdapter = new SwipeDeckAdapter(getActivity(), strings);
        swipeDeck.setAdapter(swipeDeckAdapter);
//        swipeDeck.setAdapter(presenter.getAdapter());
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
    public int getLayoutResId() {
        return R.layout.fragment_square;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.release();
    }
}
