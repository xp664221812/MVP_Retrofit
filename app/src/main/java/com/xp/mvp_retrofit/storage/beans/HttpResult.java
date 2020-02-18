package com.xp.mvp_retrofit.storage.beans;

import androidx.annotation.NonNull;

public class HttpResult<T> extends BaseBean{
    public T data;
//    public int errorCode;
//    public String errorMsg;


    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[data: " + this.data + ", ");
        sb.append("errorCode: " + this.errorCode + ", ");
        sb.append("errorMsg: " + this.errorMsg + "]");
        return sb.toString();
    }
}
