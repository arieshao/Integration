package vip.xuanhao.integration.di.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import vip.xuanhao.integration.di.scopes.ActivityScope;

/**
 * Created by Xuanhao on 2016/11/3.
 */

@Module
public class MainModule {

    private Activity mActivity;

    public MainModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
