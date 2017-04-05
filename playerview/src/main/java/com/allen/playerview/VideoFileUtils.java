package com.allen.playerview;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Allen on 2017/3/30.
 * <p>
 * 处理视频文件工具类
 */

public class VideoFileUtils {


    /**
     * 获取SDCard卡或者手机内存的根路径（优先获取SDCard卡的根路径）
     *
     * @param context Context
     * @return SDCard卡或者手机内存的根路径
     */
    public static String getRootDir(Context context) {
        String rootDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            rootDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            rootDir = context.getCacheDir().getAbsolutePath();
        }
        return rootDir;
    }


    /**
     * 检查本地是否存在某个文件
     *
     * @param filePath
     * @return
     */
    public static boolean checkFileExists(String filePath) {
        File file = new File(filePath);
        Log.i("playerView", "checkFileExists      file = " + file.getAbsolutePath());
        return file.exists() && file.isFile();
    }


    /**
     * 获取raw视频文件拷贝到sd卡后的路径   用于启动页播放本视频
     *
     * @param context        上下文对象
     * @param rawResId       R.raw.yourVideo
     * @param fileSuffix     例如  .mp4
     * @return 返回filePath
     */
    public static String getCopyRawResToSdcardPath(Context context, int rawResId, String fileSuffix) {
        String filePath = null;

        filePath = getRootDir(context) + "/" + rawResId + fileSuffix;

        if (checkFileExists(filePath)) {
            Log.i("playerView", "fileExists");
            return filePath;
        }
        File file = new File(filePath);
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            int len;
            byte[] buffer = new byte[1024];
            inputStream = context.getResources().openRawResource(rawResId);
            outputStream = new FileOutputStream(file);
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();

            return filePath;

        } catch (IOException e) {
            Log.i("playerView", "getCopyRawResToSdcardPath: " + e.toString());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                Log.i("playerView", "getCopyRawResToSdcardPath: " + e.getMessage());
            }
        }

        return filePath;

    }
}
