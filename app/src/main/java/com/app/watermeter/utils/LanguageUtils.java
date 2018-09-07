package com.app.watermeter.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import com.app.watermeter.common.ComApplication;
import com.app.watermeter.common.Constants;

import java.util.Locale;

/**
 * Created by admin on 2018/8/24.
 */

public class LanguageUtils {


    /**
     * 获取语言
     *
     * @return
     */
    public static int getAppLanguage() {
        return PreferencesUtils.getInt(Constants.APP_LANGUAGE, Constants.LANGUAGE_DEFAULT);
    }

    /**
     * 保存语言
     *
     * @param languageType
     */
    public static void setAppLanguage(int languageType) {
        PreferencesUtils.putInt(Constants.APP_LANGUAGE, languageType);
    }


    public static Locale getSetLocale() {

        ComApplication.currentLanguage = getAppLanguage();

        switch (ComApplication.currentLanguage) {
            case Constants.LANGUAGE_DEFAULT:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //解决了获取系统默认错误的问题
                    return Resources.getSystem().getConfiguration().getLocales().get(0);
                } else {
                    return Locale.getDefault();
                }
            case Constants.LANGUAGE_CHINA:
                return Locale.CHINA;
            case Constants.LANGUAGE_ENGLISH:
                return Locale.ENGLISH;
            case Constants.LANGUAGE_KH:
                Locale locale = new Locale("km", "KH");
                return locale;
            default:
                return Locale.ENGLISH;
        }

    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Context wrapContext(Context context) {
        Resources resources = context.getResources();
        Locale locale = LanguageUtils.getSetLocale();

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        return context.createConfigurationContext(configuration);
    }

    /**
     * 设置语言
     * @param context
     */
    public static void applyChange(Context context) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        Locale locale = getSetLocale();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            conf.setLocale(locale);
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            conf.setLocales(localeList);
        } else {
            conf.locale = locale;
            try {
                conf.setLayoutDirection(locale);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        res.updateConfiguration(conf, dm);
    }


}
