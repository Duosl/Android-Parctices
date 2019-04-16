package com.duoshilin.retrofitdemo.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by duoshilin on 2019/2/19.
 * {
 * "translateResult": [
 * [{
 * "tgt": "支付",
 * "src": "pay"
 * }]
 * ],
 * "errorCode": 0,
 * "type": "en2zh-CHS",
 * "smartResult": {
 * "entries": ["", "n. 工资，薪水；付款；报答\r\n", "vt. 支付，付；偿还，补偿；给予\r\n", "vi. 付款；偿还\r\n"],
 * "type": 1
 * }
 * }
 */


public class Translation2 {
    private int errorCode;
    private String type;
    private TranslateResult[] translateResult;
    private SmartResult smartResult;

    private class TranslateResult {
        String tgt;
        String src;

        @Override
        public String toString() {
            return "TranslateResult{" +
                    "tgt='" + tgt + '\'' +
                    ", src='" + src + '\'' +
                    '}';
        }
    }

    private class SmartResult {
        String type;
        String[] entrties;
    }

    @Override
    public String toString() {
        return "Translation2{" +
                "errorCode=" + errorCode +
                ", type='" + type +
                ", translateResult=" + Arrays.toString(translateResult) +
//                ", smartResult=" + smartResult==null ? "[]" : Arrays.toString(smartResult.entrties) +
                '}';
    }
}
