package vip.xuanhao.integration.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import vip.xuanhao.integration.di.modules.BaseModule;
import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.utils.PreferenceHelper;

/**
 * Created by Xuanhao on 2016/11/3.
 */

@Singleton
@Component(modules = BaseModule.class)
public interface BaseApplicationComponent {

    Context context();

    NetManager netManager();

    PreferenceHelper preferenceHelper();
}
