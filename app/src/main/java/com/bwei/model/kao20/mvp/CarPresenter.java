package com.bwei.model.kao20.mvp;

import com.bwei.model.kao20.Bean.CarBean;

import java.security.PublicKey;

/**
 * date:2018/12/20
 * author:郝仁（Thinkpad)
 * function:
 */
public class CarPresenter {

    private CarModel mCarModel;
    private CarView mCarView;

    public CarPresenter(CarView carView) {
        mCarView = carView;
        mCarModel = new CarModel();
    }


    public void car(final String s) {
        mCarModel.car(s, new CarCallBack() {
            @Override
            public void carSuccess(CarBean success) {
                 mCarView.carSuccess( success );
            }

            @Override
            public void carFail(String carfail) {
                 mCarView.carFail( carfail );
            }
        } );
    }
}
