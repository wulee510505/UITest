package com.example.wulee.uitest;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * create by  wulee   2018/5/18 13:20
 * desc:
 */
public class DensityUtil {
    private static final String TAG = DensityUtil.class.getSimpleName();

    // 当前屏幕的densityDpi
    private static float dmDensityDpi = 0.0f;
    private static DisplayMetrics dm;
    private static float scale = 0.0f;

    /**
     *
     * 根据构造函数获得当前手机的屏幕系数
     *
     * */
    public DensityUtil(Context context) {
        // 获取当前屏幕
        dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        // 设置DensityDpi
        setDmDensityDpi(dm.densityDpi);
        // 密度因子
        scale = getDmDensityDpi() / 160;
        Log.i(TAG, toString());
    }

    /**
     * 当前屏幕的density因子
     *
     * @retrun DmDensity Getter
     * */
    public static float getDmDensityDpi() {
        return dmDensityDpi;
    }

    /**
     * 当前屏幕的density因子
     *
     * @retrun DmDensity Setter
     * */
    public static void setDmDensityDpi(float dmDensityDpi) {
        DensityUtil.dmDensityDpi = dmDensityDpi;
    }


    @Override
    public String toString() {
        return " dmDensityDpi:" + dmDensityDpi;
    }


    /**
     * @Description:获取x、y的dpi,得到的数据，基本上是一致的
     */
    public static String getXydpi(Context context) {
        float xdpi = context.getResources().getDisplayMetrics().xdpi;
        float ydpi = context.getResources().getDisplayMetrics().ydpi;
        return  getDpi(xdpi);
    }


    /**
     * @Description:匹配得到手机的dpi
     */
    public static String getDpi(float xdpi) {
        String dpi="";
        if(xdpi>=0&&xdpi<120){
            dpi="ldpi";
        }else if(xdpi>=120&&xdpi<160){
            dpi="mdpi";
        }else if(xdpi>=160&&xdpi<240){
            dpi="hdpi";
        }else if(xdpi>=240&&xdpi<320){
            dpi="xhdpi";
        }else if(xdpi>=320&&xdpi<480){
            dpi="xxhdpi";
        }else if(xdpi>=480&&xdpi<640){
            dpi="xxxhdpi";
        }
        return dpi;
    }

}
