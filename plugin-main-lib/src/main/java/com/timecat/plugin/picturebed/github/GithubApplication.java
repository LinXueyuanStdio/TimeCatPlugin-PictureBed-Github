package com.timecat.plugin.picturebed.github;

import android.app.Application;
import android.util.Log;

import com.timecat.plugin.picturebed.PictureBed;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/12
 * @description null
 * @usage null
 */
public class GithubApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Github", "load PictureBed begin");
        Log.e("Github", PictureBed.all.keySet().toString());
        PictureBed.all.put("Github", new UploadWork(this));
        Log.e("Github", PictureBed.all.keySet().toString());
        Log.e("Github", "load PictureBed end");
    }
}
