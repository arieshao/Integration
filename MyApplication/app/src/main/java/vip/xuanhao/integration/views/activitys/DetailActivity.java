package vip.xuanhao.integration.views.activitys;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;
import com.r0adkll.slidr.Slidr;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.app.common.Commons;
import vip.xuanhao.integration.model.domain.ZhihuDetailBean;
import vip.xuanhao.integration.presenters.ipresenter.DetailPresenter;
import vip.xuanhao.integration.utils.HtmlUtil;
import vip.xuanhao.integration.utils.ImageLoaderHelper;
import vip.xuanhao.integration.app.base.BaseActivity;
import vip.xuanhao.integration.views.Iviews.IDetailViewView;

/**
 * Created by Xuanhao on 2016/12/5.
 */

public class DetailActivity extends BaseActivity<DetailPresenter> implements IDetailViewView {
    @BindView(R.id.toolbar)
    Toolbar viewToolbar;
    @BindView(R.id.tencent_web)
    WebView webView;
    @BindView(R.id.img_detail_title)
    ImageView imgDetailTitle;
    @BindView(R.id.ctl_title)
    CollapsingToolbarLayout ctlTitle;

    private int id;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void OtherSetting() {
        Slidr.attach(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_detail;
    }


    private void initWebConfig() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    public void initView() {
        setToolBar(viewToolbar, "");
        initWebConfig();
    }


    @Override
    public void getIntentData() {
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
    }

    @Override
    public void initData() {
        presenter.getDataSource(id);
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(Commons.STATUSBARCOLOR),0);
    }

    @Override
    public void update(ZhihuDetailBean zhihuDetailBean) {
        ctlTitle.setTitle(zhihuDetailBean.getTitle());
        String htmlData = HtmlUtil.createHtmlData(zhihuDetailBean.getBody(), zhihuDetailBean.getCss(), zhihuDetailBean.getJs());
        webView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
        ImageLoaderHelper.loadImage(this, zhihuDetailBean.getImage(), imgDetailTitle);
    }

    @Override
    public void hiddenLoading() {

    }
}
