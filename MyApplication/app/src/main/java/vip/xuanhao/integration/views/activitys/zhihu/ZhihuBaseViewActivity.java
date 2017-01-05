package vip.xuanhao.integration.views.activitys.zhihu;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;
import com.r0adkll.slidr.Slidr;
import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.app.base.BaseViewActivity;
import vip.xuanhao.integration.app.common.Commons;
import vip.xuanhao.integration.presenters.BasePresenter;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.zhihu.IZhiHuBaseViewView;
import vip.xuanhao.integration.views.ui.ExpandFooterView;
import vip.xuanhao.integration.views.ui.ExpandHeaderView;

/**
 * Created by Xuanhao on 2016/12/15.
 */

public abstract class ZhihuBaseViewActivity<T extends BasePresenter> extends BaseViewActivity<T> implements IZhiHuBaseViewView
        , BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener, IOnRecycleViewItemClickListener {

    @BindView(R.id.toolbar_allpage)
    Toolbar toolbarAllpage;
    @BindView(R.id.img_loading)
    ImageView imgLoading;
    @BindView(R.id.zhihu_news_content)
    RecyclerView zhihuNewsContent;
    @BindView(R.id.zhihu_news_header)
    ExpandHeaderView zhihuNewsHeader;
    @BindView(R.id.zhihu_news_footer)
    ExpandFooterView zhihuNewsFooter;


    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(Commons.STATUSBARCOLOR), 0);
    }

    @Override
    protected void OtherSetting() {
        Slidr.attach(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_dipole;
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        zhihuNewsContent.setLayoutManager(linearLayoutManager);
        zhihuNewsContent.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void stopRefresh() {
        zhihuNewsHeader.stopRefresh();
    }

    @Override
    public void stopLoad() {
        zhihuNewsFooter.stopLoad();
    }

    @Override
    public void initEvent() {
        zhihuNewsHeader.setOnRefreshListener(this);
        zhihuNewsFooter.setOnLoadListener(this);
    }
}
