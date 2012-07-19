package org.tophat.assassin.components;

import java.util.List;
import java.util.Map;

import org.tophat.assassin.R;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class QViewAdapter extends SimpleAdapter{

	private String[] ids;
	Color c;
	
	public QViewAdapter(Context context, List<? extends Map<String, ?>> objects, String[] from, int[] to, String[] ids) {
	    super(context, objects, R.layout.single_list_item, from, to);
	    
	    this.ids = ids;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View v = super.getView(position, convertView, parent);
	    return v;
	}
}
