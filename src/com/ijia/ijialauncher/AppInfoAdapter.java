package com.ijia.ijialauncher;

import java.util.HashMap;
import java.util.List;
import com.ijia.ijialauncher.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//自定义适配器类，提供给listView的自定义view  
public class AppInfoAdapter extends BaseAdapter {

	private List<HashMap<String, Object>> mlistAppInfo = null;

	LayoutInflater infater = null;

	public AppInfoAdapter(Context context, List<HashMap<String, Object>> apps) {
		infater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mlistAppInfo = apps;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		System.out.println("size" + mlistAppInfo.size());
		return mlistAppInfo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlistAppInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		// System.out.println("getView at " + position);
		View iv = null;
		@SuppressWarnings("unchecked")
		HashMap<String, Object> item = (HashMap<String, Object>) getItem(position);
		if (view == null || view.getTag() == null) {
			iv = infater.inflate(R.layout.app_item, null);
			iv.setTag(item);

			((ImageView) iv.findViewById(R.id.Icon))
					.setImageDrawable((Drawable) item.get("Icon"));
			((TextView) iv.findViewById(R.id.Label)).setText(item.get("Label")
					.toString());
		} else {
			iv = view;
		}
		// ((ImageView) iv.findViewById(R.id.Icon))
		// .setImageDrawable((Drawable) item.get("Icon"));
		// ((TextView) iv.findViewById(R.id.Label)).setText(item.get("Label")
		// .toString());
		return iv;
	}
}
