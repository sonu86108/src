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
import android.graphics.drawable.*;
import androidx.appcompat.widget.*;
import android.widget.TextView;
import com.sonu.vocabprogress.utilities.*;
import android.widget.Toast;

public class WordListActivity extends AppCompatActivity
implements View.OnClickListener,View.OnLongClickListener
{

	SelectionMode selectionMode;
	Toolbar toolbar;
	RecyclerView wordListRecyclerView;
	List<Word> wordList;
	WordListAdapter wordListAdapter;
	FloatingActionButton fabAddWord;
	SQLiteHelper db;
	Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist);
		//adding toolbar
		toolbar=findViewById(R.id.appbar);
		toolbar.setNavigationIcon(R.drawable.ic_menu_back);
		
		setSupportActionBar(toolbar);
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
		selectionMode=new SelectionMode(this);
		fabAddWord=findViewById(R.id.id_fab);
		db=SQLiteHelper.getSQLiteHelper(this);
		wordList=new ArrayList();
		//init recycler view
		initRecyView();
	}
	
	//recycler view initialization
	public void initRecyView(){
		//Data passing to adapeter
		wordListAdapter=new WordListAdapter(wordList,this);

		//setting layout manager to recycler view
		RecyclerView.LayoutManager lm=new LinearLayoutManager(getApplicationContext());
		wordListRecyclerView.setLayoutManager(lm);

		//setting item animator
		wordListRecyclerView.setItemAnimator(new DefaultItemAnimator());

		//finally setting adapter to recycler view
		wordListRecyclerView.setAdapter(wordListAdapter);
	}
	
	@Override
	public void onClick(View p1){
		if(selectionMode.isInSelectionMode()){
			switch(p1.getId()){
				case R.id.id_cardView:
				    selectionMode.selectItem(p1);
					break;
			}
		}else{
			switch(p1.getId()){
			case R.id.id_fab:
				startActivityForResult(new Intent(this,
				NotificationDialogActivity.class),24);
				break;
		    }
	    }
	}
	
	//On recyclerView item click
	public void onRecyclerViewItemClick(View v,int position){
		Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();
	}
    @Override
	public boolean onLongClick(View p1){
		selectionMode.enterSelectionMode(fabAddWord,toolbar);
		
		// TODO: Implement this method
		switch(p1.getId()){
			case R.id.id_cardView:
				selectionMode.selectItem(p1);
				break;
		}
		return true;
	}
	
	//on recyclerview item long click
	public void onRecyclerViewItemLongClick(View v,int p){
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_word_list,menu);
		return true;
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		// TODO: Implement this method
		if(requestCode==24){
			if(resultCode==RESULT_OK){
				updateWordList();
			}
		}
	}
	@Override
	protected void onStart(){
		// TODO: Implement this method
		super.onStart();
		updateWordList();
	}
	
	//on cluck home button
	@Override
    public boolean onSupportNavigateUp() {
        if(selectionMode.isInSelectionMode()){
			selectionMode.exitSelectionMode();
		}else{
			finish();
		}
        return true;
    }
	//setting event listners
	private void setListners(){
		fabAddWord.setOnClickListener(this);
	}
	
	//Read word data from sqlite database
	public void updateWordList(){
		Cursor curso =db.retrieveData();
		wordList.clear();
		if(curso.moveToFirst()){
			do{
				wordList.add(new Word(curso.getString(1),curso.getString(2),curso.getString(3)));
			}while(curso.moveToNext());
		}
		wordListAdapter.notifyDataSetChanged();
	}
}
