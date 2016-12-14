package vip.xuanhao.integration.model.domain;

/**
 * Created by codeest on 16/8/27.
 */

public class NightModeEvent {

    private boolean isNightMode;

    public void setNightMode(boolean nightMode) {
        isNightMode = nightMode;
    }

    public boolean getNightMode() {
        return isNightMode;
    }
}
