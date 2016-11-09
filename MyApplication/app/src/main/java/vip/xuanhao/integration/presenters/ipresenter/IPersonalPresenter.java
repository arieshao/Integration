package vip.xuanhao.integration.presenters.ipresenter;

import android.content.Context;
import android.view.View;

import java.util.List;

import vip.xuanhao.integration.model.domain.PersonalViewModel;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.adapters.PersonalAdapter;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public interface IPersonalPresenter {


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

    /**
     * 获取适配器
     *
     * @param mContext
     * @param iOnRecycleViewItemClickListener
     * @return
     */

    PersonalAdapter getAdapter(Context mContext, IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener);

    void onItemClick(Context mContext, View view, int position);
}
