package com.wochacha.learnrxjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by guanghui_wan on 2016/12/1.
 */

public class GankBeautyResult {
    public boolean error;
    public @SerializedName("results") List<GankBeauty> beauties;


}
