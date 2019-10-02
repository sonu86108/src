package com.sonu.vocabprogress.ui.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import com.sonu.vocabprogress.models.Quiz;
import java.util.List;
import com.sonu.vocabprogress.ui.activities.QuizesActivity;
import java.util.ArrayList;
import com.sonu.vocabprogress.R;
import com.sonu.vocabprogress.utilities.helpers.RecyclerViewTouchEventListener;

import java.util.zip.*;
import android.view.*;
import android.widget.*;

public class QuizesAdapter extends RecyclerView.Adapter<QuizesAdapter.QuizesViewHolder>{

	RecyclerViewTouchEventListener eventListener;
	List<Quiz> quizList;
	public QuizesAdapter(RecyclerViewTouchEventListener eventListener,List<Quiz> list){
		this.eventListener=eventListener;
		this.quizList=list;
	}
	@Override
	public QuizesViewHolder onCreateViewHolder(ViewGroup p1, int p2){
        View view=LayoutInflater.from(p1.getContext()).inflate(R.layout.row_layout_quizes,p1,
		false);
		return new QuizesViewHolder(view,eventListener);
	}

	@Override
	public void onBindViewHolder(QuizesViewHolder p1, int p2){
		p1.tvQuizId.setText(""+quizList.get(p2).getQuizId());
		p1.tvQuizName.setText(quizList.get(p2).getQuizName());
		p1.tvQuizDate.setText(quizList.get(p2).getDate());
	}

	@Override
	public int getItemCount(){
		
		return quizList.size();
	}
	
	public static class QuizesViewHolder extends RecyclerView.ViewHolder
	implements View.OnClickListener{
		TextView tvQuizId,tvQuizName,tvQuizDate;
		RecyclerViewTouchEventListener eventListener;
		public QuizesViewHolder(View v,RecyclerViewTouchEventListener eventListener){
			super(v);
			this.eventListener=eventListener;
			tvQuizId=v.findViewById(R.id.id_tv_quizId);
			tvQuizName=v.findViewById(R.id.id_tv_quizName);
			tvQuizDate=v.findViewById(R.id.id_tv_quizDate);
			v.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			eventListener.onRecyclerViewItemClick(v,getAdapterPosition());
		}
	}
	
}
