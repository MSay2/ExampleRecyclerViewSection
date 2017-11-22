package com.msay2.recyclerviewsectionned_example.adapter;

import com.msay2.recyclerviewsectionned_example.R;
import com.msay2.recyclerviewsectionned_example.item_data.ItemData;

import android.support.v7.widget.RecyclerView;

import android.support.annotation.NonNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
	private final Context context;
	private final List<ItemData> item_list;
	
	private static final int TYPE_HEADER = 0;
    private static final int TYPE_CONTENT = 1;
	
	public Adapter(@NonNull Context context, @NonNull List<ItemData> item_list)
	{
		this.context = context;
		this.item_list = item_list;
	}

	@Override
	public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View viewHolder = null;
		if (viewType == TYPE_HEADER)
		{
			viewHolder = LayoutInflater.from(context).inflate(R.layout.item_section, parent, false);
		}
		else if (viewType == TYPE_CONTENT)
		{
			viewHolder = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
		}
		return new ViewHolder(viewHolder, viewType);
	}

	@Override
	public void onBindViewHolder(Adapter.ViewHolder holder, int position)
	{
		if (holder.holderId == TYPE_HEADER)
		{
			holder.text.setText(item_list.get(position).getText());
		}
		else if (holder.holderId == TYPE_CONTENT)
		{
			holder.image.setImageResource(item_list.get(position).getImage());
			holder.text.setText(item_list.get(position).getText());
		}
	}

	@Override
	public int getItemCount()
	{
		return item_list.size();
	}

	@Override
	public int getItemViewType(int position)
	{
		if (position == 0 || position == 21 || position == 42)
		{
			return TYPE_HEADER;
		}
		return TYPE_CONTENT;
	}
	
	class ViewHolder extends RecyclerView.ViewHolder
	{
		private ImageView image;
		private TextView text;
		
		int holderId;
		
		ViewHolder(View itemView, int viewType)
		{
			super(itemView);
			if (viewType == TYPE_HEADER)
			{
				text = (TextView)itemView.findViewById(R.id.id_title_section);
				
				holderId = TYPE_HEADER;
			}
			else if (viewType == TYPE_CONTENT)
			{
				image = (ImageView)itemView.findViewById(R.id.id_image);
				text = (TextView)itemView.findViewById(R.id.id_text);
				
				holderId = TYPE_CONTENT;
			}
		}
	}
}
