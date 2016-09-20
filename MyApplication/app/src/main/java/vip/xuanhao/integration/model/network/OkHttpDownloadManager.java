package vip.xuanhao.integration.model.network;

/**
 * Created by Xuanhao on 2016/8/29.
 */

public class OkHttpDownloadManager {


    private static OkHttpDownloadManager downloadManagre = null;

    private OkHttpDownloadManager() {



    }

    public static OkHttpDownloadManager getInstance() {

        if (downloadManagre == null) {
            synchronized (OkHttpDownloadManager.class) {
                if (downloadManagre == null) {
                    downloadManagre = new OkHttpDownloadManager();
                }
            }
        }
        return downloadManagre;
    }
}
