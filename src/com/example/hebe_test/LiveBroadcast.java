package com.example.hebe_test;

import java.util.ArrayList;
import java.util.List;

import com.example.constant.Configure;
import com.tencent.qcload.playersdk.ui.UiChangeInterface;
import com.tencent.qcload.playersdk.ui.VideoRootFrame;
import com.tencent.qcload.playersdk.util.PlayerListener;
import com.tencent.qcload.playersdk.util.VideoInfo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class LiveBroadcast extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live_broadcast);
		final VideoRootFrame player = (VideoRootFrame) findViewById(R.id.player);
		List<VideoInfo> videos = new ArrayList<VideoInfo>();
		if(Configure.flag==0){
			VideoInfo v1 = new VideoInfo();
			v1.description="标清";
			v1.type=VideoInfo.VideoType.MP4;
			v1.url="file:///storage/sdcard0/data/hh.mp4";
			//v1.url="";
			videos.add(v1);
		}else if(Configure.flag==1){
			VideoInfo v2 = new VideoInfo();
			v2.description="标清";
			v2.type=VideoInfo.VideoType.MP4;
			v2.url=MainActivity.mp4;
			//v1.url="";
			videos.add(v2);
			
		}
		/*
		VideoInfo v1 = new VideoInfo();
		v1.description="标清";
		v1.type=VideoInfo.VideoType.MP4;
		v1.url="file:///storage/sdcard0/Download/hh.mp4";
		videos.add(v1);
		*/
		// 此处有坑，adnroid的url的地址前面是三个杠
		// v1.url="http://192.168.5.38:8080/hebe";
		// v1.url="rtmp://live.hkstv.hk.lxdns.com/live/hks";
		
		/*
		VideoInfo v2 = new VideoInfo();
		v2.description = "高清";
		v2.type = VideoInfo.VideoType.MKV;
		v2.url = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
		Log.e("ffff", "LiveBroadcast-----------" + "  v2.url:" + v2.url);
		videos.add(v2);
		*/
		player.play(videos);
		// 全屏切换
		player.setListener(new MyPlayerListener());
		player.setToggleFullScreenHandler(new UiChangeInterface() {

			@Override
			public void OnChange() {
				if (player.isFullScreen()) {
					// 播放器全屏时，将页面设置为竖屏状态
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
				// 播放器非全屏时，将页面设置为横屏状态，此时播放器宽度自适应到屏幕宽度
				else {
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}

			}

		});
		// Log.e("adr", "fffff-----------check:"+player.getCurrentStatus());
		if (player.getCurrentStatus() == 6) {
			player.release();
			Log.e("adr", "fffff-----------check:" + player.getCurrentStatus());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.live_broadcast, menu);
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
}

class MyPlayerListener implements PlayerListener {

	@Override
	public void onError(Exception arg0) {
		// TODO Auto-generated method stub
		Log.e("Exception", "listener Exception:" + arg0);

	}

	@Override
	public void onStateChanged(int arg0) {
		// TODO Auto-generated method stub
		Log.e("status", "listener status......." + arg0);

	}
}
