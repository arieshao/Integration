package vip.xuanhao.integration.presenters;

import android.content.Context;

import java.util.List;

import vip.xuanhao.integration.model.domain.PersonalViewModel;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public interface IPersonal extends IGodPresenter {


    /**
     * 清除缓存
     */
    boolean cleanCache();


    /**
     * 获取条目id
     *
     * @return 返回条目的数据集
     */
    List<PersonalViewModel> getModelData();


    /**
     * 跳转界面
     *
     * @param poisiton
     */
    void jumpPage(int poisiton);


    /**
     * 更换头像
     *
     * @return
     */
    String chooseIcon(Context mContext);
}
