package vip.xuanhao.integration;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by Xuanhao on 2016/6/30.
 */

public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getSimpleName();

    public BaseApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d(TAG, "BaseApplication is onCreate");
    }
}
