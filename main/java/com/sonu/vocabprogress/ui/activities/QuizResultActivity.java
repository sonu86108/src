package com.sonu.vocabprogress.ui.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sonu.vocabprogress.R;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class QuizResultActivity extends AppCompatActivity
implements View.OnClickListener{

	TextView tvShowScore;
	Button btnOk,btnRestart;
	int score,quizId;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_result);
		init();
		showScore();
		btnOk.setOnClickListener(this);
		btnRestart.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View p1)
	{
		if(p1.getId()==R.id.id_btn_ok){
			finish();
		}else if(p1.getId()==R.id.id_btn_restart){
			Intent intent=new Intent(this,PlayQuizActivity.class);
			intent.putExtra("quizId",quizId);
			startActivity(intent);
			finish();
		}
	}
	public void init(){
		tvShowScore=findViewById(R.id.id_tv_showScore);
		btnOk=findViewById(R.id.id_btn_ok);
		btnRestart=findViewById(R.id.id_btn_restart);
		getdataFromParent();
	}
	public void showScore(){
		tvShowScore.setText(String.valueOf(score)+"/10");
	}
	
	public void getdataFromParent(){
		score=getIntent().getExtras().getInt("score");
		quizId=getIntent().getExtras().getInt("quizId");
	}
	
	
	
	
}
