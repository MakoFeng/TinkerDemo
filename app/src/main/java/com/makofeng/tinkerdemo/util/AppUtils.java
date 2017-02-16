package com.makofeng.tinkerdemo.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by 冯浩 on 2017/2/16.
 */

public class AppUtils {

	/**
	 * 获取渠道名字 从AndroidManifest中的<meta-data android:name="UMENG_CHANNEL"/>
	 *
	 * @return android:value 渠道名字
	 */
	public static String getChannelName(Context context) {
		ApplicationInfo appInfo;
		String channelName = "no channelname";
		try {
			appInfo = context.getPackageManager().getApplicationInfo(
					context.getPackageName(), PackageManager.GET_META_DATA);
			channelName = appInfo.metaData.getString("UMENG_CHANNEL");
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return channelName;
	}

}
