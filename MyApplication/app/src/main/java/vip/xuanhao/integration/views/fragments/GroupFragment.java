package vip.xuanhao.integration.views.fragments;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.app.base.BaseFragment;
import vip.xuanhao.integration.presenters.GroupPresenter;
import vip.xuanhao.integration.views.Iviews.IGroupViewView;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class GroupFragment extends BaseFragment<GroupPresenter> implements IGroupViewView {

    @BindView(R.id.lv_test)
    ListView lvTest;

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
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_group;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
