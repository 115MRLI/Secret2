package com.example.secret.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;



/**
 * 常用工具
 */
public class Utils {
    /**
     * 格式化银行卡卡号
     *
     * @param cardNumber
     * @return
     */
    public static String formatBankCard(String cardNumber) {
        if (TextUtils.isEmpty(cardNumber)) return cardNumber;
        int numberLength = cardNumber.length();
        String lastCardNumber = numberLength > 4 ?
                cardNumber.substring(numberLength - 4, numberLength) : cardNumber;
        return "**** **** **** " + lastCardNumber;
    }


    /**
     * 导航栏，状态栏隐藏
     *
     * @param activity
     */
    public static void setNavigationBarStatusBar(Activity activity, boolean hasFocus) {
        if (!hasFocus) return;
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    /**
     * 计算md5
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        if (TextUtils.isEmpty(str)) return "";
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取色值状态管理
     *
     * @param normal
     * @param pressed
     * @param focused
     * @param unable
     * @return
     */
    public static ColorStateList getColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[]{pressed, focused, normal, focused, unable, normal};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_checked, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        return new ColorStateList(states, colors);
    }

    /**
     * 获取目标日期的当日开始时间
     *
     * @param timeInMillis
     * @return
     */
    public static long getDayStartTime(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取目标日期的当日结束时间
     *
     * @param timeInMillis
     * @return
     */
    public static long getDayEndTime(long timeInMillis) {
        return getDayStartTime(timeInMillis) + TimeUnit.DAYS.toMillis(1);
    }

    /**
     * 显示软键盘
     *
     * @param context
     * @param view
     * @return
     */
    public static boolean showSoftInput(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return inputMethodManager.showSoftInput(
                view, InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    /**
     * 隐藏软键盘
     *
     * @param context
     * @param view
     * @return
     */
    public static boolean hideSoftInput(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return inputMethodManager.hideSoftInputFromWindow(
                view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }



    /**
     * 两个日期相减得到的天数
     *
     * @return
     */
    public static long getDiffDays(long beginDate, long endDate) {
        long diff = (endDate - beginDate) / (1000 * 60 * 60 * 24);
        return diff;
    }


}