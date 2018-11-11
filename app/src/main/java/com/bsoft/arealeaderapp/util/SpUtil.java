package com.bsoft.arealeaderapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Map;

/**
 * @author Artolia Pendragon
 */
public class SpUtil {

    private static final String FILE_NAME = "share_data";

    /**
     * 保存数据到文件中
     */
    public static void put(@NonNull final Context context, @NonNull String key,
                           @NonNull Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        editor.apply();
    }

    /**
     * 从文件中取数据
     */
    public static Object get(@NonNull final Context context, @NonNull String key,
                             Object defaultObject) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 删除数据
     */
    public static void remove(@NonNull final Context context, @NonNull String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     */
    public static void clear(@NonNull final Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 查询是否包含某个key
     */
    public static boolean contains(@NonNull final Context context, @NonNull String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
    }

    /**
     * 获取所有键值对
     */
    public static Map<String, ?> getAll(@NonNull final Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getAll();
    }

}
