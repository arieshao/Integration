package vip.xuanhao.integration.views.activitys;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.flyco.tablayout.CommonTabLayout;
import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import rx.functions.Action1;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.app.base.BaseActivity;
import vip.xuanhao.integration.app.common.Commons;
import vip.xuanhao.integration.app.widget.ui.UnScrollViewPager;
import vip.xuanhao.integration.presenters.MainPresenter;
import vip.xuanhao.integration.utils.MainTabHelper;
import vip.xuanhao.integration.views.Iviews.IMainViewView;


public class MainActivity extends BaseActivity<MainPresenter> implements IMainViewView {

    @BindView(R.id.viewpager_main)
    UnScrollViewPager viewpagerMain;
    @BindView(R.id.main_tablayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.toolbar_allpage)
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    private View drawerContentView;

    private LayoutInflater mInflater;


    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }


    @Override
    @TargetApi(19)
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(Commons.STATUSBARCOLOR), 0);

    }

    @Override
    public void initView() {
        setToolBar(toolbar, "新闻");
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.syncState();
        drawer.addDrawerListener(actionBarDrawerToggle);
        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewpagerMain.setScrollable(false);
        viewpagerMain.setAdapter(presenter.getPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setTabData(presenter.getTabData());
        viewpagerMain.setOffscreenPageLimit(presenter.getTabData().size());
        MainTabHelper.bind(viewpagerMain, mTabLayout);
        drawerContentView = mInflater.inflate(R.layout.drawer_content, null, false);
        initDrawerView(drawerContentView);
    }


    Button btn_setting;

    private void initDrawerView(View drawerContentView) {
        btn_setting = (Button) drawerContentView.findViewById(R.id.btn_drawer_setting);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void initData() {
        requestPermission();
    }

    private void requestPermission() {
        RxPermissions.getInstance(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //请求网络
                        } else {
                            //对话框提示设置权限
                        }
                    }
                });
    }

    @Override
    public void initEvent() {
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpagerMain.setCurrentItem(3, false);
            }
        });
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(findViewById(R.id.left_drawer))) {
            drawer.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}
