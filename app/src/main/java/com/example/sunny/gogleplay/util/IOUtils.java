package com.example.sunny.gogleplay.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * ============================
 *
 * @Created by sunny
 * @project com.example.sunny.gogleplay.util
 * @file GoglePlay
 * @CreatedTime 2018/6/8 11:30
 * @Desc 浅谈宣泄 深水无波
 */

public class IOUtils {

    public static void closeSteam(Closeable closeable){
        if (closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
