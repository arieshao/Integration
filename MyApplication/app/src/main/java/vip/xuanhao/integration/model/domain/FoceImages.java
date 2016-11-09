package vip.xuanhao.integration.model.domain;

/**
 * Created by Xuanhao on 2016/10/26.
 * <p>
 * 焦点图片 这里是测试使用的非标准化
 */

public class FoceImages {
    //加入引号的作用是给变量初始值.不需要每次get的时候判空
    private String id = "";
    private String imgUrl = "";  //焦点图
    private String url = "";    //帖子图  ps:接口设计成这样我也是醉了
    private String title = "";// description the picture


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
