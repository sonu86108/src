package com.sonu.vocabprogress.service;
import android.app.*;
import android.os.*;
import android.content.*;
import android.widget.*;

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
	public void onStart(Intent intent, int startId)
	{
		// TODO: Implement this method
		Toast.makeText(getApplicationContext(),"ClipBoardListnerService started",Toast.LENGTH_LONG).show();
		clipBoardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		clipBoardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {

				@Override
				public void onPrimaryClipChanged()
				{
					// TODO: Implement this method
					Toast.makeText(getApplicationContext(),clipBoardManager.getText().toString(),Toast.LENGTH_LONG).show();
				}



			});
	}
	
	
	
}
