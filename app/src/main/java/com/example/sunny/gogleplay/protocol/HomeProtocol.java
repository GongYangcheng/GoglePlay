package com.example.sunny.gogleplay.protocol;

import com.example.sunny.gogleplay.bean.AppInfo;
import com.example.sunny.gogleplay.util.FileUtils;
import com.example.sunny.gogleplay.util.HttpHelper;
import com.example.sunny.gogleplay.util.IOUtils;
import com.example.sunny.gogleplay.util.ToastUtils;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================
 *
 * @Created by sunny
 * @project com.example.sunny.gogleplay.protocol
 * @file GoglePlay
 * @CreatedTime 2018/6/6 10:23
 * @Desc 浅谈宣泄 深水无波
 * ？断网时。
 */

public class HomeProtocol {
    //        1.请求服务器。2，缓存。3，复用缓存。4，解析
    public List<AppInfo> load(int index){
//        复用缓存
        String json = loadFromLocal(index);
//        联网请求数据
        if(json == null){
            json = loadFromServer(index);
            if(json != null){
//            缓存数据
                save2Local(index,json);
            }
        }
//        解析json
        if(json != null){
           return parserJson(json);
        }
        return null;
    }

    /**
     * 请求服务器
     * HTTPClient 安卓<6.0
     * HTTPURLConnection
     * Volley（包含HttpCliemt  ，HTTPURLConnection）<=22适合数据频繁，数据量小
     * Okhttp效率高>2.3
     * Retrofit返回Java对象
     * NoHttp用法简单
     * @param index
     * @return
     */
    private String loadFromServer(int index) {
        HttpHelper httpHelper = new HttpHelper(HttpHelper.BASEURL+"/home?index="+index);
        String json = httpHelper.getSycn();
        return json;
    }

    /**
     * 复用缓存
     * @param index
     * @return
     */
    private String loadFromLocal(int index) {
        File dir = FileUtils.getCache();
        File file = new File(dir,"home_"+index);
        FileReader fileReader = null;
        BufferedReader br = null;
        StringWriter sw = null;
        try {
            fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            //读取过期事件
            long outOfDate = Long.parseLong(br.readLine());
            if(outOfDate<System.currentTimeMillis()){
//                过期
                return null;
            }else{
                String line = null;
                sw = new StringWriter();
                while ((line = br.readLine()) != null){
                    sw.write(line);
                }
                return sw.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeSteam(br);
            IOUtils.closeSteam(fileReader);
            IOUtils.closeSteam(sw);
        }
        return null;
    };

    /**
     * 缓存
     * @param index
     * @param json
     */
    private void save2Local(int index , String json){
        File dir = FileUtils.getCache();
        File file = new File(dir,"home_"+index);
        FileWriter fw = null;
        //找原因，为啥不能用bw；TODO
//        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
//            bw = new BufferedWriter(fw);
//            bw.write(System.currentTimeMillis()+120*1000+"");
            fw.write(System.currentTimeMillis()+120*1000+"");//添加过期时间
            fw.write("\r\n");//换行  BufferedWriter
//            bw.newLine();//换行
            fw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeSteam(fw);
//            IOUtils.closeSteam(bw);
        }

    }

    /**
     * 解析json
     * @param json
     */
    private List<AppInfo> parserJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
//          使用getJSONObject时,如果返回的对象不是JSONObject,抛出JSONException异常、
//          使用optJSONObject时,当返回结果不是JSONObject时,这里不会抛异常,而是返回null
            JSONArray listArray = jsonObject.optJSONArray("list");
            JSONObject listObject = null;
            List<AppInfo> appInfos = new ArrayList<>();
            AppInfo appInfo = null;
            for (int i = 0; i < listArray.length(); i++) {
                listObject = listArray.optJSONObject(i);
                long id = listObject.optLong("id");
                String name = listObject.optString("name");
                String packageName = listObject.optString("packageName");
                String iconUrl = listObject.optString("iconUrl");
                double stars = listObject.optDouble("stars");
                long size = listObject.optLong("size");
                String downloadUrl = listObject.optString("downloadUrl");
                String des = listObject.optString("des");
                appInfo = new AppInfo(id, name, packageName, iconUrl, stars, size, downloadUrl, des);
                appInfos.add(appInfo);
            }
            return appInfos;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
