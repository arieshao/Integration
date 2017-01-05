package vip.xuanhao.integration.views.Iviews;

import vip.xuanhao.integration.app.base.IBaseView;
import vip.xuanhao.integration.model.domain.ZhihuDetailBean;

/**
 * Created by Xuanhao on 2016/12/5.
 */

public interface IDetailViewView extends IBaseView {

    void update(ZhihuDetailBean zhihuDetailBean);


    void hiddenLoading();
}
