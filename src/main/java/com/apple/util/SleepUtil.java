package com.apple.util;

/**
 * @Author Double_apple
 * @Date 2022/1/29 16:46
 * @Version 1.0
 */
public class SleepUtil {

    public static void sleep(int sec)  {
        try {
            Thread.sleep(1000*sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
