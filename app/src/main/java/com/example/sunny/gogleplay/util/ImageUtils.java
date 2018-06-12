package com.example.sunny.gogleplay.util;

import android.content.Context;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * ============================
 *
 * @Created by sunny
 * @project com.example.sunny.gogleplay.util
 * @file GoglePlay
 * @CreatedTime 2018/6/12 18:04
 * @Desc 浅谈宣泄 深水无波
 */

public class ImageUtils {
    /**
     * 修改picaso 缓存路径
     * @param context
     */
    public static void initImage(Context context){
        // 修改picasso 默认缓存目录
        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttpDownloader(FileUtils.getIcon()))
                .debugging(true)//意思是正在开发；
                .build();
        Picasso.setSingletonInstance(picasso);
    }
}
