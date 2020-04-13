package com.djm.seckill.model;

import com.djm.seckill.enums.ErrorEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RespResult<T> {
    private int code;
    private String message;
    private T data;

    public static <T> RespResult<T> ofSuccess(T data) {
        RespResult<T> result = new RespResult<>();
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> RespResult<T> ofSuccessMsg(String message) {
        RespResult<T> result = new RespResult<>();
        result.setMessage(message);
        return result;
    }

    public static <T> RespResult<T> ofFail(ErrorEnum errorEnum) {
        RespResult<T> result = new RespResult<>();
        result.setCode(errorEnum.getErrCode());
        result.setMessage(errorEnum.getErrMsg());
        return result;
    }

    public static <T> RespResult<T> ofFail(int code, String message) {
        RespResult<T> result = new RespResult<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
