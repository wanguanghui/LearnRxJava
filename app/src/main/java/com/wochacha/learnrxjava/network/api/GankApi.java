package com.wochacha.learnrxjava.network.api;

import com.wochacha.learnrxjava.model.GankBeautyResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by guanghui_wan on 2016/12/1.
 */

public interface GankApi {
    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult> getBeauties(@Path("number")int number,@Path("page")int page);
}
