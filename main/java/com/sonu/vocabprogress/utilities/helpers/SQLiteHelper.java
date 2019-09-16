package com.sonu.vocabprogress.utilities.helpers;
import android.database.sqlite.*;
import android.content.*;
import androidx.core.app.*;
import com.sonu.vocabprogress.models.*;
import android.database.*;
import android.widget.*;

public class SQLiteHelper extends SQLiteOpenHelper
{
	private static  SQLiteHelper sqliteHelper=null;
    public static final String DB_NAME="word_db";
	public static final int DB_VERSION=1;

	public static final String TN_WORD="word";
	public static final String C1_ID="id";
	public static final String C2_NAME="name";
	public static final String C3_MEANING="meaning";
	public static final String C4_DESC="desc";
	private Context context;

	private SQLiteHelper(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
		Toast.makeText(context, "Database created successfully", Toast.LENGTH_LONG).show();

	}

	public static SQLiteHelper getSQLiteHelper(Context context)
	{
		if (sqliteHelper == null)
		{
			sqliteHelper = new SQLiteHelper(context);
		}
		return sqliteHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		p1.execSQL("CREATE TABLE " + TN_WORD + " (" + C1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				   C2_NAME + " TEXT UNIQUE," + C3_MEANING + " TEXT," + C4_DESC + " TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{
		// TODO: Implement this method
		p1.execSQL("DROP TABLE IF EXISTS " + TN_WORD);
	}


	//Insert word data
	public boolean insertData(Word word) throws SQLiteConstraintException
	{
		SQLiteDatabase writableDb=getWritableDatabase();
		ContentValues values=new ContentValues();
		long result=-1;
		try
		{
			if (word != null)
			{
				if (word.getName() != null && word.getMeaning() != null && word.getDesc() != null)
				{
					values.put(C2_NAME, word.getName());
					String meaning=word.getMeaning() != null ?word.getMeaning(): "N/A";
					String desc=word.getDesc() != null ?word.getDesc(): "N/A";
					values.put(C3_MEANING, meaning);
					values.put(C4_DESC, desc);
					//It throws exception to handle
					result=writableDb.insertOrThrow(TN_WORD, null, values);
				
				
				}
				else if (word.getName() != null && word.getMeaning() != null)
				{
					values.put(C2_NAME, word.getName());
					values.put(C3_MEANING, word.getMeaning());
					result=writableDb.insertOrThrow(TN_WORD, null, values);
				

				}
				else if (word.getName() != null && word.getDesc() != null)
				{
					values.put(C2_NAME, word.getName());
					values.put(C4_DESC, word.getDesc());
					result=writableDb.insertOrThrow(TN_WORD, null, values);
				
				}
				else
				{
					values.put(C2_NAME, word.getName());
					result=writableDb.insertOrThrow(TN_WORD,null,values);
				
				}

			}
		}
		catch (SQLiteConstraintException duplicateEntry)
		{
			throw duplicateEntry;
		}
		catch(SQLiteException e){
			writableDb.close();
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
	
	//Update word data
	public boolean updateData(Word word) throws SQLiteConstraintException
	{
		SQLiteDatabase writableDb=getWritableDatabase();
		ContentValues values=new ContentValues();
		long result=-1;
		try
		{
			if (word != null)
			{
				if (word.getName() != null && word.getMeaning() != null && word.getDesc() != null)
				{
					values.put(C2_NAME, word.getName());
					String meaning=word.getMeaning() != null ?word.getMeaning(): "N/A";
					String desc=word.getDesc() != null ?word.getDesc(): "N/A";
					values.put(C3_MEANING, meaning);
					values.put(C4_DESC, desc);
					//It throws exception to handle
					result=writableDb.update(TN_WORD,values,C2_NAME+"=?",new String[]{word.getName()});

				}
				else if (word.getName() != null && word.getMeaning() != null)
				{
					values.put(C2_NAME, word.getName());
					values.put(C3_MEANING, word.getMeaning());
					result=writableDb.update(TN_WORD,values,C2_NAME+"=?",new String[]{word.getName()});

				}
				else if (word.getName() != null && word.getDesc() != null)
				{
					values.put(C2_NAME, word.getName());
					values.put(C4_DESC, word.getDesc());
					result=writableDb.update(TN_WORD,values,C2_NAME+"=?",new String[]{word.getName()});

				}
				else
				{
					values.put(C2_NAME, word.getName());
					result=writableDb.update(TN_WORD,values,C2_NAME+"=?",new String[]{word.getName()});

				}

			}
		}
		catch (SQLiteConstraintException duplicateEntry)
		{
			throw duplicateEntry;
		}
		catch(SQLiteException e){
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

	//Retriev word data
	public Cursor retrieveData()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		return db.rawQuery("SELECT * FROM " + TN_WORD, null);
	}
}
