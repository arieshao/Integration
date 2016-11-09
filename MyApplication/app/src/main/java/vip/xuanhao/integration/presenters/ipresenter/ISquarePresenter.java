package vip.xuanhao.integration.presenters.ipresenter;

import java.util.List;

import vip.xuanhao.integration.views.adapters.SwipeDeckAdapter;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public interface ISquarePresenter<T>  {


    List<String> getDataSource();

    void getSquareDataFromNet();

    void getSquareMoreDataFromCache();

    SwipeDeckAdapter getAdapter(List<T> t);

}
