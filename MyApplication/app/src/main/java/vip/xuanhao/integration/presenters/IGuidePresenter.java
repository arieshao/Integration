package vip.xuanhao.integration.presenters;

import java.util.List;

import vip.xuanhao.integration.views.adapters.GuideAdapter;

/**
 * Created by Xuanhao on 2016/9/22.
 */

public interface IGuidePresenter extends IGodPresenter {

    /**
     * 引导图片资源
     *
     * @return
     */
    List<Integer> getGuideData();

    int getDataSize();

    GuideAdapter getAdapter();
}
