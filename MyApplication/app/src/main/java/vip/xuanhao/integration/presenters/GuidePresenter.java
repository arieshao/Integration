package vip.xuanhao.integration.presenters;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.views.adapters.GuideAdapter;

/**
 * Created by Xuanhao on 2016/9/22.
 */

public class GuidePresenter implements IGuidePresenter {


    private Context mContext;
    private List<Integer> integers;
    private GuideAdapter mAdapter;
    private Integer imageResources[] = {R.mipmap.guide_bg, R.mipmap.guide_bg_01, R.mipmap.guide_bg_02, R.mipmap.guide_bg_03, R.mipmap.guide_bg_04};


    public GuidePresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public List<Integer> getGuideData() {
        integers = new ArrayList<>();
        Collections.addAll(integers, imageResources);
        return integers;
    }

    @Override
    public int getDataSize() {
        return imageResources.length;
    }

    @Override
    public GuideAdapter getAdapter() {
        mAdapter = new GuideAdapter(mContext, integers);
        return mAdapter;
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
        mContext = null;
        integers.clear();
        integers = null;
        mAdapter = null;
        imageResources = null;
    }
}
