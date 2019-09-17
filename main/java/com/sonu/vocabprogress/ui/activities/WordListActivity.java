package com.sonu.vocabprogress.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.*;
import com.sonu.vocabprogress.R;
import androidx.recyclerview.widget.*;
import com.sonu.vocabprogress.models.*;
import java.util.*;
import com.sonu.vocabprogress.ui.adapters.*;
import android.database.*;
import com.sonu.vocabprogress.utilities.helpers.*;
import com.google.android.material.floatingactionbutton.*;
import android.view.*;
import android.content.*;

public class WordListActivity extends AppCompatActivity
implements View.OnClickListener
{

	
	
	RecyclerView wordListRecyclerView;
	List<Word> wordList;
	WordListAdapter wordListAdapter;
	FloatingActionButton fabAddWord;
	SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist);
		
		
		//initializations
		init();
		//wordList add
		//updateWordList();
		
		//setting event listeners
		setListners();
		
		
		
    }
	//initializations
	public void init(){
		wordListRecyclerView=findViewById(R.id.id_recyclerview_wordlist);
		fabAddWord=findViewById(R.id.id_fab);
		db=SQLiteHelper.getSQLiteHelper(this);
		wordList=new ArrayList();
		//init recycler view
		initRecyView();
	}
	
	//recycler view initialization
	public void initRecyView(){
		//Data passing to adapeter
		wordListAdapter=new WordListAdapter(wordList);

		//setting layout manager to recycler view
		RecyclerView.LayoutManager lm=new LinearLayoutManager(getApplicationContext());
		wordListRecyclerView.setLayoutManager(lm);

		//setting item animator
		wordListRecyclerView.setItemAnimator(new DefaultItemAnimator());

		//finally setting adapter to recycler view
		wordListRecyclerView.setAdapter(wordListAdapter);
	}
	
	@Override
	public void onClick(View p1)
	{
		switch(p1.getId()){
			case R.id.id_fab:
				startActivityForResult(new Intent(this,
				NotificationDialogActivity.class),24);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO: Implement this method
		if(requestCode==24){
			if(resultCode==RESULT_OK){
				updateWordList();
			}
		}
	}
	
	

	@Override
	protected void onStart()
	{
		// TODO: Implement this method
		super.onStart();
		updateWordList();
	}
	
	
	
	
	
	//setting event listners
	private void setListners(){
		fabAddWord.setOnClickListener(this);
	}
	
	//Read word data from sqlite database
	public void updateWordList(){
		Cursor curso =db.retrieveData();
		if(curso.moveToFirst()){
			do{
				wordList.add(new Word(curso.getString(1),curso.getString(2),curso.getString(3)));
			}while(curso.moveToNext());
		}
		wordListAdapter.notifyDataSetChanged();
	}
}
