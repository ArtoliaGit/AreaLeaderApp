package com.bsoft.arealeaderapp.common;

import com.zhouyou.http.model.ApiResult;

import java.io.Serializable;

/**
 * @author Artolia Pendragon
 * @param <T>
 */
public class CustomApiResult<T> extends ApiResult<T> implements Serializable {

    public boolean isOk() {
        return getCode() == 200;
    }

}
