package vip.xuanhao.integration.model.domain;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Xuanhao on 2016/7/8.
 */

public class Student extends RealmObject {


    private String name;
    private String id;
    private String age;
    private RealmList<Classes> classes;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public RealmList<Classes> getClasses() {
        return classes;
    }

    public void setClasses(RealmList<Classes> classes) {
        this.classes = classes;
    }
}
