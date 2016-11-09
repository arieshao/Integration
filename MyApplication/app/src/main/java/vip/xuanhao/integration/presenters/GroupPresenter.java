package vip.xuanhao.integration.presenters;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import vip.xuanhao.integration.views.Iviews.IGroupView;

/**
 * Created by Xuanhao on 2016/11/9.
 */

public class GroupPresenter extends GodPresenter {


    private Context mContext;
    private IGroupView groupView;


    @Inject
    public GroupPresenter(@NotNull Fragment fragment) {

        if (fragment instanceof IGroupView)
            this.groupView = (IGroupView) fragment;
        this.mContext = fragment.getActivity();
    }

}
