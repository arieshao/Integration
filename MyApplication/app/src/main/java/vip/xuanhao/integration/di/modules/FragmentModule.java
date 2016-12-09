package vip.xuanhao.integration.di.modules;

import android.app.Activity;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import vip.xuanhao.integration.di.scopes.FragmentScope;

/**
 * Created by Xuanhao on 2016/11/4.
 */


@Module
public class FragmentModule {

    private final Fragment mFragment;

    public FragmentModule(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    @Provides
    @FragmentScope
    public Fragment provideFragment() {
        return mFragment;
    }


    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return mFragment.getActivity();
    }


}
