package com.makofeng.tinkerdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.makofeng.tinkerdemo.util.AppUtils;
import com.makofeng.tinkerdemo.util.Utils;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView tv= (TextView) findViewById(R.id.tv);

		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory
						().getAbsolutePath() + "/patch_signed_7zip.apk");
				Tinker tinker = Tinker.with(getApplicationContext());
				boolean isLoadSuccess = tinker.isTinkerLoaded();
				if (isLoadSuccess) {
					Log.i(TAG, "success");
				} else {
					Log.i(TAG, "error");
				}
				tv.setText(AppUtils.getChannelName(MainActivity.this));

			}
		});


		findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Tinker.with(getApplicationContext()).cleanPatch();
			}
		});

		findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
				android.os.Process.killProcess(android.os.Process.myPid());			}
		});



	}

	@Override
	protected void onResume() {
		Log.e(TAG, "i am on onResume");
//        Log.e(TAG, "i am on patch onResume");
		super.onResume();
		Utils.setBackground(false);

	}

	@Override
	protected void onPause() {
		super.onPause();
		Utils.setBackground(true);
	}

}
