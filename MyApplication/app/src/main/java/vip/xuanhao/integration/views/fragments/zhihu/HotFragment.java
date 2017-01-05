package vip.xuanhao.integration.views.fragments.zhihu;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.race604.drawable.wave.WaveDrawable;
import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.app.base.BaseFragment;
import vip.xuanhao.integration.presenters.ipresenter.zhihu.impl.HotPresenter;
import vip.xuanhao.integration.app.widget.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.zhihu.IHotViewView;
import vip.xuanhao.integration.app.widget.ui.ExpandFooterView;
import vip.xuanhao.integration.app.widget.ui.ExpandHeaderView;

/**
 * Created by Xuanhao on 2016/12/14.
 */

@Deprecated
public class HotFragment extends BaseFragment<HotPresenter> implements IHotViewView
        , BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener, IOnRecycleViewItemClickListener {

    @BindView(R.id.zhihu_news_content)
    RecyclerView zhihuNewsContent;
    @BindView(R.id.zhihu_news_header)
    ExpandHeaderView zhihuNewsHeader;
    @BindView(R.id.zhihu_news_footer)
    ExpandFooterView zhihuNewsFooter;
    @BindView(R.id.img_loading)
    ImageView imageView;


    private WaveDrawable mWaveDrawable;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_zhihu_news;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {

        mWaveDrawable = new WaveDrawable(mContext, R.drawable.christmastree);
        imageView.setImageDrawable(mWaveDrawable);
        mWaveDrawable.setIndeterminate(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        zhihuNewsContent.setLayoutManager(linearLayoutManager);
        zhihuNewsContent.setItemAnimator(new DefaultItemAnimator());
        zhihuNewsContent.setAdapter(presenter.getHotAdapter(mContext, this));
    }

    @Override
    public void initData() {
        presenter.getDataSource();
    }

    @Override
    public void initEvent() {
        zhihuNewsHeader.setOnRefreshListener(this);
        zhihuNewsFooter.setOnLoadListener(this);
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        presenter.getDataSource();
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        presenter.getDataSource();
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void update() {
        presenter.notifyUpdate();
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
    public void hiddenLoading() {
        mWaveDrawable.stop();
        imageView.setVisibility(View.GONE);
    }
}
