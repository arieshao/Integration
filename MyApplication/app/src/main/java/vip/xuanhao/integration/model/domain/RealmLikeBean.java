package vip.xuanhao.integration.model.domain;

import java.io.Serializable;

/**
 * Created by codeest on 16/8/23.
 * 用于数据库的bean 种类包括 新闻、技术、福利
 */

public class RealmLikeBean implements Serializable {

    public RealmLikeBean() { }

    private String id;

    private String image;

    private String title;

    private int type;

    private long time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
