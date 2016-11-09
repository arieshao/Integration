package vip.xuanhao.integration.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import vip.xuanhao.integration.di.ContextQualifier;
import vip.xuanhao.integration.di.modules.BaseModule;

/**
 * Created by Xuanhao on 2016/11/3.
 */

@Singleton
@Component(modules = BaseModule.class)
public interface BaseApplicationComponent {

    @ContextQualifier()
    Context context();
}
