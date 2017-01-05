package vip.xuanhao.integration.views.Iviews.zhihu;

import vip.xuanhao.integration.app.base.IBaseView;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public interface IZhiHuBaseViewView extends IBaseView {

    void hiddenLoading();

    void stopRefresh();

    void stopLoad();

    void update();
}
