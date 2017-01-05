package vip.xuanhao.integration.presenters;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.presenters.ipresenter.ISquarePresenter;
import vip.xuanhao.integration.views.Iviews.ISquareViewView;
import vip.xuanhao.integration.views.adapters.SwipeDeckAdapter;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class SquarePresenter extends BasePresenter<ISquareViewView> implements ISquarePresenter {

    private List<String> dataSource;
    private SwipeDeckAdapter swipeDeckAdapter;

    private Context mContext;

    private NetManager netManager;

    @Inject
    public SquarePresenter(@NotNull Fragment fragment, NetManager netManager) {
        this.mContext = fragment.getActivity();
        this.netManager = netManager;
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
    public void getSquareMoreDataFromCache() {
        for (int i = 0; i < 10; i++) {
            dataSource.add(i + "");
        }
    }

    @Override
    public SwipeDeckAdapter getAdapter() {
        if (null == swipeDeckAdapter)
            swipeDeckAdapter = new SwipeDeckAdapter(mContext, getDataSource());
        else
            swipeDeckAdapter.notifyDataSetChanged();
        return swipeDeckAdapter;
    }
}
