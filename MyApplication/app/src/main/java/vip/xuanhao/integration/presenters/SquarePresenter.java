package vip.xuanhao.integration.presenters;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vip.xuanhao.integration.presenters.ipresenter.ISquarePresenter;
import vip.xuanhao.integration.views.adapters.SwipeDeckAdapter;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class SquarePresenter implements ISquarePresenter {

    private List<String> dataSource;
    private SwipeDeckAdapter swipeDeckAdapter;

    @Override
    public boolean checkUser() {
        return false;
    }

    @Override
    public void onResume(Context mContext, String pageName) {

    }

    @Override
    public void onPause(Context mContext, String pageName) {

    }

    @Override
    public void release() {
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
    public SwipeDeckAdapter getAdapter(Context mContext, List t) {
        if (null == swipeDeckAdapter)
            swipeDeckAdapter = new SwipeDeckAdapter(mContext, t);
        else
            swipeDeckAdapter.notifyDataSetChanged();
        return swipeDeckAdapter;
    }
}
