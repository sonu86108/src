package com.sonu.vocabprogress.ui.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import com.sonu.vocabprogress.utilities.helpers.SQLiteHelper;
import com.sonu.vocabprogress.models.Word;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.sonu.vocabprogress.R;
import android.widget.CompoundButton;
import com.sonu.vocabprogress.utilities.sharedprefs.Prefs;
import android.widget.Toast;
import com.sonu.vocabprogress.services.ClipBoardListenerService;
import android.database.Cursor;
import com.sonu.vocabprogress.ui.activities.WordListActivity;
import com.sonu.vocabprogress.utilities.tmp.AndroidDatabaseManager;
import com.google.android.material.snackbar.Snackbar;




public class MainActivity extends AppCompatActivity implements View.OnClickListener,Switch.OnCheckedChangeListener
{

	
	
	ActionBar actionBar;
	CardView cardViewSettings,cardViewWordList,cardViewQuizes,
	cardViewHelp,cardViewPlayQuiz,cardViewProgress;
	Intent serviceIntent;
	SQLiteHelper db;
	Word word;
	Switch mainSwitch;
	SharedPreferences sharedPref;
	SharedPreferences.Editor sharedPrefEditor;

	//OnCreate Activity
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		//Initialization 
		init();
        //ActionBar
		actionBar();
		//Setting onclick linsteners
		setOnclickListners();
		

	}
	//Onclick
	@Override
	public void onClick(View p1)
	{
		switch(p1.getId()){
			case R.id.id_cardView_settings:
				showInSnackbar("Sorry, Not Available");
				break;
				 
			case R.id.id_cardView_WordList:
				 Intent wordListIntent=new Intent(MainActivity.this,WordListActivity.class);
				 startActivity(wordListIntent);
				 break;
			case R.id.id_cardView_Quizes:
				Intent quizListIntent=new Intent(MainActivity.this,
												 QuizesActivity.class);
				startActivity(quizListIntent);
				break;
			case R.id.id_cardView_help:
				startActivity(new Intent(this,AndroidDatabaseManager.class));
				break;
			case R.id.id_cardView_playQuiz:
				Intent playQuizIntent=new Intent(MainActivity.this,
												 QuizesActivity.class);
				playQuizIntent.putExtra("play_mode",true);
				startActivity(playQuizIntent);
				break;
			case R.id.id_cardView_progress:
				showInSnackbar("Sorry, Not Available");
				break;
				
		}
	}

	
	
	
	
	
	//on checked change listener main Switch
	@Override
	public void onCheckedChanged(CompoundButton p1, boolean p2)
	{
		if(p1.isChecked()){
			startService(serviceIntent);
			sharedPrefEditor.putBoolean(Prefs.AppSettings.MAIN_SWITCH_STATUS.toString(),true).apply();
		}else{
			stopService(serviceIntent);
			sharedPrefEditor.putBoolean(Prefs.AppSettings.MAIN_SWITCH_STATUS.toString(),false).apply();
			Toast.makeText(MainActivity.this,"ClipBoardListenerService stopped",Toast.LENGTH_LONG).
				show();
		}
	}
	
	//initialiazation
	private void init(){
		//getting shared pref ref
		sharedPref=this.getSharedPreferences(Prefs.SharedPrefs.APP_SETTINGS.toString(),MODE_PRIVATE);
		sharedPrefEditor=sharedPref.edit();
		cardViewSettings=findViewById(R.id.id_cardView_settings);
		cardViewWordList=findViewById(R.id.id_cardView_WordList);
		cardViewQuizes=findViewById(R.id.id_cardView_Quizes);
		cardViewHelp=findViewById(R.id.id_cardView_help);
		cardViewPlayQuiz=findViewById(R.id.id_cardView_playQuiz);
		cardViewProgress=findViewById(R.id.id_cardView_progress);
		db=SQLiteHelper.getSQLiteHelper(this);
		//Intent for service
		serviceIntent=new Intent(MainActivity.this,ClipBoardListenerService.class);
	}
	
	//setting on click listeners
	private void setOnclickListners(){
		cardViewSettings.setOnClickListener(this);
		cardViewWordList.setOnClickListener(this);
		cardViewQuizes.setOnClickListener(this);
		cardViewHelp.setOnClickListener(this);
		cardViewPlayQuiz.setOnClickListener(this);
		cardViewProgress.setOnClickListener(this);
		mainSwitch.setOnCheckedChangeListener(this);
	}
	
	
	
	//Read word data from sqlite database
	public void readWordData(){
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
	
	//show snack bar
	public void showInSnackbar(String msg){
		Snackbar.make(findViewById(R.id.id_layout_mainLayout),msg,Snackbar.
		LENGTH_SHORT).show();
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
		    mainSwitch=actionBar.getCustomView().findViewById(R.id.app_bar_custom_view_switchSwitch);
			//loading user settings mainSwitch
			loadUserPrefs();
			
		}
		
		
	}
	
	//load user preferences
	private void loadUserPrefs(){
       if(sharedPref.getBoolean(Prefs.AppSettings.MAIN_SWITCH_STATUS.toString(),false)){
		   mainSwitch.setChecked(true);
	   }else{
		   mainSwitch.setChecked(false);
	   }
	}






}
