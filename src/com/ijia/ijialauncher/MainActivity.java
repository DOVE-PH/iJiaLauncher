package com.ijia.ijialauncher;

import com.ijia.ijialauncher.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btnApps;

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

		btnApps = (Button) findViewById(R.id.btnApps);

		btnApps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, Apps.class);
				startActivity(intent);
				// finish();//ֹͣ��ǰ��Activity,�����д,�򰴷��ؼ�����ת��ԭ����Activity
			}
		});
	}
	private void  addWidget() {
		 
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO �Զ����ɵķ������
		super.onActivityResult(requestCode, resultCode, data);
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
