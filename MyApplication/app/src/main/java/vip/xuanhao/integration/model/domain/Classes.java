package vip.xuanhao.integration.model.domain;

import io.realm.RealmObject;

/**
 * Created by Xuanhao on 2016/7/8.
 */

public class Classes extends RealmObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
