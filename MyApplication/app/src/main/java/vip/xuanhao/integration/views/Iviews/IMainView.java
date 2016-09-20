package vip.xuanhao.integration.views.Iviews;

/**
 * Created by Xuanhao on 2016/9/13.
 */

public interface IMainView {


    /**
     * @param position    哪一个选项卡
     * @param hasNotify   是否有通知
     * @param notifyCount 几个通知
     */
    void showNotifyCount(int position, boolean hasNotify, int notifyCount);
}
