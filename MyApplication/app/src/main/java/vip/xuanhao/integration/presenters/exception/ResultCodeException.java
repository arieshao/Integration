package vip.xuanhao.integration.presenters.exception;

import java.util.HashMap;

/**
 * Created by Xuanhao on 2016/7/25.
 */

public class ResultCodeException extends RuntimeException {

    public ResultCodeException(HashMap<String, String> dict, String detailMessage) {
        super(detailMessage);
    }
}
