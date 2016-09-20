package vip.xuanhao.integration.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * Created by Xuanhao on 2016/6/30.
 */

public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getSimpleName();


    // user your appid the key.
    private static final String APP_ID = "2882303761517488535";
    // user your appid the key.
    private static final String APP_KEY = "5121748823535";


    private RefWatcher refWatcher;

    public static RefWatcher refWatcher(Context mContext) {
        BaseApplication application = (BaseApplication) mContext.getApplicationContext();
        return application.refWatcher;
    }

    public BaseApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
//        MultiDex.install(this);  //方法超出限值
        Logger.w("BaseApplication is onCreate");

        MobclickAgent.openActivityDurationTrack(false);//更改友盟默认统计方式.

        MobclickAgent.enableEncrypt(true);//6.0.0版本及以后

        Stetho.initializeWithDefaults(this);

        if (shouldInit()) {
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }


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

}
