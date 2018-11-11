package com.bsoft.arealeaderapp.util;

import com.zhouyou.http.request.GetRequest;
import com.zhouyou.http.request.PostRequest;

/**
 * @author Artolia Pendragon
 */
public class HttpManager {

    public static GetRequest get(String url) {
        return new CustomGetRequest(url);
    }

    public static PostRequest post(String url) {
        return new CustomPostRequest(url);
    }
}
