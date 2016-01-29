package com.example.hebe_test;

import java.util.ArrayList;
import java.util.List;

import com.tencent.qcload.playersdk.ui.UiChangeInterface;
import com.tencent.qcload.playersdk.ui.VideoRootFrame;
import com.tencent.qcload.playersdk.util.VideoInfo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LiveBroadcast extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live_broadcast);
		final VideoRootFrame player=(VideoRootFrame)findViewById(R.id.player);
		List<VideoInfo> videos = new ArrayList<VideoInfo>();
		VideoInfo v1 = new VideoInfo();
		v1.description="标清";
		v1.type=VideoInfo.VideoType.MP4;
		v1.url="file://storage/sdcard0/data/hh.mp4";
		
		videos.add(v1);
		VideoInfo v2 = new VideoInfo();
		v2.description="高清";
		v2.type=VideoInfo.VideoType.MP4;
		v2.url="";
		videos.add(v2);
		player.play(videos);
		//全屏切换
		player.setToggleFullScreenHandler(new UiChangeInterface(){

			@Override
			public void OnChange() {
				if(player.isFullScreen())
				{
					//播放器全屏时，将页面设置为竖屏状态
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
				//播放器非全屏时，将页面设置为横屏状态，此时播放器宽度自适应到屏幕宽度
				else
				{
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}
				
			}
			
		});
		player.release();
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
