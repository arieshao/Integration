package vip.xuanhao.integration.utils;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by Xuanhao on 2016/11/9.
 */

public class PreferenceHelper {

    private Context mContext;

    private SharedPreferences sharedPreferences;

    public static final String FIRST_INSTALL = "FIRST_INSTALL";


    @Inject
    public PreferenceHelper(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences("setting", Context.MODE_PRIVATE);
    }


    public void saveAppPre(boolean b) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(FIRST_INSTALL, b);
        edit.commit();
    }


    public boolean isFirstInstallApp() {
        return sharedPreferences.getBoolean(FIRST_INSTALL, true);
    }
}
