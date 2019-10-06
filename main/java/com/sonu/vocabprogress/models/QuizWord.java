package com.sonu.vocabprogress.models;

public class QuizWord extends Word
{

	private int quizId;
	public QuizWord(int quizId,Word word){
		super(word.getWordName(),word.getWordMeaning(),word.getWordDesc());
		this.quizId=quizId;
	}

	public void setQuizId(int quizId)
	{
		this.quizId = quizId;
	}

	public int getQuizId()
	{
		return quizId;
	}

}
