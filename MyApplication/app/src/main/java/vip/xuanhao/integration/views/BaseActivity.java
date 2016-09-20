package vip.xuanhao.integration.views;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import rx.subscriptions.CompositeSubscription;
import vip.xuanhao.integration.presenters.common.Events;
import vip.xuanhao.integration.utils.SystemUtils;

/**
 * Created by Xuanhao on 2016/7/6.
 */

public abstract class BaseActivity extends RxAppCompatActivity implements IBase{


    protected boolean isConnection = false;

    protected CompositeSubscription subscription;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Subscribe
    public void receiverConnectionState(Events.ConnectionEvent event) {
        isConnection = event.isConnection();
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
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
