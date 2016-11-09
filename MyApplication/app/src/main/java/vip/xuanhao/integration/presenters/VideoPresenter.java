package vip.xuanhao.integration.presenters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vip.xuanhao.integration.presenters.ipresenter.IVideoPresenter;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.IVideoView;
import vip.xuanhao.integration.views.adapters.VideoAdapter;

/**
 * Created by Xuanhao on 2016/9/28.
 */

public class VideoPresenter extends GodPresenter implements IVideoPresenter {


    private VideoAdapter mAdapter;
    private IVideoView iVideoView;
    private Context mContext;


    @Inject
    public VideoPresenter(Fragment fragment) {
        if (fragment instanceof IVideoView) {
            this.iVideoView = (IVideoView) fragment;
        }
        this.mContext = fragment.getActivity();
    }

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
}
