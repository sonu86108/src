package com.sonu.vocabprogress.ui.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.sonu.vocabprogress.R;
import androidx.recyclerview.widget.RecyclerView;
import com.sonu.vocabprogress.ui.adapters.QuizesAdapter;
import com.sonu.vocabprogress.models.Quiz;
import java.util.List;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import com.sonu.vocabprogress.utilities.helpers.QuizHelper;
import com.sonu.vocabprogress.utilities.helpers.RecyclerViewTouchEventListener;

import android.database.Cursor;
import android.view.View;


public class QuizesActivity extends AppCompatActivity implements
		RecyclerViewTouchEventListener {

	RecyclerView quizesRecyclerView;
	QuizesAdapter quizesAdapter;
	boolean isItPlayMode;
	List<Quiz> quizList;
	QuizHelper quizHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quizes);
		init();
		ifPlayMode();
		initRecyclerView();
	}
	
	private void init(){
		quizList=new ArrayList<Quiz>();
		quizHelper=QuizHelper.getInstance(this);
	}
	
	private void initRecyclerView(){
		quizesRecyclerView=findViewById(R.id.id_quizes_recyclerView);
		quizesAdapter=new QuizesAdapter(this,quizList,isItPlayMode);
		RecyclerView.LayoutManager lm=new LinearLayoutManager(getApplicationContext());
		quizesRecyclerView.setLayoutManager(lm);
		quizesRecyclerView.setItemAnimator(new DefaultItemAnimator());
		quizesRecyclerView.setAdapter(quizesAdapter);
	}

	@Override
	protected void onStart(){
		super.onStart();
		updateQuizList();
	}
	
	private void updateQuizList(){
		quizList.clear();
		Cursor cursor=quizHelper.retrieveData();
		if(cursor.moveToFirst()){
			do{
				quizList.add(new Quiz(cursor.getInt(0),cursor
				.getString(1),cursor.getString(2)));
	
			}while(cursor.moveToNext());
		}
		quizesAdapter.notifyDataSetChanged();
	}

	@Override
	public void onRecyclerViewItemClick(View v, int p) {
		
		switch(v.getId()){
		  case R.id.id_cardView_quizRow:
			Intent intent=new Intent(QuizesActivity.this,QuizWordsActivity.class);
			intent.putExtra("quizId",quizList.get(p).getQuizId());
			startActivity(intent);
			break;
		  case R.id.id_playQuiz:
			  Intent playQuizIntent=new Intent(this,PlayQuizActivity.class);
			  playQuizIntent.putExtra("quizId",quizList.get(p).getQuizId());
			  startActivity(playQuizIntent);
		}

	}

	@Override
	public void onRecyclerViewItemLongClick(View v, int p) {

	}
	
	public void ifPlayMode(){
		if(getIntent().getExtras()!=null && getIntent().getExtras().
		getBoolean("play_mode")){
			isItPlayMode=true;
		}
	}
}
