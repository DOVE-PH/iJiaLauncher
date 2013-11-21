package com.ijia.ijialauncher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
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
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apps);
		setTitle("Ӧ�ó����б�");
		// ���ɶ�̬���飬����ת������
		ArrayList<HashMap<String, Object>> lstImageItem = getApps();

		AppInfoAdapter appAdapter = new AppInfoAdapter(this, lstImageItem);

		GridView gridview = (GridView) findViewById(R.id.gvAppList); // ��Ӳ�����ʾ
		gridview.setAdapter(appAdapter);
		gridview.setOnItemClickListener(this);

		// Ϊ�����б���ע�������Ĳ˵�
		registerForContextMenu(gridview);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.app_item, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// �õ���ǰ��ѡ�е�item��Ϣ
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> values = (HashMap<String, Object>) menuInfo.targetView
				.getTag();
		switch (item.getItemId()) {
		case R.id.mi_appitem_remove:
		// Alert(this, "ȷ��", "ȷ��Ҫж��" + values.get("Label").toString() + "��");
		{
			Uri packageURI = Uri.parse("package:"
					+ values.get("PackageName").toString());
			// ����Intent��ͼ
			Intent intent = new Intent(Intent.ACTION_DELETE, packageURI);
			// ִ��ж�س���
			startActivity(intent);
		}
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
		alert.setNegativeButton("ȷ��", null);
		alert.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO �Զ����ɵķ������
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * @param parent
	 *            �������������AdapterView��
	 * @param view
	 *            ��AdapterView�б��������ͼ(������adapter�ṩ��һ����ͼ)��
	 * @param position
	 *            ��ͼ��adapter�е�λ�á�
	 * @param id
	 *            �����Ԫ�ص���id��
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// HashMap<String, Object> item = (HashMap<String, Object>)
		// parent.getItemAtPosition(position);//���ַ�ʽҲ����
		@SuppressWarnings("unchecked")
		HashMap<String, Object> item = (HashMap<String, Object>) view.getTag();

		ComponentName componet = new ComponentName(item.get("PackageName")
				.toString(), item.get("Name").toString());
		Intent intent = new Intent();
		intent.setComponent(componet);
		startActivity(intent);
	}

	/**
	 * @return ����Ӧ�ó�����Ϣ�б�
	 */
	public ArrayList<HashMap<String, Object>> getApps() {
		PackageManager pm = this.getPackageManager(); // ���PackageManager����
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		// ͨ����ѯ���������ResolveInfo����.
		// List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent,
		// PackageManager.MATCH_DEFAULT_ONLY);
		List<ResolveInfo> resolveInfos = pm
				.queryIntentActivities(mainIntent, 0);
		// ����ϵͳ���� �� ����name����
		// ���������Ҫ������ֻ����ʾϵͳӦ�ã��������г�������Ӧ�ó���
		Collections.sort(resolveInfos,
				new ResolveInfo.DisplayNameComparator(pm));
		// ���ɶ�̬���飬����ת������
		ArrayList<HashMap<String, Object>> apps = new ArrayList<HashMap<String, Object>>();

		for (ResolveInfo app : resolveInfos) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Name", app.activityInfo.name);// ��ø�Ӧ�ó��������Activity��name
			map.put("Icon", app.loadIcon(pm));// ���Ӧ�ó���ͼ��
			map.put("Label", app.loadLabel(pm));// ���Ӧ�ó����Label
			map.put("PackageName", app.activityInfo.packageName);// ���Ӧ�ó���İ���
			map.put("App", app);
			apps.add(map);
		}
		return apps;
	}
}
