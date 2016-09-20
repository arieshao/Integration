package vip.xuanhao.integration.presenters;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xuanhao on 2016/9/19.
 */

public class SquarePresenter implements ISquarePresenter {


    private List<String> dataSource = new ArrayList<>();


    private int pageNum = 1;


    @Override
    public boolean checkUser() {
        return false;
    }

    @Override
    public void onResume(Context mContext, String pageName) {

    }

    @Override
    public void onPause(Context mContext, String pageName) {

    }

    @Override
    public void release() {
        dataSource.clear();
        dataSource = null;
    }

    @Override
    public List<String> getDataSource() {
        return dataSource;
    }


    @Override
    public void getSquareDataFromNet() {
        dataSource.clear();
        for (int i = 0; i < 20; i++) {
            dataSource.add("这是一页  " + i + "  条");
        }
    }

    @Override
    public void getSquareMoreDataFromCache() {
        for (int i = 0; i < 20; i++) {
            dataSource.add("这是2页  " + i + "  条");
        }
    }
}
