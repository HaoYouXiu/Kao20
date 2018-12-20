package com.bwei.model.kao20.mvp;

import com.bwei.model.kao20.Bean.CarBean;

/**
 * date:2018/12/20
 * author:郝仁（Thinkpad)
 * function:
 */
public interface CarView {
    void carSuccess(CarBean success);
    void carFail(String carfail);
}
