package com.wochacha.learnrxjava.network.api;

import com.wochacha.learnrxjava.model.ZhuangBiImage;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by guanghui_wan on 2016/11/30.
 */

public interface ZhuangBiApi {

    @GET("search")
    Observable<List<ZhuangBiImage>> search(@Query("q")String query);
//    Observable<String> search(@Query("q")String query);

}
