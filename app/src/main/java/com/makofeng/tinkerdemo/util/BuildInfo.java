package com.makofeng.tinkerdemo.util;

import com.makofeng.tinkerdemo.BuildConfig;

/**
 * Created by 冯浩 on 2017/2/15.
 */

public class BuildInfo {
	public static boolean DEBUG        = BuildConfig.DEBUG;
	public static String  VERSION_NAME = BuildConfig.VERSION_NAME;
	public static int     VERSION_CODE = BuildConfig.VERSION_CODE;

	public static String MESSAGE       = BuildConfig.MESSAGE;
	public static String TINKER_ID     = BuildConfig.TINKER_ID;
	public static String PLATFORM      = BuildConfig.PLATFORM;
}
