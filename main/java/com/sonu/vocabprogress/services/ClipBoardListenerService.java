package com.sonu.vocabprogress.services;
import android.app.*;
import android.os.*;
import android.content.*;
import android.widget.*;
import androidx.core.app.*;
import com.sonu.vocabprogress.R;
import com.sonu.vocabprogress.activities.*;

public class ClipBoardListenerService extends Service
{
	ClipboardManager clipBoardManager;

	@Override
	public IBinder onBind(Intent p1)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		// TODO: Implement this method
		Toast.makeText(getApplicationContext(),"ClipBoardListnerService started",Toast.LENGTH_LONG).show();
		clipBoardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		clipBoardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {

				@Override
				public void onPrimaryClipChanged()
				{
			        makeNotification(clipBoardManager.getText().toString());
				}



			});
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	public void makeNotification(String msg){
		int nid=0;
		//notification builder to build notification
		NotificationCompat.Builder nBuilder=new NotificationCompat.Builder(getApplicationContext());
		nBuilder.setSmallIcon(R.drawable.ic_launcher_background);
		nBuilder.setContentTitle(msg);
		
		//Intent to open notification dialog activity to get word input
		Intent intent=new Intent(ClipBoardListenerService.this,NotificationDialogActivity.class);
		PendingIntent pi=
		PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		nBuilder.addAction(android.R.drawable.ic_menu_view, "VIEW", pi);
		//notification manager to show notification on device
		NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		
		//oreo and above
		if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){

			String channelId="your channel id";
			NotificationChannel nc=new NotificationChannel(channelId," Human redable tag",NotificationManager.IMPORTANCE_DEFAULT);
		    nm.createNotificationChannel(nc);
			nBuilder.setChannelId(channelId);
		}
		nm.notify(nid,nBuilder.build());
		
	}

	@Override
	public void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
		Toast.makeText(this,"Service stoped",Toast.LENGTH_LONG).show();
	}
	
	
	
	
	
	
}
