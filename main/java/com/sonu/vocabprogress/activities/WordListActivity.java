package com.sonu.vocabprogress.activities;

import android.os.Bundle;
import androidx.appcompat.app.*;
import com.sonu.vocabprogress.R;
import androidx.recyclerview.widget.*;
import com.sonu.vocabprogress.models.*;
import java.util.*;
import com.sonu.vocabprogress.adapters.*;
import android.database.*;
import com.sonu.vocabprogress.utilities.helpers.*;

public class WordListActivity extends AppCompatActivity 
{
	RecyclerView wordListRecyclerView;
	List<Word> wordList;
	WordListAdapter wordListAdapter;
	SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist);
		
		wordListRecyclerView=findViewById(R.id.id_recyclerview_wordlist);
		db=new SQLiteHelper(this);
		wordList=new ArrayList();
		
		//Data passing to adapeter
		wordListAdapter=new WordListAdapter(wordList);
		
		//setting layout manager to recycler view
		RecyclerView.LayoutManager lm=new LinearLayoutManager(getApplicationContext());
		wordListRecyclerView.setLayoutManager(lm);
		
		//setting item animator
		wordListRecyclerView.setItemAnimator(new DefaultItemAnimator());
		
		//finally setting adapter to recycler view
		wordListRecyclerView.setAdapter(wordListAdapter);
		
		updateWordList();
		
		
		
		
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
