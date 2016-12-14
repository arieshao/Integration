package vip.xuanhao.integration.di.components;

import dagger.Component;
import vip.xuanhao.integration.di.modules.FragmentModule;
import vip.xuanhao.integration.di.scopes.FragmentScope;
import vip.xuanhao.integration.views.fragments.CommunityFragment;
import vip.xuanhao.integration.views.fragments.GroupFragment;
import vip.xuanhao.integration.views.fragments.HomeFragment;
import vip.xuanhao.integration.views.fragments.PersonalFragment;
import vip.xuanhao.integration.views.fragments.SquareFragment;
import vip.xuanhao.integration.views.fragments.VideoFragment;
import vip.xuanhao.integration.views.fragments.zhihu.DailyNewFragment;
import vip.xuanhao.integration.views.fragments.zhihu.HotFragment;
import vip.xuanhao.integration.views.fragments.zhihu.HotFragment2;
import vip.xuanhao.integration.views.fragments.zhihu.SpecialFragment;
import vip.xuanhao.integration.views.fragments.zhihu.ThemeFragment;
import vip.xuanhao.integration.views.fragments.zhihu.ZhiHuFragment;

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

    void inject(ZhiHuFragment zhiHuFragment);

    void inject(DailyNewFragment dailyNewFragment);

    void inject(ThemeFragment themeFragment);

    void inject(SpecialFragment specialFragment);

    void inject(HotFragment hotFragment);

    void inject(HotFragment2 hotFragment2);
}
