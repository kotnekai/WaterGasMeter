package com.app.watermeter.manager;

import android.util.Log;

import com.app.okhttputils.Model.Result;
import com.app.okhttputils.callback.GenericsCallback;
import com.app.okhttputils.request.JsonGenericsSerializator;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.common.CommonUrl;
import com.app.watermeter.eventBus.BindEvent;
import com.app.watermeter.eventBus.DefaultResultEvent;
import com.app.watermeter.eventBus.GetDetailReChargeListEvent;
import com.app.watermeter.eventBus.GetDetailReadListEvent;
import com.app.watermeter.eventBus.GetElectReChargeListEvent;
import com.app.watermeter.eventBus.GetElectReadListEvent;
import com.app.watermeter.eventBus.GetGasReChargeListEvent;
import com.app.watermeter.eventBus.GetGasReadListEvent;
import com.app.watermeter.eventBus.GetMeterInfoEvent;
import com.app.watermeter.eventBus.GetMeterListEvent;
import com.app.watermeter.eventBus.GetMeterTypeEvent;
import com.app.watermeter.eventBus.GetReChargeListEvent;
import com.app.watermeter.eventBus.GetReadListEvent;
import com.app.watermeter.eventBus.GetWaterReChargeListEvent;
import com.app.watermeter.eventBus.GetWaterReadListEvent;
import com.app.watermeter.eventBus.UnBindErrEvent;
import com.app.watermeter.eventBus.UnBindEvent;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.model.MeterTypeModel;
import com.app.watermeter.model.MeterReChargeModel;
import com.app.watermeter.model.MeterReadModel;
import com.app.watermeter.okhttp.DataManager;
import com.app.watermeter.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Create by Admin on 2018/9/3
 */
public class MeterManager {
    public static MeterManager instance = null;
    public static DataManager dataInstance = null;

    public Gson gson = new Gson();

    private MeterManager() {

    }

    public static MeterManager getInstance() {
        if (instance == null) {
            instance = new MeterManager();
        }
        if (dataInstance == null) {
            dataInstance = DataManager.getInstance();
        }
        return instance;
    }

    /**
     * 获取全部的表类型
     */
    public void getMeterType() {
        Map<String, String> params = new HashMap<>();
        dataInstance.sendGetRequestData(CommonUrl.METER_TYPE_URL, params)
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
                        Log.d("admin", "onError: errorMsg=" + errorMsg);
                    }

                    @Override
                    public void onResponse(Result result, int id) {
                        Log.d("admin", "getMeterType====onResponse: response=" + result);
                        String jsonString = gson.toJson(result.getData());

                        //保存表类型数据
                        PreferencesUtils.putString(CommonParams.METTER_TYPE_JSON, jsonString);

                        List<MeterTypeModel> list = gson.fromJson(jsonString.toString(), new TypeToken<List<MeterTypeModel>>() {
                        }.getType());

                        EventBus.getDefault().post(new GetMeterTypeEvent(list));
                    }
                });
    }

    /**
     * 获取列表数据,指定TYPE
     */
    public void getMeterList(final int meterType) {
        Map<String, String> params = new HashMap<>();
        params.put("type", meterType + "");
        dataInstance.sendGetRequestData(CommonUrl.METER_LIST_URL, params)
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
                        Log.d("admin", "onError: errorMsg=" + errorMsg);
                    }

                    @Override
                    public void onResponse(Result result, int id) {
                        Log.d("admin", "getMeterList====onResponse: response=" + result);
                        String jsonString = gson.toJson(result.getData());
                        List<MeterInfoModel> list = gson.fromJson(jsonString.toString(), new TypeToken<List<MeterInfoModel>>() {
                        }.getType());

                        EventBus.getDefault().post(new GetMeterListEvent(list, meterType));
                    }
                });
    }

    /**
     * 获取表详细
     */
    public void getMeterDetail(final String sn) {
        Map<String, String> params = new HashMap<>();
        params.put("sn", sn);
        dataInstance.sendGetRequestData(CommonUrl.METER_DETAIL_URL, params)
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
                        Log.d("admin", "onError: errorMsg=" + errorMsg);
                    }

                    @Override
                    public void onResponse(Result result, int id) {
                        Log.d("admin", "getMeterDetail====onResponse: response=" + result);
                        String jsonString = gson.toJson(result.getData());
                        MeterInfoModel model = gson.fromJson(jsonString.toString(), MeterInfoModel.class);
                        EventBus.getDefault().post(new GetMeterInfoEvent(model));
                    }
                });
    }

    /**
     * 表解绑
     */
    public void unbindMeter(final String sn) {
        Map<String, String> params = new HashMap<>();
        params.put("sn", sn);
        dataInstance.sendGetRequestData(CommonUrl.METER_UNBIND_URL, params)
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
                        Log.d("admin", "onError: errorMsg=" + errorMsg);
                        EventBus.getDefault().post(new UnBindErrEvent(errorMsg));
                    }

                    @Override
                    public void onResponse(Result result, int id) {
                        Log.d("admin", "unbindMeter====onResponse: response=" + result);
                        EventBus.getDefault().post(new UnBindEvent(result));
                    }
                });
    }

    /**
     * 表绑定
     */
    public void bindMeter(final String sn) {
        Map<String, String> params = new HashMap<>();
        params.put("sn", sn);
        dataInstance.sendGetRequestData(CommonUrl.METER_BIND_URL, params)
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
                        Log.d("admin", "onError: errorMsg=" + errorMsg);
                    }

                    @Override
                    public void onResponse(Result result, int id) {
                        Log.d("admin", "bindMeter====onResponse: response=" + result);
                        EventBus.getDefault().post(new BindEvent(result));
                    }
                });
    }

    /**
     * 获取缴费明细
     * https://www.showdoc.cc/web/#/137924192608060?page_id=789816901624533
     */
    public void getRePayList(int offset, int count, final int type, final int machine) {
        Map<String, String> params = new HashMap<>();
        params.put("offset", offset + "");
        params.put("count", count + "");
        params.put("type", type + "");
        if (machine > 0) {
            params.put("machine", machine + "");
        }
        dataInstance.sendGetRequestData(CommonUrl.METER_READ_LIST_URL, params)
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
                        Log.d("admin", "onError: errorMsg=" + errorMsg);
                    }

                    @Override
                    public void onResponse(Result result, int id) {
                        String jsonString = gson.toJson(result.getData());
                        List<MeterReadModel> list = gson.fromJson(jsonString.toString(), new TypeToken<List<MeterReadModel>>() {
                        }.getType());

                        if (machine > 0) {
                            EventBus.getDefault().post(new GetDetailReadListEvent(list));
                        } else {
                            switch (type) {
                                case MeterTypeModel.METER_WATER:
                                    EventBus.getDefault().post(new GetWaterReadListEvent(list));
                                    break;
                                case MeterTypeModel.METER_ELECT:
                                    EventBus.getDefault().post(new GetElectReadListEvent(list));

                                    break;
                                case MeterTypeModel.METER_GAS:
                                    EventBus.getDefault().post(new GetGasReadListEvent(list));
                                    break;
                            }
                        }

//                        EventBus.getDefault().post(new GetReadListEvent(list, type));
                    }
                });
    }

    /**
     * 获取预存明细
     * https://www.showdoc.cc/web/#/137924192608060?page_id=789816901624533
     */
    public void getReChargeList(int offset, int count, final int type, final int machine) {
        Map<String, String> params = new HashMap<>();
        params.put("offset", offset + "");
        params.put("count", count + "");
        params.put("type", type + "");
        if (machine > 0) {
            params.put("machine", machine + "");
        }
        dataInstance.sendGetRequestData(CommonUrl.METER_RECHARGE_LIST_URL, params)
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
                        Log.d("admin", "onError: errorMsg=" + errorMsg);
                    }

                    @Override
                    public void onResponse(Result result, int id) {
                        String jsonString = gson.toJson(result.getData());
                        List<MeterReChargeModel> list = gson.fromJson(jsonString.toString(), new TypeToken<List<MeterReChargeModel>>() {
                        }.getType());
                        if (machine > 0) {
                            EventBus.getDefault().post(new GetDetailReChargeListEvent(list));
                        } else {
                            switch (type) {
                                case MeterTypeModel.METER_WATER:
                                    EventBus.getDefault().post(new GetWaterReChargeListEvent(list));
                                    break;
                                case MeterTypeModel.METER_ELECT:
                                    EventBus.getDefault().post(new GetElectReChargeListEvent(list));
                                    break;
                                case MeterTypeModel.METER_GAS:
                                    EventBus.getDefault().post(new GetGasReChargeListEvent(list));
                                    break;
                            }
                        }
                    }
                });
    }
}
