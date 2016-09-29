package vip.xuanhao.integration.presenters.ipresenter;

import java.util.List;

import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.adapters.HomeAdapter;
import vip.xuanhao.integration.views.adapters.HomeContentListAdapter;
import vip.xuanhao.integration.views.adapters.HorizontalPagerAdapter;

/**
 * Created by Xuanhao on 2016/9/20.
 */

public interface IHomePresenter extends IGodPresenter {

    List<Integer> getDataSource();

    HorizontalPagerAdapter getBannerAdapter();

    int getBannerDataSize();

    void onItemPageClick(int pos);

    HomeContentListAdapter getContentListAdapter();

    HomeAdapter getHomeAdapter(IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener);

    void onItemClick(int position);
}
