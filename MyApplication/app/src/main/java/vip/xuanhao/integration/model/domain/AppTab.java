package vip.xuanhao.integration.model.domain;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by Xuanhao on 2016/9/22.
 */

public class AppTab implements CustomTabEntity{

    private String tabName;
    private int tabUnSelected;
    private int tabSelected;

    public AppTab(String tabName, int tabUnSelected, int tabSelected) {
        this.tabName = tabName;
        this.tabUnSelected = tabUnSelected;
        this.tabSelected = tabSelected;
    }


    @Override
    public String getTabTitle() {
        return tabName;
    }

    @Override
    public int getTabSelectedIcon() {
        return tabUnSelected;
    }

    @Override
    public int getTabUnselectedIcon() {
        return tabSelected;
    }
}
