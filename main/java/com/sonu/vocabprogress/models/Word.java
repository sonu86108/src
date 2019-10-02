package com.sonu.vocabprogress.models;

public class Word
{
	String wordName,wordMeaning,wordDesc;

	public Word(String wordName, String wordMeaning, String wordDesc){
		this.wordName = wordName;
		this.wordMeaning = wordMeaning;
		this.wordDesc = wordDesc;
	}

	public void setWordName(String wordName){
    this.wordName = wordName;
    }

public String getWordName()
{
return wordName;
}

public void setWordMeaning(String wordMeaning)
{
this.wordMeaning = wordMeaning;
}

public String getWordMeaning()
{
return wordMeaning;
}

public void setWordDesc(String wordDesc)
{
this.wordDesc = wordDesc;
}

public String getWordDesc()
{
return wordDesc;
}


}
