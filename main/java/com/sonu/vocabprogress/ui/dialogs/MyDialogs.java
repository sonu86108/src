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
import com.sonu.vocabprogress.utilities.*;

public class MyDialogs implements View.OnClickListener
{
	Dialog dialog;
	WordListActivity context;
	SelectionMode selectionMode;
	EditText edtQuizName;
	Button btnSave;
	List<Word> wordList;
	List<Integer> selectedWords;
	QuizHelper quizHelper;
	QuizWordHelper quizWordHelper;
	String date ,quizName;
	int quizId;
	QuizWord quizWord;
	public MyDialogs(Context context,SelectionMode selectionMode,List<Word> wordList,List<Integer> selectedWords){
		this.context=(WordListActivity)context;
		this.selectionMode=selectionMode;
		this.wordList=wordList;
		dialog=new Dialog(context);
		this.selectedWords=selectedWords;
		dialog.setContentView(R.layout.dialog_make_quiz);
		dialog.setCancelable(true);
		edtQuizName=dialog.findViewById(R.id.id_quizname);
		btnSave=dialog.findViewById(R.id.id_btn_save);
		quizHelper=QuizHelper.getInstance(this.context);
		quizWordHelper=QuizWordHelper.getInstance(this.context);
		btnSave.setOnClickListener(this);
		date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
		
	}
	
	public void showDialog(){
		dialog.show();
	}

	@Override
	public void onClick(View p1){
		if(p1.getId()==R.id.id_btn_save){
			quizName=edtQuizName.getText().toString().trim();
			if(quizHelper.insertData(new Quiz(quizName,date))){
				quizId=quizHelper.retrieveQuizId(quizName);
	            saveSelectedWords();
			}else{
				context.showInToast("Error creating quiz");
			}

		}
	}
	
	private void saveSelectedWords(){
		StringBuilder indexes=new StringBuilder();
		if(selectedWords.isEmpty()){
			this.context.showInSnackBar("Selected words not found");
			dialog.dismiss();
		}else{
			for(int n:selectedWords){
		     quizWordHelper.insertData(new QuizWord(quizId,wordList.get(n)));
			}
			dialog.dismiss();
			selectionMode.exitSelectionMode();
			context.showInSnackBar("Quiz created");
		}
	}
	
	
}
