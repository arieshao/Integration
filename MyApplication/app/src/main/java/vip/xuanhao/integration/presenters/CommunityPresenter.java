package vip.xuanhao.integration.presenters;

import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.views.adapters.CommunityContentAdapter;

/**
 * Created by Xuanhao on 2016/11/28.
 */

public class CommunityPresenter extends BasePresenter {

    private CommunityContentAdapter communityContentAdapter;

    private NetManager netManager;


    @Inject
    public CommunityPresenter(NetManager netManager) {
        this.netManager = netManager;
    }

    public CommunityContentAdapter getAdapter(FragmentManager fragmentManager) {

        if (communityContentAdapter == null)
            communityContentAdapter = new CommunityContentAdapter(fragmentManager);
        return communityContentAdapter;
    }


}
