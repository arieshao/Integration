package vip.xuanhao.integration.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;

public class SevenFragment extends Fragment {


    @BindView(R.id.content)
    ListView contentListview;

    private Context mContext;
    private List<String> strings = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_seven, container, false);
        ButterKnife.bind(this, convertView);
        mContext = getActivity();

        initData();
        setAdapter();

        return convertView;
    }

    private void setAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, strings);
        contentListview.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 15; i++) {
            strings.add(i + "");
        }
    }
}
