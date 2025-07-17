package com.java.huhaoran;

import android.content.Context;
import android.content.SharedPreferences;


//用于获取当前用户状态的工具类，可以设置，查询当前的登录用户名，检查是否已登录，退出登录
public class UserManager {

    private static final String PREF_NAME = "user_prefs";
    private static final String KEY_USER_NAME = "userName";

    private static SharedPreferences sharedPreferences;

    // 初始化方法，在 MainActivity 中调用一次，获取SharedPreferences 对象，注意这个对象是静态的，只要初始化一次就可以一直使用
    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getApplicationContext()
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
    }

    // 设置当前用户名称（登录后调用）
    public static void setCurrentUserName(String userName) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(KEY_USER_NAME, userName).apply();
        }
    }

    // 获取当前用户名（如未登录返回 null）
    public static String getCurrentUserName() {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(KEY_USER_NAME, null);
        }
        return null;
    }

    // 检查是否已登录
    public static boolean isLoggedIn() {
        return getCurrentUserName() != null;
    }

    // 退出登录（清除记录）
    public static void logout() {
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(KEY_USER_NAME).apply();
        }
    }
}

