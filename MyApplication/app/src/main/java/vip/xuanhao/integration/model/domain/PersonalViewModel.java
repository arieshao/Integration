package vip.xuanhao.integration.model.domain;

/**
 * Created by Xuanhao on 2016/9/14.
 * 个人中心条条目
 */

public class PersonalViewModel {

    public static final int TYPE_JUMP = 1;
    public static final int TYPE_UNJUMP = 2;


    private String itemName;
    private int itemDrawable;
    private int itemType;
    private String notifyMessage;


    public PersonalViewModel(String itemName, int itemDrawable, int itemType, String notifyMessage) {
        this.itemName = itemName;
        this.itemDrawable = itemDrawable;
        this.itemType = itemType;
        this.notifyMessage = notifyMessage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemDrawable() {
        return itemDrawable;
    }

    public void setItemDrawable(int itemDrawable) {
        this.itemDrawable = itemDrawable;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getNotifyMessage() {
        return notifyMessage;
    }

    public void setNotifyMessage(String notifyMessage) {
        this.notifyMessage = notifyMessage;
    }
}
