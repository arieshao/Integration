package vip.xuanhao.integration.presenters.exception;

import android.support.v4.app.FragmentManager;

import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import vip.xuanhao.integration.presenters.ipresenter.IGodPresenter;
import vip.xuanhao.integration.views.adapters.MainPagerAdapter;

/**
 * Created by Xuanhao on 2016/9/22.
 */

public interface IMainPresenter extends IGodPresenter {


    MainPagerAdapter getPagerAdapter(FragmentManager fragmentManager);

    ArrayList<CustomTabEntity> getTabData();
}
