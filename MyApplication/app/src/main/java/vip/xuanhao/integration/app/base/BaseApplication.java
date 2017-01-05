package vip.xuanhao.integration.app.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

import vip.xuanhao.integration.di.components.BaseApplicationComponent;
import vip.xuanhao.integration.di.components.DaggerBaseApplicationComponent;
import vip.xuanhao.integration.di.modules.BaseModule;

/**
 * Created by Xuanhao on 2016/6/30.
 */

public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getSimpleName();
    private static final String APP_ID = "2882303761517488535";
    private static final String APP_KEY = "5121748823535";

    private static BaseApplication INSTANCE;

    private RefWatcher refWatcher;


    public BaseApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);  //方法超出限值
        initLogger();
        initSelf();
        initUmeng();
        initLeakCanary();
        initMiPush();
    }

    private void initLogger() {
        Logger.init().hideThreadInfo();//初始化logger隐藏线程信息
        Logger.v("initLogger is called");
    }

    private void initSelf() {
        Logger.v("initSelf is called");
        INSTANCE = this;
    }


    /**
     * 初始化友盟统计
     */
    private void initUmeng() {
        Logger.v("initUmeng is called");
    }

    private void initLeakCanary() {
        Logger.v("initLeakCanary is called");
        refWatcher = LeakCanary.install(this);
    }


    /**
     * 初始化小米推送
     */
    private void initMiPush() {
        Logger.v("initMiPush is called");
        if (shouldInit())
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        com.xiaomi.mipush.sdk.Logger.setLogger(this, newLogger);
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * dagger2 di
     *
     * @return
     */

    private static BaseApplicationComponent applicationComponent;

    public static BaseApplicationComponent getAppComponent() {
        Logger.v("getAppComponent is called");
        applicationComponent = DaggerBaseApplicationComponent
                .builder()
                .baseModule(new BaseModule(INSTANCE))
                .build();
        return applicationComponent;
    }


    public static RefWatcher refWatcher() {
        return INSTANCE.refWatcher;
    }
}
