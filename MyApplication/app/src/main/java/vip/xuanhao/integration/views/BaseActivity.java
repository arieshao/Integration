package vip.xuanhao.integration.views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import vip.xuanhao.integration.app.BaseApplication;
import vip.xuanhao.integration.di.components.DaggerMainActivityComponent;
import vip.xuanhao.integration.di.components.MainActivityComponent;
import vip.xuanhao.integration.di.modules.MainModule;
import vip.xuanhao.integration.presenters.BasePresenter;
import vip.xuanhao.integration.app.common.Events;
import vip.xuanhao.integration.utils.SystemUtils;

/**
 * Created by Xuanhao on 2016/7/6.
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements IBase {

    protected boolean isConnection = false;
    private Unbinder unbinder;
    @Inject
    public T presenter;

    private Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(getLayoutResId());
        unbinder = ButterKnife.bind(this);
        setStatusBar();
    }


    @TargetApi(19)
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        initInject();
        initialize();
        if (presenter != null)
            presenter.attach(this);
    }

    @Subscribe
    public void receiverConnectionState(Events.ConnectionEvent event) {
        isConnection = event.isConnection();
    }

    public MainActivityComponent getActivityComponent() {
        return DaggerMainActivityComponent
                .builder()
                .baseApplicationComponent(BaseApplication.getAppComponent())
                .mainModule(getMainModule())
                .build();
    }

    public MainModule getMainModule() {
        return new MainModule(this);
    }

    /**
     * 网络连接类型
     *
     * @return
     */
    public String getNetWroKType() {
        return isConnection ? SystemUtils.GetNetworkType(this) : "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        presenter.release();
        unbinder.unbind();
    }

    public abstract void initInject();

    public abstract int getLayoutResId();

    public void initialize() {
        getNetWroKType();
        initData();
        initView();
        initEvent();
    }
}
