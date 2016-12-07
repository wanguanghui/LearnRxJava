package com.wochacha.learnrxjava.module.map_2;


import com.wochacha.learnrxjava.model.GankBeauty;
import com.wochacha.learnrxjava.model.GankBeautyResult;
import com.wochacha.learnrxjava.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

//        Logger.json(new Gson().toJson(gankBeautyResult));
//        Logger.e(gankBeautyResult.toString());

        List<GankBeauty> gankBeauties = gankBeautyResult.beauties;
        List<Item> items = new ArrayList<>(gankBeauties.size());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for (GankBeauty gankBeauty:gankBeauties){
            Item item = new Item();
            try {
                Date date = inputFormat.parse(gankBeauty.getCreatedAt());
                item.setDescription(outputFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
                item.setDescription("unknown data");
            }
            item.setImageUrl(gankBeauty.getUrl());
            items.add(item);
        }
        return items;
    }
}
