package vip.xuanhao.integration.presenters;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.views.Iviews.IGroupView;

/**
 * Created by Xuanhao on 2016/11/9.
 */

public class GroupPresenter extends BasePresenter<IGroupView> {


    private Context mContext;
    private NetManager netManager;


    @Inject
    public GroupPresenter(@NotNull Fragment fragment, NetManager netManager) {
        this.mContext = fragment.getActivity();
        this.netManager = netManager;
    }

}
