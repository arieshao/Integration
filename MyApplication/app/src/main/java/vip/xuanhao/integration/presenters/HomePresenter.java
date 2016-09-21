package vip.xuanhao.integration.presenters;

import android.content.Context;

/**
 * Created by Xuanhao on 2016/9/20.
 */

public class HomePresenter implements IHomePresenter {

    private Context context;

    public HomePresenter(Context context) {
        this.context = context;
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
