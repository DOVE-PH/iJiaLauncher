package com.ijia.ijialauncher;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MainActivity extends Activity implements OnClickListener {

	private Button btnApps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����Activity���ⲻ��ʾ(�������setContentViewǰ)
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����ȫ����ʾ(�������setContentViewǰ)
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);
		// //ʹ��view �ɼ�
		// ImageView.setVisibility(View.VISIBLE);
		// //ʹ��view ���ɼ�������ռ������λ��
		// ImageView.setVisibility(View.INVISIBLE);
		// //ʹ��view ���ɼ���ͬʱ��ռ���κ�λ��
		// ImageView.setVisibility(View.GONE);

		ViewGroup pnlBtns = (ViewGroup) findViewById(R.id.pnlBtns);
		for (int i = 0; i < pnlBtns.getChildCount(); i++) {
			View v = pnlBtns.getChildAt(i);
			if (v instanceof Button) {
				v.setOnClickListener(this);
				v.setOnCreateContextMenuListener(this);
			}
		}
	}

	@Override
	public void onClick(View v) {
		if (v instanceof Button) {
			Log.e("BTN",
					String.format("����ˣ�%s:%s", ((Button) v).getText(),
							v.isFocusable()));

			switch (v.getId()) {
			case R.id.btnTV:

				break;
			case R.id.btnXmHeZi:
				break;
			case R.id.btnMovie:
				break;
			case R.id.btnYouKu:
				break;
			case R.id.btnChild:
				break;
			case R.id.btnWeather:
				break;
			case R.id.btnDate:
				break;
			case R.id.btnApps: {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, Apps.class);
				startActivity(intent);
				// finish();//ֹͣ��ǰ��Activity,�����д,�򰴷��ؼ�����ת��ԭ����Activity
				break;
			}
			default:
				break;
			}
		}
	}

	Button btnCurrent;

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		btnCurrent = (Button) v;
		menu.setHeaderTitle("�༭" + btnCurrent.getText());
		getMenuInflater().inflate(R.menu.home_item, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Log.e("BTN", String.format("�����ѡ�%s,%s,%s", btnCurrent,
				item.getMenuInfo(), item.getActionView()));
		if (btnCurrent != null) {
			switch (item.getItemId()) {
			case R.id.mi_appitem_remove:

				break;
			default:
				return super.onContextItemSelected(item);
			}
		}
		return true;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mi_installApp:

			break;
		case R.id.mi_wallpaper:
			// ����һ�����ñ�ֽ������
			final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
			Intent chooser = Intent.createChooser(pickWallpaper,
					"chooser_wallpaper");
			// �������ñ�ֽ������
			startActivity(chooser);
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
