package com.app.okhttputils.request;


import com.google.gson.Gson;
import com.app.okhttputils.callback.IGenericsSerializator;

/**
 * Created by admin on 2018/8/23.
 */
public class JsonGenericsSerializator implements IGenericsSerializator {
    Gson mGson = new Gson();
    @Override
    public <T> T transform(String response, Class<T> classOfT) {
        return mGson.fromJson(response, classOfT);
    }
}
