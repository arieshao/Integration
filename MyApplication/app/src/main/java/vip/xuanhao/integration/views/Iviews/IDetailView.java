package vip.xuanhao.integration.views.Iviews;

import vip.xuanhao.integration.model.domain.ZhihuDetailBean;
import vip.xuanhao.integration.views.IBase;

/**
 * Created by Xuanhao on 2016/12/5.
 */

public interface IDetailView  extends IBase{

    void update(ZhihuDetailBean zhihuDetailBean);


    void hiddenLoading();
}
