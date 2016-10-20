package vip.xuanhao.integration.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.views.adapters.RecommAdapter;

/**
 * Created by Xuanhao on 2016/8/2.
 */

public class RecommentFragments extends Fragment {


    @BindView(R.id.rv_list)
    RecyclerView rvList;
    //    @BindView(R.id.sfl_refresh)
//    SwipeRefreshLayout sflRefresh;
    private List<String> dataSource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgment_recomment_test, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
    }

    RecommAdapter recommAdapter;

    private void initView() {
        recommAdapter = new RecommAdapter(getContext(), dataSource);

        LinearLayout.LayoutParams layoutParams = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.headerview_test, null, false);
        headerView.setLayoutParams(layoutParams);
        final View footer = LayoutInflater.from(getActivity()).inflate(R.layout.footerview_test, null, false);
        footer.setLayoutParams(layoutParams);
        final ProgressBar pb = (ProgressBar) footer.findViewById(R.id.progressBar);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(recommAdapter);
        rvList.addItemDecoration(new RecycleItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvList.setItemAnimator(new DefaultItemAnimator());
        recommAdapter.setHeaderView(headerView);
        recommAdapter.setFooterView(footer);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSource.add(0, "dataSource   >> " + dataSource.size() + 1);
                recommAdapter.notifyItemInserted(0);
                rvList.smoothScrollToPosition(0);
            }
        });

        recommAdapter.setOnItemClickListener(new RecommAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "你点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int itemCount = recyclerView.getLayoutManager().getItemCount();
//                Logger.w(itemCount + "");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int itemPosition = layoutManager.findLastVisibleItemPosition();
                if (itemPosition + 1 == recommAdapter.getItemCount()) {
                    Logger.w("itemPosition >>" + itemPosition);

                    rvList.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            recommAdapter.notifyItemRemoved(recommAdapter.getItemCount());
                        }
                    }, 100);
                }
            }
        });
    }

    boolean isLoading;

    private void initData() {
        dataSource = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataSource.add("dataSource   >> " + i);
        }
    }
}
