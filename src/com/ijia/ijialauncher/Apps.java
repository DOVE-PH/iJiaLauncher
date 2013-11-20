package com.ijia.ijialauncher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.iijia.ijialauncher.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class Apps extends Activity implements OnItemClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apps);
		setTitle("应用程序列表");
		// 生成动态数组，并且转入数据
		ArrayList<HashMap<String, Object>> lstImageItem = getApps();

		AppInfoAdapter appAdapter = new AppInfoAdapter(this, lstImageItem);

		GridView gridview = (GridView) findViewById(R.id.gvAppList); // 添加并且显示
		gridview.setAdapter(appAdapter);
		gridview.setOnItemClickListener(this);

		// 为所有列表项注册上下文菜单
		registerForContextMenu(gridview);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.app_item, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// 得到当前被选中的item信息
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> values = (HashMap<String, Object>) menuInfo.targetView
				.getTag();
		switch (item.getItemId()) {
		case R.id.mi_appitem_remove:
			Alert(this, "确认", "确定要卸载" + values.get("Label").toString() + "吗？");
			break;
		default:
			return super.onContextItemSelected(item);
		}
		return true;
	}

	public static void Alert(Context context, String title, String msg) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle(title);
		alert.setMessage(msg);
		alert.setNegativeButton("确定", null);
		alert.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 自动生成的方法存根
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * @param parent
	 *            发生点击动作的AdapterView。
	 * @param view
	 *            在AdapterView中被点击的视图(它是由adapter提供的一个视图)。
	 * @param position
	 *            视图在adapter中的位置。
	 * @param id
	 *            被点击元素的行id。
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// HashMap<String, Object> item = (HashMap<String, Object>)
		// parent.getItemAtPosition(position);//这种方式也可以
		@SuppressWarnings("unchecked")
		HashMap<String, Object> item = (HashMap<String, Object>) view.getTag();

		ComponentName componet = new ComponentName(item.get("PackageName")
				.toString(), item.get("Name").toString());
		Intent intent = new Intent();
		intent.setComponent(componet);
		startActivity(intent);
	}

	/**
	 * @return 返回应用程序信息列表
	 */
	public ArrayList<HashMap<String, Object>> getApps() {
		PackageManager pm = this.getPackageManager(); // 获得PackageManager对象
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		// 通过查询，获得所有ResolveInfo对象.
		// List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent,
		// PackageManager.MATCH_DEFAULT_ONLY);
		List<ResolveInfo> resolveInfos = pm
				.queryIntentActivities(mainIntent, 0);
		// 调用系统排序 ， 根据name排序
		// 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
		Collections.sort(resolveInfos,
				new ResolveInfo.DisplayNameComparator(pm));
		// 生成动态数组，并且转入数据
		ArrayList<HashMap<String, Object>> apps = new ArrayList<HashMap<String, Object>>();

		for (ResolveInfo app : resolveInfos) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Name", app.activityInfo.name);// 获得该应用程序的启动Activity的name
			map.put("Icon", app.loadIcon(pm));// 获得应用程序图标
			map.put("Label", app.loadLabel(pm));// 获得应用程序的Label
			map.put("PackageName", app.activityInfo.packageName);// 获得应用程序的包名
			map.put("App", app);
			apps.add(map);
		}
		return apps;
	}
}
