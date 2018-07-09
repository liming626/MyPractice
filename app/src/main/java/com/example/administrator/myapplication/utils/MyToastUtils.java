package com.example.administrator.myapplication.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/5/29.
 */

public class MyToastUtils {
    private static Toast toast;
    @SuppressLint("ShowToast")
    public  static  void showToast (Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context,str, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
        }
        toast.setText(str);
        toast.show();
    }

    @SuppressLint("ShowToast")
    public  static  void showLongToast (Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context,str, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
        }
        toast.setText(str);
        toast.show();
    }
}
