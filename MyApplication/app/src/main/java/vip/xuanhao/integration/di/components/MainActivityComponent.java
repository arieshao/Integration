package vip.xuanhao.integration.di.components;

import android.app.Activity;

import dagger.Component;
import vip.xuanhao.integration.di.scopes.ActivityScope;
import vip.xuanhao.integration.di.modules.MainModule;
import vip.xuanhao.integration.views.activitys.GuideActivity;
import vip.xuanhao.integration.views.activitys.MainActivity;
import vip.xuanhao.integration.views.activitys.WelcomeActivity;

/**
 * Created by Xuanhao on 2016/11/3.
 */

@ActivityScope
@Component(dependencies = BaseApplicationComponent.class, modules = MainModule.class)
public interface MainActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(GuideActivity guideActivity);

    void inject(WelcomeActivity welcomeActivity);
}