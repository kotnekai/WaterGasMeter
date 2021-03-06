package com.app.okhttputils.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public abstract class Callback<T>
{
    /**
     * UI Thread
     *
     * @param request
     */
    public void onBefore(Request request, int id)
    {
    }

    /**
     * UI Thread
     *
     * @param
     */
    public void onAfter(int id)
    {
    }

    /**
     * UI Thread
     *
     * @param progress
     */
    public void inProgress(float progress, long total , int id)
    {

    }

    /**
     * if you parse reponse code in parseNetworkResponse, you should make this method return true.
     *
     * @param response
     * @return
     */
    public boolean validateReponse(Response response, int id)
    {
        return response.isSuccessful();
    }

    /**
     * Thread Pool Thread
     *
     * @param response
     */
    public abstract T parseNetworkResponse(Response response, int id) throws Exception;

    public abstract void onError(Response response, Call call, Exception e, int id);

    public abstract void onNetWorkError(Response response, String errorMsg, int NetWorkCode);


    public abstract void onResponse(T result, int id);

    public static Callback CALLBACK_DEFAULT = new Callback()
    {

        @Override
        public Object parseNetworkResponse(Response response, int id) throws Exception
        {

            return null;
        }

        @Override
        public void onError(Response response, Call call, Exception e, int id)
        {

        }

        @Override
        public void onNetWorkError(Response response, String errorMsg, int NetWorkCode) {

        }


        @Override
        public void onResponse(Object result, int id)
        {

        }

    };

}