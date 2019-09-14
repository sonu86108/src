package com.sonu.vocabprogress.adapters;
import androidx.recyclerview.widget.*;
import android.view.*;
import android.widget.*;
import android.text.*;
import com.sonu.vocabprogress.R;
import com.sonu.vocabprogress.models.*;
import java.util.*;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordListViewHolder>
{
	List<Word> wordList;
	
	public WordListAdapter(List<Word> wordlist){
		this.wordList=wordlist;
	}

	@Override
	public WordListViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		// TODO: Implement this method
		View view=LayoutInflater.from(p1.getContext()).
		inflate(R.layout.row_layout_words,p1,false);
		return new WordListViewHolder(view);
	}
	

	@Override
	public void onBindViewHolder(WordListAdapter.WordListViewHolder p1, int p2)
	{
		// TODO: Implement this method
		Word word =this.wordList.get(p2);
		p1.name.setText(word.getName());
		p1.meaning.setText(word.getMeaning());
		p1.desc.setText(word.getDesc());
	}
	
	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return wordList.size();
	}

	//ViewModel class extending RecyclerView.ViewHolder
	public class WordListViewHolder extends RecyclerView.ViewHolder{
		public TextView name,meaning,desc;
		public WordListViewHolder(View view){
			super(view);
			name=view.findViewById(R.id.id_textview_word);
			meaning=view.findViewById(R.id.id_textview_meaning);
			desc=view.findViewById(R.id.id_textview_desc);
		}
	}

	
	
}
