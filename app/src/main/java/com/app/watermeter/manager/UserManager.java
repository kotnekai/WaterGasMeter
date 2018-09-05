package com.app.watermeter.manager;

import android.util.Log;

import com.app.okhttputils.Model.Result;
import com.app.okhttputils.callback.GenericsCallback;
import com.app.okhttputils.request.JsonGenericsSerializator;
import com.app.watermeter.common.CommonUrl;
import com.app.watermeter.eventBus.CheckSmsCodeEvent;
import com.app.watermeter.eventBus.LoginEvent;
import com.app.watermeter.eventBus.PersonInfoEvent;
import com.app.watermeter.eventBus.RegisterInfoEvent;
import com.app.watermeter.eventBus.SuccessEvent;
import com.app.watermeter.model.UserInfoModel;
import com.app.watermeter.model.UserInfoParam;
import com.app.watermeter.okhttp.DataManager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
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

    /**
     * 登录
     *
     * @param contact
     * @param passwd
     */
    public void login(String contact, String passwd) {
        JSONObject params = new JSONObject();
        try {
            params.put("contact", contact);
            params.put("passwd", passwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dataInstance.sendPostRequestData(CommonUrl.LOGIN, params)
                .execute(new GenericsCallback<Result>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Response response, Call call, Exception e, int id) {
                        String message = e.getMessage();
                      /*  String errorMsg = JsonUtils.getErrorMsg(response);
                        EventBus.getDefault().post(new ErrorResponseEvent(errorMsg, CommonPageState.login_page));*/
                    }

                    @Override
                    public void onNetWorkError(Response response, String errorMsg, int NetWorkCode) {

                    }

                    @Override
                    public void onResponse(Result result, int id) {
                        EventBus.getDefault().post(new LoginEvent(result));
                    }
                });
    }

    /**
     * 发送短信验证码
     *
     * @param contact
     * @param type
     */
    public void sendSmsToCheck(String contact, String type) {
        JSONObject params = new JSONObject();
        try {
            params.put("contact", contact);
            params.put("type", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dataInstance.sendPostRequestData(CommonUrl.SEND_SMS, params)
                .execute(new GenericsCallback<Result>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Response response, Call call, Exception e, int id) {
                        String message = e.getMessage();
                      /*  String errorMsg = JsonUtils.getErrorMsg(response);
                        EventBus.getDefault().post(new ErrorResponseEvent(errorMsg, CommonPageState.login_page));*/

                    }

                    @Override
                    public void onNetWorkError(Response response, String errorMsg, int NetWorkCode) {

                    }

                    @Override
                    public void onResponse(Result result, int id) {
//                        ComResponseModel model =(ComResponseModel) result.getData();
                        EventBus.getDefault().post(new SuccessEvent(result));

                    }
                });
    }

    /**
     * 校验短信验证码
     *
     * @param contact
     * @param type
     * @param vcode
     */
    public void checkSmsCode(String contact, String type, String vcode) {
        JSONObject params = new JSONObject();
        try {
            params.put("contact", contact);
            params.put("type", type);
            params.put("vcode", vcode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dataInstance.sendPostRequestData(CommonUrl.CHECK_SMS_CODE, params)
                .execute(new GenericsCallback<Result>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Response response, Call call, Exception e, int id) {
                        String message = e.getMessage();
                      /*  String errorMsg = JsonUtils.getErrorMsg(response);
                        EventBus.getDefault().post(new ErrorResponseEvent(errorMsg, CommonPageState.login_page));*/
                        Log.d("admin", "onError: message=" + message);
                    }

                    @Override
                    public void onNetWorkError(Response response, String errorMsg, int NetWorkCode) {

                    }

                    @Override
                    public void onResponse(Result result, int id) {
//                        ComResponseModel model =(ComResponseModel) result.getData();
                        EventBus.getDefault().post(new CheckSmsCodeEvent(result));
                    }


                });
    }

    /**
     * 注册个人信息
     *
     * @param userInfoParam
     */
    public void register(UserInfoParam userInfoParam) {
        JSONObject params = new JSONObject();
        try {
            params.put("contact", userInfoParam.getContact());
            params.put("passwd", userInfoParam.getPassword());
            params.put("passwd_confirmation", userInfoParam.getConfirmPsw());
            params.put("email", userInfoParam.getEmail());
            params.put("real_name", userInfoParam.getRealName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dataInstance.sendPostRequestData(CommonUrl.REGISTER, params)
                .execute(new GenericsCallback<Result>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Response response, Call call, Exception e, int id) {
                        String message = e.getMessage();
                      /*  String errorMsg = JsonUtils.getErrorMsg(response);
                        EventBus.getDefault().post(new ErrorResponseEvent(errorMsg, CommonPageState.login_page));*/
                        Log.d("admin", "onError: message=" + message);
                    }

                    @Override
                    public void onNetWorkError(Response response, String errorMsg, int NetWorkCode) {

                    }

                    @Override
                    public void onResponse(Result result, int id) {
//                        LoginInfoModel model = (LoginInfoModel) result.getData();
                        EventBus.getDefault().post(new RegisterInfoEvent(result));
                        Log.d("admin", "onResponse: response=" + result);
                    }
                });
    }

    /**
     * 请求个人信息
     */
    public void getPersonInfo() {
        dataInstance.sendGetRequestData(CommonUrl.GET_PERSON_INFO, null)
                .execute(new GenericsCallback<Result>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Response response, Call call, Exception e, int id) {
                        String message = e.getMessage();
                      /*  String errorMsg = JsonUtils.getErrorMsg(response);
                        EventBus.getDefault().post(new ErrorResponseEvent(errorMsg, CommonPageState.login_page));*/
                    }

                    @Override
                    public void onNetWorkError(Response response, String errorMsg, int NetWorkCode) {

                    }

                    @Override
                    public void onResponse(Result result, int id) {
                        UserInfoModel model = (UserInfoModel) result.getData();
                        EventBus.getDefault().post(new PersonInfoEvent(model));
                    }
                });
    }

    /**
     * 重置密码
     *
     * @param password
     * @param confirmPassword
     */
    public void resetPassword(String password, String confirmPassword) {
        JSONObject params = new JSONObject();
        try {
            params.put("passwd", password);
            params.put("passwd_confirmation", confirmPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dataInstance.sendPostRequestData(CommonUrl.RESET_PASSWORD, params)
                .execute(new GenericsCallback<Result>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Response response, Call call, Exception e, int id) {

                    }

                    @Override
                    public void onNetWorkError(Response response, String errorMsg, int NetWorkCode) {

                    }

                    @Override
                    public void onResponse(Result result, int id) {

//                        ComResponseModel model = (ComResponseModel) result.getData();
                        EventBus.getDefault().post(new SuccessEvent(result));
                    }
                });
    }

    /**
     * 退出登录
     */
    public void loginOut() {
        dataInstance.sendPostRequestData(CommonUrl.LOGIN_OUT, null)
                .execute(new GenericsCallback<Result>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Response response, Call call, Exception e, int id) {

                    }

                    @Override
                    public void onNetWorkError(Response response, String errorMsg, int NetWorkCode) {

                    }

                    @Override
                    public void onResponse(Result result, int id) {

                        // EventBus.getDefault().post(new SuccessEvent(response));
                    }
                });
    }
}
