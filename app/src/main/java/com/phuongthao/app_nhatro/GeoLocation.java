package com.phuongthao.app_nhatro;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

class GeoLocation {
    public static void getAddress(final String locationAddress, final Context context, final Handler handler){
        Thread thread = new Thread(){
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String resultLat = null;
                String resultLon = null;
                try {
                    List addressList = geocoder.getFromLocationName(locationAddress, 1);
                    if(addressList != null && addressList.size() >0 ){
                        Address address = (Address) addressList.get(0);
                        StringBuilder stringBuilder = new StringBuilder();
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder.append(address.getLatitude());
                        stringBuilder1.append(address.getLongitude());
                        resultLat = stringBuilder.toString();
                        resultLon = stringBuilder1.toString();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if(resultLat != null && resultLon != null){
                        message.what = 1;
                        Bundle bundle = new Bundle();
                      //  result = "Address   :   "+locationAddress+ "\n\n\nLatitube And Longitude\n"+result;
                        bundle.putString("LAT", resultLat);
                        bundle.putString("LON", resultLon);
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }
}
