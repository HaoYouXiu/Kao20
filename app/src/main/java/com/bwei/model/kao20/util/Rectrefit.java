package com.bwei.model.kao20.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * date:2018/12/20
 * author:郝仁（Thinkpad)
 * function:
 */
public class Rectrefit {

    private static Rectrefit instance;
    private final Retrofit mBuilder;


    private Rectrefit(){
          OkHttpClient builder = new OkHttpClient.Builder()
                  .readTimeout( 3000, TimeUnit.MILLISECONDS )
                  .writeTimeout( 3000, TimeUnit.MILLISECONDS )
                  .connectTimeout( 3000, TimeUnit.MILLISECONDS )
                  .retryOnConnectionFailure( true )
                  .build();
        mBuilder = new Retrofit.Builder()
        .baseUrl( Httputil.USER_URL )
        .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
        .addConverterFactory( GsonConverterFactory.create() )
        .client( builder )
        .build();
    }



    public static Rectrefit getInstance() {
        if (instance == null){
            synchronized (Rectrefit.class){
                if (instance == null){
                    instance = new Rectrefit();
                }
            }
        }
        return instance;
    }

    public <T> T Create(Class<T> tClass){
        return mBuilder.create( tClass );
    }
}
