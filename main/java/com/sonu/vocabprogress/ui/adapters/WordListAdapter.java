package com.sonu.vocabprogress.ui.adapters;
import androidx.recyclerview.widget.*;
import android.view.*;
import android.widget.*;
import android.text.*;
import com.sonu.vocabprogress.R;
import com.sonu.vocabprogress.models.*;
import java.util.*;
import com.sonu.vocabprogress.ui.activities.*;
import android.content.*;
import androidx.cardview.widget.*;
import android.view.View.*;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordListViewHolder>
{
	List<Word> wordList;
	WordListActivity wordListActivity;
	
	public WordListAdapter(List<Word> wordlist,Context ctx){
		this.wordList=wordlist;
		this.wordListActivity=(WordListActivity)ctx;
		
	}

	@Override
	public WordListViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		// TODO: Implement this method
		View view=LayoutInflater.from(p1.getContext()).
		inflate(R.layout.row_layout_words,p1,false);
		return new WordListViewHolder(view,wordListActivity);
	}
	

	@Override
	public void onBindViewHolder(WordListAdapter.WordListViewHolder p1, int p2)
	{
		// TODO: Implement this method
		Word word =this.wordList.get(p2);
		p1.name.setText(word.getName());
		p1.meaning.setText(word.getMeaning());
		p1.desc.setText(word.getDesc());
		//p1.cardView.setOnLongClickListener(this.mainActivity);
	}
	
	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return wordList.size();
	}

	//ViewModel class extending RecyclerView.ViewHolder
	public class WordListViewHolder extends RecyclerView.ViewHolder implements
	View.OnClickListener,View.OnLongClickListener{
		public TextView name,meaning,desc;
		WordListActivity wordListActivity;
		CardView cardView;
		public WordListViewHolder(View view,Context wordListActivity){
			super(view);
			this.wordListActivity=(WordListActivity)wordListActivity;
			name=view.findViewById(R.id.id_textview_word);
			meaning=view.findViewById(R.id.id_textview_meaning);
			desc=view.findViewById(R.id.id_textview_desc);
			//desc.setVisibility(View.GONE);
			cardView=view.findViewById(R.id.id_cardView);
			cardView.setOnLongClickListener(this);
			cardView.setOnClickListener(this);
		}

		@Override
		public void onClick(View p1)
		{
			wordListActivity.onRecyclerViewItemClick(p1,getAdapterPosition());
		}

		@Override
		public boolean onLongClick(View p1)
		{
			wordListActivity.onRecyclerViewItemLongClick(p1,getAdapterPosition());
			return true;
		}

		
        
		
		
	}

	
	
}
