package com.msay2.recyclerviewsectionned_example;

import com.msay2.recyclerviewsectionned_example.item_data.ItemData;
import com.msay2.recyclerviewsectionned_example.adapter.Adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;

import java.util.List;
import java.util.ArrayList;

import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity 
{
	private RecyclerView recycler;
	private Adapter adapter;
	private GridLayoutManager layout_manager;
	private int COLUMN_COUNT = 3;
	private String LOG_TAG = MainActivity.class.getSimpleName();
	private List<ItemData> list;
	private List<ItemData> section_01;
	private List<ItemData> section_02;
	private List<ItemData> section_03;
	private AsyncTask<Void, Void, Boolean> getList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		recycler = (RecyclerView)findViewById(R.id.ma_recyclerView);
		recycler.setLayoutManager(layout_manager = new GridLayoutManager(this, COLUMN_COUNT));
		recycler.setItemAnimator(new DefaultItemAnimator());
		recycler.setHasFixedSize(true);
		
		obtainSpanSizeLookUp();
		getList();
    }
	
	private void obtainSpanSizeLookUp()
	{
        adapter = (Adapter)recycler.getAdapter();
        layout_manager = (GridLayoutManager)recycler.getLayoutManager();
        try 
		{
            layout_manager.setSpanCount(COLUMN_COUNT);
            layout_manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
			{
				@Override
				public int getSpanSize(int position)
				{
					if (position == 0 || position == 21 || position == 42)
					{
						return COLUMN_COUNT;
					}
					return 1;
				}
			});
        } 
		catch (Exception e) 
		{
			Log.e(LOG_TAG, e.getLocalizedMessage());
		}
    }
	
	private void getList()
	{
		getList = new AsyncTask<Void, Void, Boolean>()
		{
			@Override
			protected void onPreExecute()
			{
				super.onPreExecute();
				
				list = new ArrayList<>();
			}
			
			@Override
			protected Boolean doInBackground(Void... params)
			{
				try
				{
					Thread.sleep(1);
					
					String[] name = getResources().getStringArray(R.array.texts);
					TypedArray image = getResources().obtainTypedArray(R.array.images);
					
					section_01 = new ArrayList<>();
					section_02 = new ArrayList<>();
					section_03 = new ArrayList<>();
					
					section_01.add(new ItemData(-1, "Section 1"));
					section_02.add(new ItemData(-1, "Section 2"));
					section_03.add(new ItemData(-1, "Section 3"));
					
					for (int i = 0; i < name.length; i++)
					{
						int icon = R.drawable.ic_launcher;
						if (i < image.length())
						{
							icon = image.getResourceId(i, icon);
						}
						section_01.add(new ItemData(icon, name[i].toString()));
						section_02.add(new ItemData(icon, name[i].toString()));
						section_03.add(new ItemData(icon, name[i].toString()));
					}
					
					list.addAll(section_01);
					list.addAll(section_02);
					list.addAll(section_03);
					
					return true;
				}
				catch (InterruptedException e)
				{
					Log.e(LOG_TAG, e.getLocalizedMessage());
					return false;
				}
			}
			
			@Override
			protected void onPostExecute(Boolean result)
			{
				super.onPostExecute(result);
				if (result)
				{
					recycler.setAdapter(new Adapter(MainActivity.this, list));
					obtainSpanSizeLookUp();
				}
				else
				{
					// For ERROR
				}
			}
			
		}.execute();
	}
}
