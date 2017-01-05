package vip.xuanhao.integration.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import vip.xuanhao.integration.di.components.DaggerFragmentComponent;
import vip.xuanhao.integration.di.components.FragmentComponent;
import vip.xuanhao.integration.di.modules.FragmentModule;
import vip.xuanhao.integration.presenters.BasePresenter;

/**
 * Created by Xuanhao on 2016/9/14.
 * MVP Fragment 基类
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView {


    @Inject
    public T presenter;
    protected Context mContext;
    private Unbinder unbinder;
    protected boolean isVisible = false;
    protected boolean isPrepared = false;   //onCreateView方法是否执行了


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initInject();
        isPrepared = true;
        LazyLoad();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attach(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
            LazyLoad();
    }


    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
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


    public void initialize() {
        initView();
        initEvent();
    }

    private void LazyLoad() {
        if (isPrepared && !isVisible && getUserVisibleHint()) {
            isVisible = true;
            initData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isPrepared = false;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.release();
    }

    public abstract int getLayoutResId();

    /**
     * 初始化dagger2 注入
     */
    protected abstract void initInject();

}
