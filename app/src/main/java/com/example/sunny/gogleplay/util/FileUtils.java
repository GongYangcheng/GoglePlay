package com.example.sunny.gogleplay.util;

import android.os.Environment;

import java.io.File;

/**
 * ============================
 *
 * @Created by sunny
 * @project com.example.sunny.gogleplay.util
 * @file GoglePlay
 * @CreatedTime 2018/6/8 11:34
 * @Desc 浅谈宣泄 深水无波
 *
 * 统一管理所有项目的缓存目录
 * External: 外面的，外部的; 表面上的; 外用的; 外国的;
            n.	外部，外面; 外观; 外部情况
    Storage:n.	贮存; 贮藏; 储藏处，仓库; 贮存器，蓄电（瓶）;
 */

public class FileUtils {

    private static String PackageName = "GoogelPlay";
    private static String CACHE = "CACHE";
    private static String ICON = "ICON";

    public static File getDir(String fileName){
        StringBuilder sb = new StringBuilder();
        if(isSDCardAvailable()){
            sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());// /mnt/Sdcard
            sb.append(File.separator);
            sb.append(PackageName);
//            sb.append(File.separator);
//            sb.append(fileName);
        }else{
            sb.append(UiUtils.getContext().getCacheDir().getAbsolutePath());
        }
        sb.append(File.separator);
        sb.append(fileName);
        File file = new File(sb.toString());
        if (!file.exists()&&!file.isDirectory()){
            file.mkdirs();
        }
        return file;
    }
    /**
     * 判断sd卡是否可用
     * @return
     */
    private static boolean isSDCardAvailable() {

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);//sd卡可用
    }
    /**
     * 获取数据缓存目录
     * @return
     */
    public static File getCache(){
        return getDir(CACHE);
    }
    /**
     * 维护图片缓存的目录
     * @return
     */
    public static File getIcon(){
        return getDir(ICON);
    }

}
