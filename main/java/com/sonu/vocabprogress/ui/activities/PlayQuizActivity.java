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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class PlayQuizActivity extends AppCompatActivity
{

	int quizId,quizNumber=0,totalQuiz=10;
	List<Word> quizWords;
	List<String> randomWords;
	List<RadioButton> radioButtons;
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
		radioButtons= new ArrayList<>();
		radioButtons.add(rbOption1);
		radioButtons.add(rbOption2);
		radioButtons.add(rbOption3);
		radioButtons.add(rbOption4);
		tvqQNumber=findViewById(R.id.id_tv_qNumber);
		btnNextQuiz=findViewById(R.id.id_btn_next);
		quizWordHelper=QuizWordHelper.getInstance(this);
		randomWords=new ArrayList<>();
		loadWordsFromAssets();
		
	}
	
	public int getQuizId(){
		return getIntent().getExtras().getInt("quizId");
	}
	
	public void setQuiz(){
		Word word=quizWords.get(quizNumber);
		tvqQNumber.setText(String.valueOf(1+quizNumber)+"/"+String.valueOf(totalQuiz));
		tvQuestion.setText(text+word.getWordName());
		Random random=new Random();
		int rNum=(random.nextInt(4));
		radioButtons.get(rNum).
		setText(word.getWordMeaning());
		for(int n=0;n<=(radioButtons.size()-1);n++){
			if(n==rNum){
				continue;
			}else if(randomWords.isEmpty()==false){
				radioButtons.get(n).setText(
				randomWords.get(random.nextInt(randomWords.size())));
			}
		}
		if(quizWords.size()==quizNumber+1 ||quizNumber==9){
			btnNextQuiz.setEnabled(false);
		}
		quizNumber++;
		
	}
	public void onClickNextButton(View v){
		setQuiz();
	}
	
	public void loadWordsFromAssets(){
		InputStreamReader isr=null;
		try{
			isr=new InputStreamReader(
			getAssets().open("random_words.txt"));
			BufferedReader br=new BufferedReader(isr);
			String word=new String();
			while((word=br.readLine())!=null){
				randomWords.add(word);
			}
			isr.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
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

