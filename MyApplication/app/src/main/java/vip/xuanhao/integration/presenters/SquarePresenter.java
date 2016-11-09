package vip.xuanhao.integration.presenters;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vip.xuanhao.integration.presenters.ipresenter.ISquarePresenter;
import vip.xuanhao.integration.views.Iviews.ISquareView;
import vip.xuanhao.integration.views.adapters.SwipeDeckAdapter;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class SquarePresenter extends GodPresenter implements ISquarePresenter {

    private List<String> dataSource;
    private SwipeDeckAdapter swipeDeckAdapter;

    private Context mContext;
    private ISquareView mISquareView;


    @Inject
    public SquarePresenter(@NotNull Fragment fragment) {
        if (fragment instanceof ISquareView)
            this.mISquareView = (ISquareView) fragment;
        this.mContext = fragment.getActivity();
    }

    @Override
    public List<String> getDataSource() {
        if (dataSource == null)
            dataSource = new ArrayList<>();
        else
            dataSource.clear();
        getSquareMoreDataFromCache();
        return dataSource;
    }


    @Override
    public void getSquareDataFromNet() {
    }

    @Override
    public void getSquareMoreDataFromCache() {
        for (int i = 0; i < 10; i++) {
            dataSource.add(i + "");
        }
    }

    @Override
    public SwipeDeckAdapter getAdapter(List t) {
        if (null == swipeDeckAdapter)
            swipeDeckAdapter = new SwipeDeckAdapter(mContext, t);
        else
            swipeDeckAdapter.notifyDataSetChanged();
        return swipeDeckAdapter;
    }
}
