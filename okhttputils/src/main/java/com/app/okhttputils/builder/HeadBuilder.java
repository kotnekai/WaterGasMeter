package com.app.okhttputils.builder;

import com.app.okhttputils.request.OtherRequest;
import com.app.okhttputils.request.RequestCall;
import com.app.okhttputils.utils.OkHttpUtils;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
