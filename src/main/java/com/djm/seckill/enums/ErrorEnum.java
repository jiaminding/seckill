package com.djm.seckill.enums;

public enum ErrorEnum {
    SYSTEM_ERROR(500, "系统异常");

    private int errCode;
    private String errMsg;

    private ErrorEnum(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public static final ErrorEnum getByCode(int errCode) {
        for (ErrorEnum item : ErrorEnum.values()) {
            if (errCode == item.getErrCode()) {
                return item;
            }
        }
        return null;
    }
}
