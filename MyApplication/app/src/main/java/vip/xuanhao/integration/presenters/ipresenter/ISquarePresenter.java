package vip.xuanhao.integration.presenters.ipresenter;

import android.content.Context;

import java.util.List;

import vip.xuanhao.integration.views.adapters.SwipeDeckAdapter;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public interface ISquarePresenter<T> extends IGodPresenter {


    List<String> getDataSource();

    void getSquareDataFromNet();

    void getSquareMoreDataFromCache();

    SwipeDeckAdapter getAdapter(Context mContext, List<T> t);

}
