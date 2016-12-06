package com.wochacha.learnrxjava.module.map_2;


import com.wochacha.learnrxjava.model.GankBeauty;
import com.wochacha.learnrxjava.model.GankBeautyResult;
import com.wochacha.learnrxjava.model.Item;

import java.util.List;

import rx.functions.Func1;

/**
 * Created by guanghui_wan on 2016/12/6.
 */

public class GankBeautyResultToItemsMapper implements Func1<GankBeautyResult,List<Item>>{

    private static GankBeautyResultToItemsMapper INSTANCE = new GankBeautyResultToItemsMapper();

    public static GankBeautyResultToItemsMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Item> call(GankBeautyResult gankBeautyResult) {
        List<GankBeauty> gankBeauties = gankBeautyResult.beauties;

        return null;
    }
}
