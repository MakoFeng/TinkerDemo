package com.makofeng.tinkerdemo;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.makofeng.tinkerdemo.Log.MyLogImp;
import com.makofeng.tinkerdemo.util.SampleApplicationContext;
import com.makofeng.tinkerdemo.util.TinkerManager;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by 冯浩 on 2017/2/15.
 */
@DefaultLifeCycle(application = "com.makofeng.tinkerdemo.MyApplication",
		flags = ShareConstants.TINKER_ENABLE_ALL,
		loadVerifyFlag = false)
public class SampleApplicarion extends DefaultApplicationLike {
	public SampleApplicarion(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
								 long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
		super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
	}
	@Override
	public void onBaseContextAttached(Context base) {
		super.onBaseContextAttached(base);
		SampleApplicationContext.application = getApplication();
		SampleApplicationContext.context = getApplication();
		TinkerManager.setTinkerApplicationLike(this);

		TinkerManager.initFastCrashProtect();
		//should set before tinker is installed
		TinkerManager.setUpgradeRetryEnable(true);

		//optional set logIml, or you can use default debug log
		TinkerInstaller.setLogIml(new MyLogImp());

		//installTinker after load multiDex
		//or you can put com.tencent.tinker.** to main dex
		TinkerManager.installTinker(this);
		Tinker tinker = Tinker.with(getApplication());
	}
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
		getApplication().registerActivityLifecycleCallbacks(callback);
	}
}
