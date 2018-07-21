package com.example.wulee.uitest;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Method;
import java.math.BigDecimal;

public class UIUtils {

	public static Context getContext() {
		return App.getInstance().getContext();
	}

	// 获取字符串
	public static String getString(int id) {
		return getContext().getResources().getString(id);
	}

	// 获取图片
	public static Drawable getDrawable(int id) {
		return getContext().getResources().getDrawable(id);
	}

	// 获取颜色
	public static int getColor(int id) {
		return getContext().getResources().getColor(id);
	}

	// 获取颜色的状态选择器
	public static ColorStateList getColorStateList(int id) {
		return getContext().getResources().getColorStateList(id);
	}

	// 获取尺寸
	public static int getDimen(int id) {
		return getContext().getResources().getDimensionPixelSize(id);// 返回像素
	}

	public static int dip2px(float dp) {
		return (int) (getContext().getResources().getDisplayMetrics().density
				* dp + 0.5f);
	}

	public static float px2dip(int px) {
		return px / getContext().getResources().getDisplayMetrics().density;
	}

	public static int px2sp(float pxValue) {
		final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int sp2px(float spValue) {
		final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static int getScreenWidth(){
		WindowManager wm = (WindowManager) getContext()
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}


	/**
	 * 获得状态栏的高度
	 * @return
	 */
	public static int getStatusHeight() {
		int statusHeight = -1;
		try {
			Class clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = getContext().getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

	//获取屏幕原始尺寸高度，包括虚拟功能键高度
	public static int getDpi(){
		int dpi = 0;
		WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		@SuppressWarnings("rawtypes")
		Class c;
		try {
			c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
			Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
			method.invoke(display, displayMetrics);
			dpi=displayMetrics.heightPixels;
		}catch(Exception e){
			e.printStackTrace();
		}
		return dpi;
	}

	/**
	 * 获得屏幕高度
	 * @return
	 */
	public static int getScreenHeight() {
		WindowManager wm = (WindowManager) getContext()
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	/**
	 * 标题栏高度
	 * @return
	 */
	public static int getTitleHeight(Activity activity){
		return  activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
	}


	private static double mInch = 0;
	/**
	 * 获取屏幕尺寸
	 * @param context
	 * @return
	 */
	public static double getScreenInch(Activity context) {
		if (mInch != 0.0d) {
			return mInch;
		}
		try {
			int realWidth = 0, realHeight = 0;
			Display display = context.getWindowManager().getDefaultDisplay();
			DisplayMetrics metrics = new DisplayMetrics();
			display.getMetrics(metrics);
			if (android.os.Build.VERSION.SDK_INT >= 17) {
				Point size = new Point();
				display.getRealSize(size);
				realWidth = size.x;
				realHeight = size.y;
			} else if (android.os.Build.VERSION.SDK_INT < 17
					&& android.os.Build.VERSION.SDK_INT >= 14) {
				Method mGetRawH = Display.class.getMethod("getRawHeight");
				Method mGetRawW = Display.class.getMethod("getRawWidth");
				realWidth = (Integer) mGetRawW.invoke(display);
				realHeight = (Integer) mGetRawH.invoke(display);
			} else {
				realWidth = metrics.widthPixels;
				realHeight = metrics.heightPixels;
			}

			mInch =formatDouble(Math.sqrt((realWidth/metrics.xdpi) * (realWidth /metrics.xdpi) + (realHeight/metrics.ydpi) * (realHeight / metrics.ydpi)),1);


		} catch (Exception e) {
			e.printStackTrace();
		}

		return mInch;
	}
	/**
	 * Double类型保留指定位数的小数，返回double类型（四舍五入）
	 * newScale 为指定的位数
	 */
	private static double formatDouble(double d,int newScale) {
		BigDecimal bd = new BigDecimal(d);
		return bd.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
