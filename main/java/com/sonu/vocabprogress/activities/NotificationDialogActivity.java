package com.sonu.vocabprogress.activities;

import android.app.*;
import android.os.*;
import android.graphics.drawable.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;
import android.widget.GridLayout.*;
import com.sonu.vocabprogress.R;

public class NotificationDialogActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setFinishOnTouchOutside(true);
		setContentView(R.layout.activity_notification_dialog);
		//window settings
		Window window=getWindow();
		window.setBackgroundDrawable(new ColorDrawable(
		Color.TRANSPARENT));
		window.setDimAmount(0.0f);
		window.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		window.setGravity(Gravity.TOP);
    }
}
