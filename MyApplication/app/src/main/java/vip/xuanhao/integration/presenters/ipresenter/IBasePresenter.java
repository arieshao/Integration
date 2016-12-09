package vip.xuanhao.integration.presenters.ipresenter;

import vip.xuanhao.integration.views.IBase;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public interface IBasePresenter<T extends IBase> {

    void attach(T t);

    void release();
}
