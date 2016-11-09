package vip.xuanhao.integration.di.components;

import dagger.Component;
import vip.xuanhao.integration.di.scopes.FragmentScope;
import vip.xuanhao.integration.di.modules.FragmentModule;
import vip.xuanhao.integration.views.fragments.CommunityFragment;
import vip.xuanhao.integration.views.fragments.GroupFragment;
import vip.xuanhao.integration.views.fragments.HomeFragment;
import vip.xuanhao.integration.views.fragments.PersonalFragment;
import vip.xuanhao.integration.views.fragments.SquareFragment;
import vip.xuanhao.integration.views.fragments.VideoFragment;

/**
 * Created by Xuanhao on 2016/11/4.
 */

@FragmentScope
@Component(dependencies = BaseApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(HomeFragment homeFragment);

    void inject(VideoFragment videoFragment);

    void inject(SquareFragment squareFragment);

    void inject(CommunityFragment communityFragment);

    void inject(GroupFragment groupFragment);

    void inject(PersonalFragment personalFragment);


}
