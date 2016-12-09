package vip.xuanhao.integration.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vip.xuanhao.integration.app.BaseApplication;
import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.utils.PreferenceHelper;

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
    Context providesContext() {
        return baseApplication;
    }

    @Provides
    @Singleton
    PreferenceHelper providesPreference() {
        return new PreferenceHelper(baseApplication);
    }

    @Provides
    @Singleton
    NetManager providesNetManager() {
        return new NetManager(baseApplication);
    }

}
