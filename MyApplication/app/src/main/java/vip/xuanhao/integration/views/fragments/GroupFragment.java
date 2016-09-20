package vip.xuanhao.integration.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.views.BaseFragment;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class GroupFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View personalRootView = inflater.inflate(R.layout.fragment_group, container, false);
        return personalRootView;
    }
}
