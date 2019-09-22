package com.sonu.vocabprogress.utilities;
import com.sonu.vocabprogress.ui.activities.*;
import android.content.*;
import com.sonu.vocabprogress.models.*;
import java.util.*;
import com.google.android.material.floatingactionbutton.*;
import android.view.*;
import android.graphics.drawable.*;
import com.sonu.vocabprogress.R;
import androidx.appcompat.widget.*;
import com.sonu.vocabprogress.ui.adapters.*;
import com.sonu.vocabprogress.ui.adapters.WordListAdapter.WordListViewHolder;

public class SelectionMode
{
	private WordListActivity wordListActivity;
	private boolean isInSelectionMode;
	FloatingActionButton fab;
	Drawable background;
	View view;
	Toolbar toolbar;
	private int counter;
	private List<Word> selectedWordList;
	private List<View> selectedWordView;
	public SelectionMode(Context context){
		this.wordListActivity=(WordListActivity)context;
		selectedWordList=new ArrayList<Word>();
		selectedWordView=new ArrayList<View>();
	}
	
	public boolean isInSelectionMode(){
		return this.isInSelectionMode;
	}
	
	public void setSelectionMode(boolean b){
		this.isInSelectionMode=b;
	}
	
	public void updateCounter(){
		counter=counter+1;
		toolbar.setTitle(counter+" items selected");
	}
	
	public void selectItem(View view){
		selectedWordView.add(view);
		view.setEnabled(false);
		background=view.getBackground();
		view.setBackgroundColor(R.color.colorPrimary);
		updateCounter();
	}
	
	public void enterSelectionMode(FloatingActionButton fab,
	Toolbar toolbar){
		isInSelectionMode=true;
		this.fab=fab;
		this.toolbar=toolbar;
		toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_selection_mode);
		fab.setVisibility(View.GONE);
		toolbar.setTitle(counter+" items selected");
	}
	
	public void exitSelectionMode(){
		isInSelectionMode=false;
		for(View v:selectedWordView){
			v.setBackground(background);
			v.setEnabled(true);
		}
		this.toolbar.getMenu().clear();
		toolbar.inflateMenu(R.menu.menu_word_list);
		this.fab.setVisibility(View.VISIBLE);
		this.toolbar.setTitle("Word List");
		counter=0;
	}
	
}
