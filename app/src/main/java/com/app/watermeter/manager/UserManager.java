package com.app.watermeter.manager;

import android.util.Log;

import com.app.okhttputils.callback.GenericsCallback;
import com.app.okhttputils.request.JsonGenericsSerializator;
import com.app.watermeter.common.CommonUrl;
import com.app.watermeter.model.TestModel;
import com.app.watermeter.model.UserInfoModel;
import com.app.watermeter.okhttp.DataManager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Create by Admin on 2018/8/24
 */
public class UserManager {
    public static UserManager instance = null;
    public static DataManager dataInstance = null;

    private UserManager() {

    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        if (dataInstance == null) {
            dataInstance = DataManager.getInstance();
        }
        return instance;
    }

    public void login(String contact, String passwd) {
        JSONObject params = new JSONObject();
        try {
            params.put("contact", contact);
            params.put("passwd", passwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dataInstance.sendPostRequestData(CommonUrl.LOGIN, params, MediaType.parse("application/json; charset=utf-8"))
                .execute(new GenericsCallback<UserInfoModel>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Response response, Call call, Exception e, int id) {
                        String message = e.getMessage();
                      /*  String errorMsg = JsonUtils.getErrorMsg(response);
                        EventBus.getDefault().post(new ErrorResponseEvent(errorMsg, CommonPageState.login_page));*/

                    }

                    @Override
                    public void onResponse(UserInfoModel response, int id) {
                        // EventBus.getDefault().post(new LoginEvent(response));
                        Log.d("xyc", "onResponse: response="+response);
                    }
                });
    }
    public void testIt(){
        dataInstance.sendGetRequestData(CommonUrl.TEST, null)
                .execute(new GenericsCallback<TestModel>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Response response, Call call, Exception e, int id) {
                        String message = e.getMessage();
                      /*  String errorMsg = JsonUtils.getErrorMsg(response);
                        EventBus.getDefault().post(new ErrorResponseEvent(errorMsg, CommonPageState.login_page));*/

                    }

                    @Override
                    public void onResponse(TestModel response, int id) {
                        // EventBus.getDefault().post(new LoginEvent(response));
                        Log.d("xyc", "onResponse: response="+response);
                    }
                });
    }
}
