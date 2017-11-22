package com.msay2.recyclerviewsectionned_example.item_data;

public class ItemData
{
	private int image;
	private String text;
	
	public ItemData(int image, String text)
	{
		this.image = image;
		this.text = text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}


	public void setImage(int image)
	{
		this.image = image;
	}

	public int getImage()
	{
		return image;
	}
}
