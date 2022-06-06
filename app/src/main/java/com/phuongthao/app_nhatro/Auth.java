package com.phuongthao.app_nhatro;

import android.app.Application;

public class Auth extends Application {
    public String authID;

    public String getAuthID() {
        return authID;
    }

    public void setAuthID(String authID) {
        this.authID = authID;
    }
}
