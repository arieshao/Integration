package vip.xuanhao.integration.presenters;

import android.content.Context;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public interface IGodPresenter {

    boolean checkUser();

    void onResume(Context mContext, String pageName);

    void onPause(Context mContext, String pageName);

    void release();
}
