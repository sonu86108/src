package com.sonu.vocabprogress.models;

public class Quiz
{
	private String quizName,date;
	private int quizId;

	public Quiz(int quizId,String quizName, String date){
		this.quizId=quizId;
		this.quizName = quizName;
		this.date = date;
	}
	public Quiz(String quizName, String date){
		this.quizName = quizName;
		this.date = date;
	}


	public void setQuizName(String quizName){
		this.quizName = quizName;
	}

	public String getQuizName(){
		return quizName;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setQuizId(int quizId){
		this.quizId = quizId;
	}

	public int getQuizId(){
		return quizId;
	}
}
