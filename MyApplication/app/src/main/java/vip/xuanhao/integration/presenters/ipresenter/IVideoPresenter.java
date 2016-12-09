package vip.xuanhao.integration.presenters.ipresenter;

import android.view.View;

import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.adapters.VideoAdapter;

/**
 * Created by Xuanhao on 2016/9/28.
 */

public interface IVideoPresenter{

    void getDataSource();

    VideoAdapter getAdapter();

    void setListener(IOnRecycleViewItemClickListener listener);

    void onItemClick(View view, int position);
}
