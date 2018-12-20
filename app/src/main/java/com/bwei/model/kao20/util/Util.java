package com.bwei.model.kao20.util;

import com.bwei.model.kao20.Bean.CarBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/20
 * author:郝仁（Thinkpad)
 * function:
 */
public interface Util {

    @GET("product/getCarts")
    Observable<CarBean> getcar(@Query("uid") String uid);
}
