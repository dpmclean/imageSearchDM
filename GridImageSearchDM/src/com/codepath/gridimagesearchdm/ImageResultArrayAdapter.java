package com.codepath.gridimagesearchdm;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResults> {
	public ImageResultArrayAdapter(Context context, List<ImageResults> images){
		super(context, R.layout.item_image_result, images);
	}

	@Override
	// position: position of item in the array,
	// convetView: for helping manage use of memory in the pool for different views in the array
	// parent:   gives access to the grid view itself
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResults imageInfo = this.getItem(position);
		SmartImageView ivImage;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			ivImage = (SmartImageView) inflater.inflate(R.layout.item_image_result, parent, false);
		} else {
			ivImage = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		ivImage.setImageUrl(imageInfo.getThmbUrl());
		return ivImage;
	}
}
