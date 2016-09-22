package vip.xuanhao.integration.presenters;

import java.util.List;

import vip.xuanhao.integration.views.adapters.HorizontalPagerAdapter;

/**
 * Created by Xuanhao on 2016/9/20.
 */

public interface IHomePresenter extends IGodPresenter {

    List<Integer> getDataSource();

    HorizontalPagerAdapter getBannerAdapter();

    int getBannerDataSize();
}
