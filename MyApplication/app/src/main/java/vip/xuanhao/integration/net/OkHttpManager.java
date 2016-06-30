package vip.xuanhao.integration.net;

/**
 * Created by Xuanhao on 2016/6/30.
 */
public class OkHttpManager {

    private static OkHttpManager ourInstance = new OkHttpManager();

    public static OkHttpManager getInstance() {
        return ourInstance;
    }

    private OkHttpManager() {
    }
}
