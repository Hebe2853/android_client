package com.example.hebe_test;

import java.util.ArrayList;
import java.util.List;

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
		final VideoRootFrame player=(VideoRootFrame)findViewById(R.id.player);
		List<VideoInfo> videos = new ArrayList<VideoInfo>();
		VideoInfo v1 = new VideoInfo();
		v1.description="����";
		v1.type=VideoInfo.VideoType.MP4;
		v1.url="file:///storage/sdcard0/Download/hh.mp4";
		//�˴��пӣ�adnroid��url�ĵ�ַǰ����������
		//v1.url="rtmp://live.hkstv.hk.lxdns.com/live/hks";
		//v1.url="rtmp://live.hkstv.hk.lxdns.com/live/hks";
		
		videos.add(v1);
		VideoInfo v2 = new VideoInfo();
		v2.description="����";
		v2.type=VideoInfo.VideoType.MP4;
		v2.url="";
		//videos.add(v2);
		player.play(videos);
		//ȫ���л�
		player.setListener( new MyPlayerListener());
		player.setToggleFullScreenHandler(new UiChangeInterface(){

			@Override
			public void OnChange() {
				if(player.isFullScreen())
				{
					//������ȫ��ʱ����ҳ������Ϊ����״̬
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
				//��������ȫ��ʱ����ҳ������Ϊ����״̬����ʱ�������������Ӧ����Ļ���
				else
				{
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}
				
			}
			
		});
		//Log.e("adr", "fffff-----------check:"+player.getCurrentStatus());
		if(player.getCurrentStatus()==6)
		{
			player.release();
			Log.e("adr", "fffff-----------check:"+player.getCurrentStatus());
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
class MyPlayerListener implements PlayerListener
{

	@Override
	public void onError(Exception arg0) {
		// TODO Auto-generated method stub
		Log.e("Exception", "listener Exception:"+arg0);
		
	}

	@Override
	public void onStateChanged(int arg0) {
		// TODO Auto-generated method stub
		Log.e("status", "listener status......."+arg0);
				
	}
}
