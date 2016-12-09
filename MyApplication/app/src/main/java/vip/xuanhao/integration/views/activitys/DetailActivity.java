package vip.xuanhao.integration.views.activitys;

import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.views.BaseActivity;
import vip.xuanhao.integration.views.Iviews.IDetailView;

/**
 * Created by Xuanhao on 2016/12/5.
 */

public class DetailActivity extends BaseActivity implements IDetailView {

//    @BindView(R.id.lv_menu)
//    ListView lvMenu;
//    @BindView(R.id.drawer_layout)
//    DrawerLayout drawerLayout;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.tencent_web)
    WebView webView;



    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public void initInject() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_detail;
    }


    private void initWebConfig() {
        webView.loadUrl("http://www.baidu.com");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    public void initView() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.btn_guide, R.string.btn_jump);
//        actionBarDrawerToggle.syncState();
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringLIst);
//        lvMenu.setAdapter(adapter);
//        drawerLayout.closeDrawers();
        initWebConfig();

    }

    List<String> stringLIst;

    @Override
    public void initData() {
        stringLIst = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            stringLIst.add("" + i);
        }
    }

    @Override
    public void initEvent() {
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
