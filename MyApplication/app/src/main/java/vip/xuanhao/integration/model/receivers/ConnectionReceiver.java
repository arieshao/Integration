package vip.xuanhao.integration.model.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import org.greenrobot.eventbus.EventBus;

import vip.xuanhao.integration.app.common.Events;
import vip.xuanhao.integration.utils.SystemUtils;

/**
 * Created by Xuanhao on 2016/8/18.
 * <p>
 * 网络连接广播接受
 */

public class ConnectionReceiver extends BroadcastReceiver {


    private static final String CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), CONNECTIVITY_CHANGE)) {
            boolean b = SystemUtils.checkNet(context);

            EventBus.getDefault().post(new Events.ConnectionEvent(b));
        }
    }
}
