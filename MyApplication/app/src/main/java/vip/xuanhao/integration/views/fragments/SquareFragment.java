package vip.xuanhao.integration.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.ISquarePresenter;
import vip.xuanhao.integration.presenters.SquarePresenter;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.ui.ExpandFooterView;
import vip.xuanhao.integration.views.ui.ExpandHeaderView;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class SquareFragment extends BaseFragment implements BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener {

    @BindView(R.id.square_header)
    ExpandHeaderView squareHeader;
    @BindView(R.id.square_footer)
    ExpandFooterView squareFooter;
    @BindView(R.id.lv_square_content)
    ListView mSquareContent;

    private ISquarePresenter iSquarePresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View personalRootView = inflater.inflate(R.layout.fragment_square, container, false);
        ButterKnife.bind(this, personalRootView);
        return personalRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iSquarePresenter = new SquarePresenter();
        initView();
        initEvent();
    }

    ArrayAdapter<String> adapter;

    @Override
    public void initView() {
        adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, iSquarePresenter.getDataSource());
        mSquareContent.setAdapter(adapter);
    }


    @Override
    public void initEvent() {
        squareHeader.setOnRefreshListener(this);
        squareFooter.setOnLoadListener(this);
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {

        baseFooterView.postDelayed(new Runnable() {
            @Override
            public void run() {
                iSquarePresenter.getSquareDataFromNet();
                adapter.notifyDataSetChanged();
                squareFooter.stopLoad();
            }
        }, 2000);

    }


    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
                iSquarePresenter.getSquareDataFromNet();
                adapter.notifyDataSetChanged();
                squareHeader.stopRefresh();
            }
        }, 2000);
    }
}
