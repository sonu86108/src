package com.sonu.vocabprogress.utilities.helpers;
import android.content.ContentValues;
import com.sonu.vocabprogress.models.QuizWord;
import android.database.sqlite.*;
import android.database.*;
import android.content.Context;

public class QuizWordHelper
{
	private static QuizWordHelper instance=null;
	private SQLiteHelper db;
	private QuizWordHelper(Context context){
		db=SQLiteHelper.getSQLiteHelper(context);
	}
	
	public static QuizWordHelper getInstance(Context context){
		if(instance==null){
			instance=new QuizWordHelper(context);
		}return instance;
	}
	
	public boolean insertData(QuizWord quizWord) throws SQLiteConstraintException{
	    long result=-1;
		ContentValues values=new ContentValues();
		values.put(db.C2_QWL_WORD_NAME,quizWord.getWordName());
		values.put(db.C3_QUIZ_ID,quizWord.getQuizId());
		values.put(db.C4_QWL_MEANING,quizWord.getWordMeaning());
		values.put(db.C5_QWL_DESC,quizWord.getWordDesc());
		try{
			result =db.getWritableDatabase().insertOrThrow(db.TN_QUIZ_LIST, null, values);
		}
		catch (SQLiteConstraintException e){
			throw e;
		}catch(SQLException e){

		}finally{
			db.close();
		}
		if(result==-1){
			return false;
		}else{
			return true;
		}
	}
	
	public Cursor retrieveData(){
		String query="SELECT "+db.C2_QWL_WORD_NAME+","+
				db.C4_QWL_MEANING+","+db.C5_QWL_DESC+" FROM "+db.TN_QUIZ_WORD_LIST+" WHERE "+db.C3_QUIZ_ID+"=";
		String q="SELECT * FROM "+db.TN_QUIZ_WORD_LIST;
		Cursor cursor=
		db.getReadableDatabase().rawQuery(q,null);
		return cursor;
	}
	
}
