package vip.xuanhao.integration.views.Iviews.zhihu;

import vip.xuanhao.integration.views.IBase;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public interface IZhiHuBaseView extends IBase{

    void hiddenLoading();

    void stopRefresh();

    void stopLoad();

    void update();
}
