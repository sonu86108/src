package com.sonu.vocabprogress.ui.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sonu.vocabprogress.R;
import java.util.List;
import com.sonu.vocabprogress.models.Word;
import com.sonu.vocabprogress.utilities.helpers.QuizWordHelper;
import android.database.Cursor;
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.RadioButton;
import java.util.Random;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;

public class PlayQuizActivity extends AppCompatActivity
{

	int quizId,quizNumber=0,totalQuiz=10;
	List<Word> quizWords;
	QuizWordHelper quizWordHelper;
	TextView  tvQuestion,tvqQNumber;
	String text="Select the appropriate option for the word: ";
	RadioGroup rgOptions;
	RadioButton rbOption1,rbOption2,rbOption3,rbOption4;
	Button btnNextQuiz;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_quiz);
		init();
		quizId=getQuizId();
		quizWords=getQuizWords();
		setQuiz();
		btnNextQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
			public void onClick(View p1){
					onClickNextButton(p1);
				}});
	}
	
	public void init(){
		tvQuestion=findViewById(R.id.id_tv_question);
		rgOptions=findViewById(R.id.id_rg_o);
		rbOption1=findViewById(R.id.id_rb_o1);
		rbOption2=findViewById(R.id.id_rb_o2);
		rbOption3=findViewById(R.id.id_rb_o3);
		rbOption4=findViewById(R.id.id_rb_o4);
		tvqQNumber=findViewById(R.id.id_tv_qNumber);
		btnNextQuiz=findViewById(R.id.id_btn_next);
		quizWordHelper=QuizWordHelper.getInstance(this);
		
	}
	
	public int getQuizId(){
		return getIntent().getExtras().getInt("quizId");
	}
	
	public void setQuiz(){
		Word word=quizWords.get(quizNumber);
		tvqQNumber.setText(String.valueOf(1+quizNumber)+"/"+String.valueOf(totalQuiz));
		tvQuestion.setText(text+word.getWordName());
		Random random=new Random();
		((RadioButton)rgOptions.getChildAt(random.nextInt(5)-1)).
		setText(word.getWordMeaning());
		if(quizNumber==9){
			btnNextQuiz.setEnabled(false);
		}
		quizNumber++;
		
	}
	public void onClickNextButton(View v){
		setQuiz();
	}
	
	public List<Word> getQuizWords(){
	    ArrayList<Word> quizWords=new ArrayList<>();
		Cursor cursor=quizWordHelper.retrieveData(quizId);
		quizWords.clear();
		if(cursor.moveToFirst()){
			do{
				quizWords.add(new Word(cursor.getString(0),
				cursor.getString(1),cursor.getString(2)));
			}while(cursor.moveToNext());
		}
		return quizWords;
	}
	
	
}

