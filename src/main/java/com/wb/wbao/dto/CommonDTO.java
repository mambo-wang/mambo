package com.wb.wbao.dto;

public class CommonDTO<T> {

    public enum Result {
        SUCCESS,
        FAILURE,
        PARTIAL_SUCCESS
    }

    private Result result = Result.SUCCESS;

    private String message;

    private T data;

    public CommonDTO() {}

    public CommonDTO(String message) {
        this.message = message;
    }

    public CommonDTO(Result result, String message) {
        this.result = result;
        this.message = message;
    }

    public CommonDTO(Result result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
