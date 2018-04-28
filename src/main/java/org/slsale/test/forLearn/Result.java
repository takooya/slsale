package org.slsale.test.forLearn;

/**
 * @Author takooya
 * @Description
 * @Date:created in 17:47 2018/4/28
 */
public class Result<T> {
    private T data;
    private boolean success;
    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }
}
