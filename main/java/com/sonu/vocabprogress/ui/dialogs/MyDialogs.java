package com.sonu.vocabprogress.ui.dialogs;
import android.content.*;
import android.app.*;
import com.sonu.vocabprogress.R;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import java.util.*;
import com.sonu.vocabprogress.models.*;
import com.sonu.vocabprogress.utilities.helpers.*;
import android.icu.text.*;
import com.sonu.vocabprogress.ui.activities.*;

public class MyDialogs implements View.OnClickListener
{
	Dialog dialog;
	WordListActivity context;
	EditText edtQuizName;
	Button btnSave;
	List<Word> wordList;
	List<Integer> selectedWords;
	public MyDialogs(Context context,List<Word> wordList,List<Integer> selectedWords){
		this.context=(WordListActivity)context;
		this.wordList=wordList;
		dialog=new Dialog(context);
		this.selectedWords=selectedWords;
		dialog.setContentView(R.layout.dialog_make_quiz);
		dialog.setCancelable(true);
		edtQuizName=dialog.findViewById(R.id.id_quizname);
		btnSave=dialog.findViewById(R.id.id_btn_save);
		btnSave.setOnClickListener(this);
		
	}
	
	public void showDialog(){
		dialog.show();
	}

	@Override
	public void onClick(View p1){
		if(p1.getId()==R.id.id_btn_save){
			QuizHelper quizHelper=QuizHelper.getInstance(this.context);
			QuizWordHelper quizWordHelper=QuizWordHelper.getInstance(this.context);
			String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
			if(quizHelper.insertData(new Quiz(edtQuizName.getText().toString().trim(),date))){
				dialog.dismiss();
	           for(int n:selectedWords){
				   int quizId=quizHelper.retrieveQuizId(edtQuizName.getText().toString().trim());
				   quizWordHelper.insertData(new QuizWord(wordList.get(n),quizId));
				  
			   }
				this.context.showInSnackBar("data saved");
			}

		}
	}

	
	
}
