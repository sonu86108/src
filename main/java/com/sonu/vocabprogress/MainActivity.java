package com.sonu.vocabprogress;

import android.app.*;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.*;
import androidx.appcompat.app.*;
import androidx.cardview.widget.CardView;

import android.content.*;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sonu.vocabprogress.service.*;


public class MainActivity extends AppCompatActivity 
{
    CardView cardViewSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardViewSettings=findViewById(R.id.id_cardView_settings);
		
		//Removing divider between actionbar and layout
		getSupportActionBar().setElevation(0);
		//Setting ActionBar color as Status bar
		 if(Build.VERSION.SDK_INT >=21){
		     ColorDrawable colors=new ColorDrawable(getWindow().getStatusBarColor());
		     getSupportActionBar().setBackgroundDrawable(colors);
         }
		 
		 cardViewSettings.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(MainActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
             }
         });

		
		//starting clipboard lisntener service
		//startService(new Intent(MainActivity.this,ClipBoardListenerService.class));
//        //changing status bar color
//        if(Build.VERSION.SDK_INT >=21){
//            Window window=this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.color_actionBarBackround));




        }
	
	
}
