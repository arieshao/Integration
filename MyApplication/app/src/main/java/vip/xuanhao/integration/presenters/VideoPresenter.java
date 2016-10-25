package vip.xuanhao.integration.presenters;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vip.xuanhao.integration.presenters.ipresenter.IVideoPresenter;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.adapters.VideoAdapter;

/**
 * Created by Xuanhao on 2016/9/28.
 */

public class VideoPresenter implements IVideoPresenter {


    private VideoAdapter mAdapter;

    @Override
    public List<String> getDataSource() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add(i + "");
        }
        return strings;
    }

    @Override
    public VideoAdapter getAdapter(Context mContext, List dataSource) {

        if (mAdapter == null) {
            mAdapter = new VideoAdapter(mContext, dataSource);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        return mAdapter;
    }

    @Override
    public void setListener(IOnRecycleViewItemClickListener listener) {
        mAdapter.setiOnRecycleViewItemClickListener(listener);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

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
}
