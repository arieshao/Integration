package vip.xuanhao.integration.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.GroupPresenter;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.Iviews.IGroupView;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class GroupFragment extends BaseFragment implements IGroupView {

    @BindView(R.id.lv_test)
    ListView lvTest;

    @Inject
    GroupPresenter groupPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View personalRootView = inflater.inflate(R.layout.fragment_group, container, false);
        ButterKnife.bind(this, personalRootView);
        return personalRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getFragmentComponent().inject(this);
        initView();
    }

    @Override
    public void initView() {

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add(" " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, strings);
        lvTest.setAdapter(adapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
