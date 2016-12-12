package com.wochacha.learnrxjava.model;

/**
 * @version V9.0.0
 * @author: guanghui_wan
 * @date: 2016/12/12
 */

public class FakeToken {

    public String token;
    public boolean expired;

    public FakeToken() {
    }

    public FakeToken(boolean expired) {
        this.expired = expired;
    }
}
