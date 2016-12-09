package vip.xuanhao.integration.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.ipresenter.IGuidePresenter;
import vip.xuanhao.integration.utils.PreferenceHelper;
import vip.xuanhao.integration.views.activitys.MainActivity;
import vip.xuanhao.integration.views.adapters.GuideAdapter;

/**
 * Created by Xuanhao on 2016/9/22.
 */

public class GuidePresenter extends BasePresenter implements IGuidePresenter {

    private Context mContext;
    private List<Integer> integers;
    private GuideAdapter mAdapter;
    private Integer imageResources[] = {R.mipmap.guide_bg, R.mipmap.guide_bg_01, R.mipmap.guide_bg_02, R.mipmap.guide_bg_03, R.mipmap.guide_bg_04};


    private PreferenceHelper preferenceHelper;


    @Inject
    public GuidePresenter(Activity activity, PreferenceHelper preferenceHelper) {
        this.preferenceHelper = preferenceHelper;
        this.mContext = activity;
    }

    @Override
    public List<Integer> getGuideData() {
        if (integers == null) {
            integers = new ArrayList<>();
            Collections.addAll(integers, imageResources);
        }
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
    public void jump() {
        preferenceHelper.saveAppPre(false);
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }


    @Override
    public void release() {
        mContext = null;
        if (integers == null) {
            integers.clear();
            integers = null;
        }
        mAdapter = null;
        imageResources = null;
    }
}
