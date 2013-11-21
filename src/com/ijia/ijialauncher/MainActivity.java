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
		// 设置Activity标题不显示(必须放在setContentView前)
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏显示(必须放在setContentView前)
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);
		// //使得view 可见
		// ImageView.setVisibility(View.VISIBLE);
		// //使得view 不可见，但是占据他的位置
		// ImageView.setVisibility(View.INVISIBLE);
		// //使得view 不可见，同时不占据任何位置
		// ImageView.setVisibility(View.GONE);

		btnApps = (Button) findViewById(R.id.btnApps);

		btnApps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, Apps.class);
				startActivity(intent);
				// finish();//停止当前的Activity,如果不写,则按返回键会跳转回原来的Activity
			}
		});
	}
	private void  addWidget() {
		 
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO 自动生成的方法存根
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
			// 生成一个设置壁纸的请求
			final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
			Intent chooser = Intent.createChooser(pickWallpaper,
					"chooser_wallpaper");
			// 发送设置壁纸的请求
			startActivity(chooser);
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
