package com.app.okhttputils.utils;

import com.app.okhttputils.Model.Result;
import com.app.okhttputils.builder.GetBuilder;
import com.app.okhttputils.builder.HeadBuilder;
import com.app.okhttputils.builder.OtherRequestBuilder;
import com.app.okhttputils.builder.PostFileBuilder;
import com.app.okhttputils.builder.PostFormBuilder;
import com.app.okhttputils.builder.PostStringBuilder;
import com.app.okhttputils.callback.Callback;
import com.app.okhttputils.request.RequestCall;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by zhy on 15/8/17.
 */
public class OkHttpUtils {
    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;

    public OkHttpUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        } else {
            mOkHttpClient = okHttpClient;
        }

        mPlatform = Platform.get();
    }


    public static OkHttpUtils initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtils getInstance() {
        return initClient(null);
    }


    public Executor getDelivery() {
        return mPlatform.defaultCallbackExecutor();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public static PostFileBuilder postFile() {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post() {
        return new PostFormBuilder();
    }

    public static OtherRequestBuilder put() {
        return new OtherRequestBuilder(METHOD.PUT);
    }

    public static HeadBuilder head() {
        return new HeadBuilder();
    }

    public static OtherRequestBuilder delete() {
        return new OtherRequestBuilder(METHOD.DELETE);
    }

    public static OtherRequestBuilder patch() {
        return new OtherRequestBuilder(METHOD.PATCH);
    }

    public void execute(final RequestCall requestCall, Callback callback) {
        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;
        final int id = requestCall.getOkHttpRequest().getId();

        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendFailResultCallback(null,call, e, finalCallback, id);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (call.isCanceled()) {
                        sendFailResultCallback(response,call, new IOException("Canceled!"), finalCallback, id);
                        return;
                    }

                    if (!finalCallback.validateReponse(response, id)) {
                        sendFailResultCallback(response,call, new IOException("request failed , reponse's code is : " + response.code()), finalCallback, id);
                        return;
                    }

                    sendSuccessResultCallback(response, finalCallback, id);

                } catch (Exception e) {
                    sendFailResultCallback(response,call, e, finalCallback, id);
                }

            }
        });
    }


    public void sendFailResultCallback(final Response response, final Call call, final Exception e, final Callback callback, final int id) {
        if (callback == null) return;

        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onError(response,call, e, id);
                callback.onAfter(id);
            }
        });
    }

    public void sendSuccessResultCallback(final Response response, final Callback callback, final int id) {
        if (callback == null) {
            return;
        }
        try {
            Object o = null;
            if (response.request().url().toString().contains("ace.trade.pay"))
            {
                final Result result = new Result();
                result.setMessage(response.message());
                result.setStatus_code(response.code());
                result.setData(response.body().string());
                mPlatform.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (result != null) {
                            callback.onResponse(result, id);
                            callback.onAfter(id);
                        }
                    }
                });
            }
            else {
                o = callback.parseNetworkResponse(response, id);
                final Result result = (Result) o;

                //判断网络返回200即成功
                if (result.isSuccess()) {
                    mPlatform.execute(new Runnable() {
                        @Override
                        public void run() {
                            if (result != null) {
                                callback.onResponse(result, id);
                                callback.onAfter(id);
                            }
                        }
                    });
                } else {

                    String errMessage = "";
                    switch (result.getStatus_code()) {
                        case Result.TOKEN_FAILD:
                            errMessage = "token过期，请重新登录";
                    }

                    final String finalErrMessage = errMessage;
                    mPlatform.execute(new Runnable() {
                        @Override
                        public void run() {
                            if (result != null) {
                                callback.onNetWorkError(response, finalErrMessage, result.getErr_code());
                                callback.onAfter(id);
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public static class METHOD {
        public static final String HEAD = "HEAD";
        public static final String DELETE = "DELETE";
        public static final String PUT = "PUT";
        public static final String PATCH = "PATCH";
    }
}

