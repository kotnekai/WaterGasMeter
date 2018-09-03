package com.app.watermeter.common;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;


import com.app.watermeter.utils.LanguageUtils;

import java.util.Locale;

/**
 * Created by admin on 2018/8/24.
 */

public class ChangeLanguageHelper {


    private static String country = null;

    private static String mLanguage = "";

    private static Resources mResources;

    private static String mAutoCountry;

    public static void init(Context context) {
        initResources(context);
        int currentLanguage = LanguageUtils.getAppLanguage();

        switch (currentLanguage) {
            case Constants.LANGUAGE_DEFAULT:
                country = context.getResources().getConfiguration().locale.getCountry();
                if ("TW".equals(country) || "HK".equals(country) || "MO".equals(country)) {
                    country = "CN";
                }
                if ("CN".equals(country)) {
                    mLanguage = "zh-CN";
                } else if ("US".equals(country)) {
                    mLanguage = "en";
                }
                break;
            case Constants.LANGUAGE_CHINA:
                country = "CN";
                mLanguage = "zh-CN";
                break;
            case Constants.LANGUAGE_ENGLISH:
                country = "US";
                mLanguage = "en";
                break;
            case Constants.LANGUAGE_KH:
                country = "KH";
                mLanguage = "km";
                break;
            default:
                country = context.getResources().getConfiguration().locale.getCountry();
                if ("CN".equals(country)) {
                    mLanguage = "zh-CN";
                } else if ("US".equals(country)) {
                    mLanguage = "en";
                }
                break;
        }

        mAutoCountry = Locale.getDefault().getCountry();
    }

    /**
     * 获取当前字符串资源的内容
     */
    public static String getStringById(int id) {
        String string;
        if (mLanguage != null && !"".equals(mLanguage)) {
            string = mResources.getString(id, mLanguage);
        } else {
            string = mResources.getString(id, "");
        }

        if (string != null && string.length() > 0) {
            return string;
        }
        return "";
    }

    public static void changeLanguage(Context context, int language) {
        switch (language) {
            case Constants.LANGUAGE_CHINA:
                mLanguage = "zh-CN";
                country = "CN";
                LanguageUtils.setAppLanguage(Constants.LANGUAGE_CHINA);
                break;
            case Constants.LANGUAGE_ENGLISH:
                mLanguage = "en";
                country = "US";
                LanguageUtils.setAppLanguage(Constants.LANGUAGE_ENGLISH);
                break;
            case Constants.LANGUAGE_DEFAULT:
//                country = Locale.getDefault().getCountry();
                country = mAutoCountry;
                if ("TW".equals(country) || "HK".equals(country) || "MO".equals(country)) {
                    country = "CN";
                }
                if ("CN".equals(country)) {
                    mLanguage = "zh-CN";
                } else if ("US".equals(country)) {
                    mLanguage = "en";
                }
                LanguageUtils.setAppLanguage(Constants.LANGUAGE_DEFAULT);

                break;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtils.applyChange(context);
        }
    }

    public static boolean getDefaultLanguage() {
        return ("CN".equals(country));
    }

    public static void initResources(Context context) {
        mResources = context.getResources();
    }
}
