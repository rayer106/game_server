package com.ry.base;

import java.text.SimpleDateFormat;

public class LogUtil {
    public static void i(String msg) {
        String threadName = Thread.currentThread().getName();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String time = df.format(System.currentTimeMillis());
        System.out.println(time + " [" + threadName + "] " + msg);
    }

    public static void w(String msg) {

    }

    public static void e(String msg) {

    }
}
