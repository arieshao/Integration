package vip.xuanhao.integration.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vip.xuanhao.integration.app.BaseApplication;
import vip.xuanhao.integration.di.components.DaggerFragmentComponent;
import vip.xuanhao.integration.di.components.FragmentComponent;
import vip.xuanhao.integration.di.modules.FragmentModule;

/**
 * Created by Xuanhao on 2016/9/14.
 */

public class BaseFragment extends Fragment implements IBase {

    protected Context mContext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent
                .builder()
                .baseApplicationComponent(BaseApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    public FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

}
