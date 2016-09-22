package vip.xuanhao.integration.presenters.ipresenter;

import java.util.List;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public interface ISquarePresenter extends IGodPresenter {


    List<String> getDataSource();

    void getSquareDataFromNet();

    void getSquareMoreDataFromCache();

}
