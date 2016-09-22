package vip.xuanhao.integration.presenters.ipresenter;

import android.content.Context;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public interface IGodPresenter {

    /**
     * 检查用户是否登录等
     *
     * @return
     */
    boolean checkUser();


    /**
     * 友盟统计
     *
     * @param mContext
     * @param pageName
     */
    void onResume(Context mContext, String pageName);

    void onPause(Context mContext, String pageName);

    /**
     * 回收
     */
    void release();
}
