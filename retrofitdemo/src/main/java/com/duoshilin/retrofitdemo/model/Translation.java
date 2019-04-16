package com.duoshilin.retrofitdemo.model;

import java.util.Arrays;

/**
 * Created by duoshilin on 2019/2/18.
 * URL模板
 * http://fy.iciba.com/ajax.php
 * <p>
 * URL实例
 * http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world
 * <p>
 * 参数说明：
 * a：固定值 fy
 * f：原文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
 * t：译文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
 * w：查询内容
 * <p>
 * 返回json
 * {
 * status: 1,
 * content: {
 * from: "en-EU",
 * to: "zh-CN",
 * out: "示例",
 * vendor: "ciba",
 * err_no: 0
 * }
 * }
 */
public class Translation {

    private String status;
    private Content content;

    private class Content {
        private String from;
        private String to;
        private String vendor; // 翻译来源平台
        private String out; //译文内容
        private String err_no; //请求成功是返回0,1
        private String[] word_mean; //外译中时有

    }

    public String show() {
        if (content.out != null) {
            return content.out;
        } else {
            if (content.word_mean == null) {
                return null;
            }
            StringBuffer sb = new StringBuffer();
            for (String word : content.word_mean) {
                sb.append(word + "\n");
            }
            return sb.toString();
        }
    }
}
