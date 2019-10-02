package com.sonu.vocabprogress.utilities.helpers;
import android.database.sqlite.*;
import android.content.*;
import androidx.core.app.*;
import com.sonu.vocabprogress.models.*;
import android.database.*;
import android.widget.*;

public class SQLiteHelper extends SQLiteOpenHelper{
	private static  SQLiteHelper sqliteHelper=null;
    public static final String DB_NAME="word_db";
	public static final int DB_VERSION=1;

	public static final String TN_WORD="words";
	public static final String C1_ID="id";
	public static final String C2_NAME="name";
	public static final String C3_MEANING="meaning";
	public static final String C4_DESC="desc";
	
	public static final String TN_QUIZ_LIST="quiz_list";
	public static final String C1_QUIZ_ID="quiz_id";
	public static final String C2_QUIZ_NAME="quiz_name";
	public static final String C3_QUIZ_DATE="date";
	
	public static final String TN_QUIZ_WORD_LIST="quiz_word_list";
	public static final String C1_QWL_ID="id";
	public static final String C2_QWL_WORD_NAME="word_name";
	public static final String C3_QUIZ_ID="quiz_id";
	public static final String C4_QWL_MEANING="meaning";
	public static final String C5_QWL_DESC="desc";
	
	private Context context;

	private SQLiteHelper(Context context){
		super(context, DB_NAME, null, DB_VERSION);

	}

	public static SQLiteHelper getSQLiteHelper(Context context){
		if (sqliteHelper == null){
			sqliteHelper = new SQLiteHelper(context);
		}
		return sqliteHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase p1){
		p1.execSQL("CREATE TABLE " + TN_WORD + " (" + C1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				   C2_NAME + " TEXT UNIQUE," + C3_MEANING + " TEXT," + C4_DESC + " TEXT)");
		p1.execSQL("CREATE TABLE " + TN_QUIZ_LIST + " (" + C1_QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				   C2_QUIZ_NAME + " TEXT UNIQUE," + C3_QUIZ_DATE + " TEXT)");
		p1.execSQL("CREATE TABLE " + TN_QUIZ_WORD_LIST + " (" + C1_QWL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				   C2_QWL_WORD_NAME+ " TEXT UNIQUE," + C3_QUIZ_ID + " INTEGER," + C4_QWL_MEANING+ " TEXT,"+C5_QWL_DESC+" TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3){
		// TODO: Implement this method
		p1.execSQL("DROP TABLE IF EXISTS " + TN_WORD);
		p1.execSQL("DROP TABLE IF EXISTS " + TN_QUIZ_LIST);
		p1.execSQL("DROP TABLE IF EXISTS " + TN_QUIZ_WORD_LIST);
	}


	//Insert word data
	public boolean insertData(Word word) throws SQLiteConstraintException{
		SQLiteDatabase writableDb=getWritableDatabase();
		ContentValues values=new ContentValues();
		long result=-1;
		try{
			if (word != null){
				if (word.getWordName() != null && word.getWordMeaning() != null && word.getWordDesc() != null){
					values.put(C2_NAME, word.getWordName());
					String meaning=word.getWordMeaning() != null ?word.getWordMeaning(): "N/A";
					String desc=word.getWordDesc() != null ?word.getWordDesc(): "N/A";
					values.put(C3_MEANING, meaning);
					values.put(C4_DESC, desc);
					//It throws exception to handle
					writableDb.insertOrThrow(TN_WORD, null, values);
				
				
				}else if (word.getWordName() != null && word.getWordMeaning() != null){
					values.put(C2_NAME, word.getWordName());
					values.put(C3_MEANING, word.getWordMeaning());
					writableDb.insertOrThrow(TN_WORD, null, values);
				

				}else if (word.getWordName() != null && word.getWordDesc() != null){
					values.put(C2_NAME, word.getWordName());
					values.put(C4_DESC, word.getWordDesc());
					writableDb.insertOrThrow(TN_WORD, null, values);
				
				}else{
					values.put(C2_NAME, word.getWordName());
					writableDb.insertOrThrow(TN_WORD,null,values);
				}
			}
		}catch (SQLiteConstraintException duplicateEntry){
			throw duplicateEntry;
		}catch(SQLiteException e){
			return false;
		}finally{
			writableDb.close();
		}
		return true;
	}
	
	//Update word data
	public boolean updateData(Word word) throws SQLiteConstraintException{
		SQLiteDatabase writableDb=getWritableDatabase();
		ContentValues values=new ContentValues();
		long result=-1;
		try{
			if (word != null){
				if (word.getWordName() != null && word.getWordMeaning() != null && word.getWordDesc() != null){
					values.put(C2_NAME, word.getWordName());
					String meaning=word.getWordMeaning() != null ?word.getWordMeaning(): "N/A";
					String desc=word.getWordDesc() != null ?word.getWordDesc(): "N/A";
					values.put(C3_MEANING, meaning);
					values.put(C4_DESC, desc);
					//It throws exception to handle
					result=writableDb.update(TN_WORD,values,C2_NAME+"=?",new String[]{word.getWordName()});

				}else if (word.getWordName() != null && word.getWordMeaning() != null){
					values.put(C2_NAME, word.getWordName());
					values.put(C3_MEANING, word.getWordMeaning());
					result=writableDb.update(TN_WORD,values,C2_NAME+"=?",new String[]{word.getWordName()});

				}else if (word.getWordName() != null && word.getWordDesc() != null){
					values.put(C2_NAME, word.getWordName());
					values.put(C4_DESC, word.getWordDesc());
					result=writableDb.update(TN_WORD,values,C2_NAME+"=?",new String[]{word.getWordName()});

				}else{
					values.put(C2_NAME, word.getWordName());
					result=writableDb.update(TN_WORD,values,C2_NAME+"=?",new String[]{word.getWordName()});

				}

			}
		}catch (SQLiteConstraintException duplicateEntry){
			throw duplicateEntry;
		}catch(SQLiteException e){
			return false;
		}finally{
			writableDb.close();
		}
		if(result==-1){
			return false;
		}else{
			return true;
		}
	}
	
	public Cursor retrieveData()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		return db.rawQuery("SELECT * FROM " + TN_WORD, null);
	}
	public boolean deleteData(String w){
		long result=getWritableDatabase().delete(TN_WORD,C2_NAME+"=?",new String[]{w});
		if(result==-1){
			return false;
		}else{
			return true;
		}
	}
	
}
