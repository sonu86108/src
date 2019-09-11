package com.sonu.vocabprogress;

import android.os.*;
import androidx.appcompat.app.*;
import androidx.cardview.widget.CardView;
import android.content.*;
import android.view.View;
import android.widget.Toast;
import com.sonu.vocabprogress.service.*;
import android.widget.*;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
	ActionBar actionBar;
	CardView cardViewSettings;
	Intent serviceIntent;

	//OnCreate Activity
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		//Initialization 
		cardViewSettings=findViewById(R.id.id_cardView_settings);
		//Intent for service
		serviceIntent=new Intent(MainActivity.this,ClipBoardListenerService.class);

		//ActionBar
		this.actionBar();
		//Setting onclick linsteners
		cardViewSettings.setOnClickListener(this);
		
		

	}
	//Onclick
	@Override
	public void onClick(View p1)
	{
		switch(p1.getId()){
			case R.id.id_cardView_settings:
				//Todo
				break;
				
		}
	}

	//Action bar related operations
	private void actionBar(){
		//Action bar
		if(getSupportActionBar() !=null){
			actionBar = getSupportActionBar();
			//Removing divider between actionbar and layout
			actionBar.setElevation(0);
			//Setting ActionBar color as Status bar
			actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimary));
			/*
			if (Build.VERSION.SDK_INT >= 21)
			{
				ColorDrawable colors=new ColorDrawable(getWindow().getStatusBarColor());
				//finding status bar color
				Integer color=this.getWindow().getStatusBarColor();
				Log.v("color","statusbar color: "+Integer.toHexString(color));
				actionBar.setBackgroundDrawable(colors);
			}
			*/
			//Custom view action bar main switch
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
			actionBar.setCustomView(R.layout.app_bar_custom_view_switch);
			Switch mainSwitch=actionBar.getCustomView().findViewById(R.id.app_bar_custom_view_switchSwitch);
			mainSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton p1, boolean p2)
				{
					// TODO: Implement this method
					if(p1.isChecked()){
						startService(serviceIntent);
						Toast.makeText(MainActivity.this,"ClipBoardListenerServuce started",Toast.LENGTH_LONG)
								.show();
					}else{
						stopService(serviceIntent);
						Toast.makeText(MainActivity.this,"ClipBoardListenerService stopped",Toast.LENGTH_LONG).
								show();

					}
				}


			});
		}
	}






}
