package vip.xuanhao.integration.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vip.xuanhao.integration.app.BaseApplication;
import vip.xuanhao.integration.di.ContextQualifier;

/**
 * Created by Xuanhao on 2016/11/3.
 */

@Module
public class BaseModule {

    private final BaseApplication baseApplication;


    public BaseModule(BaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @Singleton
    @ContextQualifier(value = "Application")
    Context providesContext() {
        return baseApplication;
    }
}
