package com.bwei.model.kao20.mvp;

import com.bwei.model.kao20.Bean.CarBean;
import com.bwei.model.kao20.util.Rectrefit;
import com.bwei.model.kao20.util.Util;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/20
 * author:郝仁（Thinkpad)
 * function:
 */
public class CarModel {
    public void car(String s, final CarCallBack carCallBack) {
        Util create = Rectrefit.getInstance().Create( Util.class );
       create.getcar( s )
               .subscribeOn( Schedulers.io() )
               .subscribeOn( AndroidSchedulers.mainThread() )
               .subscribe( new Consumer<CarBean>() {
                   @Override
                   public void accept(CarBean carBean) throws Exception {
                       carCallBack.carSuccess( carBean );
                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {
                       carCallBack.carFail( "失败" );
                   }
               } );
    }
}
