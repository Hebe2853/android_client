package com.example.hebe_test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import com.example.constant.Configure;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {
	public static final String mp4 = File.separator + "storage"
			+ File.separator + "sdcard0" + File.separator + "data"
			+ File.separator + "xx.mp4";
	public static final String[] choices = new String[] { mp4, "file2",
			"file3", "file4" };

	private void showDialog(String title, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton("ok", null);
		builder.create().show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		findViews();

		// RelativeLayout clockRelativeLayout =
		// (RelativeLayout)getLayoutInflater().inflate(R.layout.edittext,null);
		// AnalogClock clock1 = new AnalogClock(this);
		// mainRelativeLayout.addView(clock1,new
		// ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
		// ViewGroup.LayoutParams.FILL_PARENT));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private String getVersionName() throws Exception {
		PackageManager packageManager = this.getApplication()
				.getPackageManager();
		PackageInfo packInfo = packageManager.getPackageInfo(this
				.getApplication().getPackageName(), 0);
		return packInfo.versionName;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button3: {
			try {
				showDialog("当前版本", getVersionName());
				break;
			} catch (Exception e) {
				e.toString();
			}
		}

		case R.id.button1: {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			showDialog("当前日期", sdf.format(new Date()));
			break;

		}
		case R.id.button2: {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			showDialog("当前时间", sdf.format(new Date()));
			break;
		}
		case R.id.button5: {

			Intent intent = new Intent(MainActivity.this, ShowTime.class);
			// intent.putExtra("store", "from activityMain");
			startActivity(intent);
			break;
		}
		case R.id.button_live: {
			Configure.flag=0;
			Intent intent = new Intent(MainActivity.this, LiveBroadcast.class);
			startActivity(intent);
			break;
		}
		case R.id.button_play: {
			showSingleChoiceDialog("请选择一个播放文件");
			break;
		}
		}

	}

	private void findViews() {
		RelativeLayout mainRelativeLayout = (RelativeLayout) getLayoutInflater()
				.inflate(R.layout.activity_main, null);
		setContentView(R.layout.activity_main);
		Button butDate = (Button) findViewById(R.id.button1);
		Button butTime = (Button) findViewById(R.id.button2);
		Button showTime = (Button) findViewById(R.id.button5);
		Button showVersion = (Button) findViewById(R.id.button3);
		Button showLive = (Button) findViewById(R.id.button_live);
		Button filePlay = (Button) findViewById(R.id.button_play);
		butDate.setOnClickListener(this);
		butTime.setOnClickListener(this);
		showTime.setOnClickListener(this);
		showVersion.setOnClickListener(this);
		showLive.setOnClickListener(this);
		filePlay.setOnClickListener(this);
	}

	private void showSingleChoiceDialog(String title) {
		// AlertDialog.Builder builder = new AlertDialog.Builder(this);
		new AlertDialog.Builder(this).setTitle(title)
				.setSingleChoiceItems(choices, 1, new SingleOnClick())
				.setPositiveButton("确定", new SingleOnClick())
				.setNegativeButton("取消", new SingleOnClick()).show();

	}

	// 此处把这个监听定义为内部类，定义在外部的话，引用MainActivity.this会出现
	// No enclosing instance of the type MainActivity is accessible in scope
	private class SingleOnClick implements DialogInterface.OnClickListener {

		private int index;

		public void SingleOnClick() {
			this.index = index;
		}

		public void onClick(DialogInterface dialog, int whichButton) {
			// TODO Auto-generated method stub
			if (whichButton >= 0) {
				index = whichButton;
				// dialog.cancel();点击列表项后弹框关闭
			} else {
				if (whichButton == DialogInterface.BUTTON_POSITIVE) {
					new AlertDialog.Builder(MainActivity.this)
							.setMessage(
									"您已经选择了："
											+ ":"
											+ com.example.hebe_test.MainActivity.choices[index])
							.show().cancel();
					if (com.example.hebe_test.MainActivity.choices[index] == MainActivity.mp4) {
						Configure.flag = 1;
						Intent intent = new Intent(MainActivity.this,
								LiveBroadcast.class);
						startActivity(intent);
					}

				} else if (whichButton == DialogInterface.BUTTON_NEGATIVE) {
					dialog.cancel();
				}
			}

		}
	}
}
