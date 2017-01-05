package vip.xuanhao.integration.presenters.ipresenter;

import vip.xuanhao.integration.app.base.IBaseView;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public interface IBasePresenter<T extends IBaseView> {

    void attach(T t);

    void release();
}
