package vip.xuanhao.integration.presenters;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import vip.xuanhao.integration.presenters.ipresenter.IGodPresenter;

/**
 * Created by Xuanhao on 2016/11/4.
 */

public class GodPresenter implements IGodPresenter {
    @Override
    public boolean checkUser() {
        return false;
    }

    @Override
    public void onResume(Context mContext, String pageName) {
        MobclickAgent.onResume(mContext);
        MobclickAgent.onPageStart(pageName);
    }

    @Override
    public void onPause(Context mContext, String pageName) {
        MobclickAgent.onPageEnd(pageName);
        MobclickAgent.onPause(mContext);
    }

    @Override
    public void release() {

    }
}
