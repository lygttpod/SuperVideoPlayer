package com.allen.supervideoplayer;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;

/**
 * Created by Allen on 2017/4/5.
 *
 */

public class App extends Application {

    private static Context context;

    /**
     * 视频缓存类
     */
    private HttpProxyCacheServer proxy;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }


    public static Context getContext() {
        return context;
    }


    /**
     * 视频缓存
     *
     * @return
     */
    public static HttpProxyCacheServer getProxy() {
        App app = (App) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }


}
