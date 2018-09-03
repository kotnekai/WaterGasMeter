package com.app.watermeter.okhttp;

import android.util.Log;

import com.app.watermeter.common.ApplicationHolder;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.utils.PreferencesUtils;
import com.app.okhttputils.builder.GetBuilder;
import com.app.okhttputils.builder.PostFileBuilder;
import com.app.okhttputils.builder.PostStringBuilder;
import com.app.okhttputils.callback.Callback;
import com.app.okhttputils.request.RequestCall;
import com.app.okhttputils.utils.OkHttpUtils;


import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;


/**
 * Created by admin on 2018/8/30.
 */

public class DataManager {

    public static DataManager instance = null;
    public final String MULTIPART_FORM_DATA = "image/*";       // 指明要上传的文件格式

    DataManager() {

    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    /**
     * 登录成功设置token，这里拿
     * @return
     */
    private String getToken() {
        return PreferencesUtils.getString(CommonParams.USER_TOKEN);
    }

    /**
     * 一般的发送get网络请求
     * @param url
     * @param params
     * @return
     */
    public RequestCall sendGetRequestData(String url, Map<String, String> params) {
        String token = getToken();
        GetBuilder getBuilder = OkHttpUtils.get().url(url);
        if (params != null) {
            getBuilder.params(params);
        }
        getBuilder.addHeader("Accept", "application/x.watermeter.v1+json");

        if (token != null) {
            getBuilder.addHeader("Authorization", "bearer " + token);
        }
        return getBuilder.build();
    }

    /**
     * 指定MediaType 的网络请求，
     * @param url
     * @param params
     * @param mediaType
     * @return
     */
    public RequestCall sendPostRequestData(String url, JSONObject params, MediaType mediaType) {
        String token = getToken();
        PostStringBuilder postStringBuilder = OkHttpUtils.postString().url(url);
        postStringBuilder.addHeader("Authorization", "bearer " + token);
        postStringBuilder.addHeader("Accept", "application/x.watermeter.v1+json");

        postStringBuilder.mediaType(mediaType);
        postStringBuilder.content(params.toString());
        return postStringBuilder.build();
    }

    /**
     * 默认的post请求
     * @param url
     * @param params
     * @return
     */
    public RequestCall sendPostRequestData(String url, JSONObject params) {
        String token = getToken();
        PostStringBuilder postStringBuilder = OkHttpUtils.postString().url(url);
        postStringBuilder.mediaType(MediaType.parse("application/json; charset=utf-8"));
        postStringBuilder.addHeader("Authorization", "bearer " + token);
        postStringBuilder.addHeader("Accept", "application/x.watermeter.v1+json");

        postStringBuilder.content(params.toString());

        return postStringBuilder.build();
    }

    /**
     * 只返回code的表单请求。在主线程中返回出去
     * @param url
     * @param params
     * @param listener
     */
    public void sendPostRequestData(String url, JSONObject params, final IGetResponseCodeListener listener) {
        String token = getToken();
        PostStringBuilder postStringBuilder = OkHttpUtils.postString().url(url);
        postStringBuilder.mediaType(MediaType.parse("application/json; charset=utf-8"));
        postStringBuilder.addHeader("Authorization", "bearer " + token);
        postStringBuilder.addHeader("Accept", "application/x.watermeter.v1+json");

        postStringBuilder.content(params.toString());
        postStringBuilder.build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(final Response response, final int i) throws Exception {
                final int code = response.code();
                final String string = response.body().string();
                ApplicationHolder.getInstance().postMainRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if(listener!=null){
                            listener.onSuccessResponse(code,string);
                        }
                    }
                });
                return null;
            }

            @Override
            public void onError(Response response, Call call, Exception e, int i) {
                if(listener!=null){
                    listener.onFailedResponse(response,e.getMessage());
                }
            }

            @Override
            public void onResponse(Object o, int i) {

            }
        });
    }

    /**
     * 图片等文件上传
     * @param url
     * @param file
     * @param listener
     */
    public void sendPostFileData(String url,File file, final IGetResponseCodeListener listener) {
        String token = getToken();
        PostFileBuilder postFileBuilder = OkHttpUtils.postFile();
        postFileBuilder.isFormSubmitFile = true;
        postFileBuilder.mediaType(MediaType.parse(MULTIPART_FORM_DATA));
        Log.d("xyc", "okHttpUpload: file=" + file);
        postFileBuilder.addHeader("Authorization", "bearer " + token);
        postFileBuilder.addHeader("Accept", "application/x.watermeter.v1+json");

        postFileBuilder.file(file);
        postFileBuilder.url(url);
        postFileBuilder.build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(final Response response, final int i) throws Exception {
                final int code = response.code();
                final String string = response.body().string();
                ApplicationHolder.getInstance().postMainRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if(listener!=null){
                            listener.onSuccessResponse(code,string);
                        }
                    }
                });

                return null;
            }

            @Override
            public void onError(Response response,Call call, Exception e, int i) {
               if(listener!=null){
                   listener.onFailedResponse(response,e.getMessage());
               }
            }

            @Override
            public void onResponse(Object o, int i) {

            }
        });

    }
}
