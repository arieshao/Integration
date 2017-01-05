package vip.xuanhao.integration.app.common;

import java.io.File;

import vip.xuanhao.integration.R;
import vip.xuanhao.integration.app.base.BaseApplication;

/**
 * Created by Xuanhao on 2016/12/7.
 */

public class Commons {

    /**
     * 状态栏颜色
     */
    public static final int STATUSBARCOLOR = R.color.color_primary_dark;

    /**
     * 缓存目录
     */
    public static final File CACHE_FOLDER = BaseApplication.getAppComponent().context().getCacheDir();

}
