package com.hngy.attendance.common.lang;

import lombok.Data;

@Data
public class ResponseFormat {
    private int code;
    private String msg;
    private Object data;

    //    操作成功调用
    //    有返回
    public static ResponseFormat successful(Object data) {
        return operate(200, "操作成功", data);
    }

    //     无返回
    public static ResponseFormat successful() {
        return operate(200, "操作成功", null);
    }

    //    操作失败调用
    public static ResponseFormat fail(String msg) {
        return operate(400, msg, null);
    }

    //    其他操作调用
    public static ResponseFormat operate(int code, String msg, Object data) {
        ResponseFormat util = new ResponseFormat();
        util.setCode(code);
        util.setMsg(msg);
        util.setData(data);
        return util;
    }

}
