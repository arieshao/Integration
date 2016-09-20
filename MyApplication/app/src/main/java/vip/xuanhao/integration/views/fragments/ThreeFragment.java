package vip.xuanhao.integration.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.views.adapters.ThereAdapter;

/**
 * Created by Xuanhao on 2016/8/11.
 */

public class ThreeFragment extends Fragment implements
        SwipeRefreshLayout.OnRefreshListener, ThereAdapter.OnItemClickListener {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.srl_swipe)
    SwipeRefreshLayout srlSwipe;

    private List<String> dataSource;
    private ThereAdapter thereAdapter;
    private Context mContext;

    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_three, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, contentView);
        initData();
        setAdapter();
        initEvent();
        return contentView;
    }

    private void setAdapter() {
        thereAdapter = new ThereAdapter(mContext, dataSource);
        linearLayoutManager = new LinearLayoutManager(mContext);
        recycleView.setLayoutManager(linearLayoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(thereAdapter);
        thereAdapter.setOnItemClickListener(this);
    }

    private void initData() {
        dataSource = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataSource.add("dataSource -> " + i);
        }
    }


    private void initEvent() {
        srlSwipe.setOnRefreshListener(this);
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int last = linearLayoutManager.findLastVisibleItemPosition();
                final int itemCount = thereAdapter.getItemCount();
                if (last + 1 == itemCount) {
                    recycleView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            thereAdapter.notifyItemRemoved(itemCount);
                        }
                    }, 1000);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        srlSwipe.postDelayed(new Runnable() {
            @Override
            public void run() {
                srlSwipe.setRefreshing(false);
            }
        }, 1000);

    }

    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
    }
}
