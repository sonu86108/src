package com.sonu.vocabprogress.activities;

import android.os.*;
import androidx.appcompat.app.*;
import androidx.cardview.widget.CardView;
import android.content.*;
import android.view.View;
import android.widget.Toast;
import com.sonu.vocabprogress.services.*;
import android.widget.*;
import com.sonu.vocabprogress.R;
import com.sonu.vocabprogress.utilities.helpers.*;
import com.sonu.vocabprogress.models.*;
import android.app.Dialog;
import android.preference.*;
import android.database.*;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
	ActionBar actionBar;
	CardView cardViewSettings,cardViewWordList,cardViewQuizes;
	Intent serviceIntent;
	SQLiteHelper db;
	Word word;
	Dialog dialog;
	EditText name,meaning,desc;

	//OnCreate Activity
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		//Initialization 
		cardViewSettings=findViewById(R.id.id_cardView_settings);
		cardViewWordList=findViewById(R.id.id_cardView_WordList);
		cardViewQuizes=findViewById(R.id.id_cardView_Quizes);
		
		initDialog();
		db=new SQLiteHelper(this);
		//Intent for service
		serviceIntent=new Intent(MainActivity.this,ClipBoardListenerService.class);
 
		//ActionBar
		this.actionBar();
		//Setting onclick linsteners
		cardViewSettings.setOnClickListener(this);
		cardViewWordList.setOnClickListener(this);
		cardViewQuizes.setOnClickListener(this);
	
		Button submit=dialog.findViewById(R.id.id_btn_submit);
		submit.setOnClickListener(this);
		
		

	}
	//Onclick
	@Override
	public void onClick(View p1)
	{
		switch(p1.getId()){
			case R.id.id_cardView_settings:
				//Todo
				dialog.show();
				break;
			case R.id.id_btn_submit:
			     onClickSubmitBtn();
				 break;
				 
			case R.id.id_cardView_WordList:
				 Intent intent=new Intent(MainActivity.this,WordListActivity.class);
				 startActivity(intent);
				 break;
			case R.id.id_cardView_Quizes:
				readWord();
				break;
				
		}
	}
	
	//Dialog initiliacization
	public void initDialog(){
		dialog =new Dialog(this);
		dialog.setContentView(R.layout.tmp_word_entry);
		dialog.setCancelable(true);
		name=dialog.findViewById(R.id.id_enter_word);
		meaning=dialog.findViewById(R.id.id_enter_word_meaning);
		desc=dialog.findViewById(R.id.id_enter_word_desc);
	}
	
	//On sumbit button click of dialog
	public void onClickSubmitBtn(){
		word=new Word(name.getText().toString(),
					  meaning.getText().toString(),
					  desc.getText().toString());
		if(db.insertData(word)){
			Toast.makeText(this,"New Word Added Successfully",
			Toast.LENGTH_LONG).show();
			dialog.dismiss();
		}
else
{
			Toast.makeText(this,"Error while adding",
						   Toast.LENGTH_LONG).show();
						   dialog.dismiss();
		}
	
	}
	
	//Read word data from sqlite database
	public void readWord(){
		Cursor curso =db.retrieveData();
		if(curso.moveToFirst()){
		   do{
			   StringBuilder s=new StringBuilder();
			   s.append(curso.getString(1));
			   s.append("\n"+curso.getString(2));
			   s.append("\n"+curso.getString(3));
			  showToast(s.toString());
			
		   }while(curso.moveToNext());
		}
	}
	
	//show toast
	public void showToast(String msg){
		Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
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
