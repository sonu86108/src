package com.sonu.vocabprogress.models;

public class Word
{
	String name,meaning,desc;

	public Word(String name, String meaning, String desc)
	{
		this.name = name;
		this.meaning = meaning;
		this.desc = desc;
	}


	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setMeaning(String meaning)
	{
		this.meaning = meaning;
	}

	public String getMeaning()
	{
		return meaning;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public String getDesc()
	{
		return desc;
	}
}
