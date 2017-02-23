package com.xiaocheng.test;

import com.xiaocheng.usbhost.R;
import com.xiaocheng.usbhost.R.id;
import com.xiaocheng.usbhost.R.layout;
import com.xiaocheng.usbhost.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActivityC extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_c);
		
		Button buttonc = (Button)findViewById(R.id.buttonc1);
		buttonc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//实例化一个 intent 对象
				Intent data = new Intent();
				//获取 EditText 实例
				EditText editText = (EditText)findViewById(R.id.etActivityc);
				//得到 EditText 的值
				String val = editText.getText().toString();
				//将 EditText 的值存到 intent 对象中（以键值对的形式）
				data.putExtra("activity_c", val);
				//调用 setResult 方法，将 intent 对象（data）传回父 Activity
				setResult(Activity.RESULT_OK, data);
				//关闭当前 Activity
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_c, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
