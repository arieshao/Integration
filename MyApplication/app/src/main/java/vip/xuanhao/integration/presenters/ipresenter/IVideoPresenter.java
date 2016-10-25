package vip.xuanhao.integration.presenters.ipresenter;

import android.content.Context;
import android.view.View;

import java.util.List;

import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.adapters.VideoAdapter;

/**
 * Created by Xuanhao on 2016/9/28.
 */

public interface IVideoPresenter<T> extends IGodPresenter {

    List<String> getDataSource();

    VideoAdapter getAdapter(Context mContext, List<T> dataSource);

    void setListener(IOnRecycleViewItemClickListener listener);

    void onItemClick(View view, int position);
}
