package com.xiaocheng.test;

import com.xiaocheng.usbhost.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * @author XCCD
 * <li>TODO	
 * <li>2017-2-20 下午2:15:33
 * <li>
 */
public class ActivityA extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		
		//得到两个 Button 控件
		Button mButton1 = (Button)findViewById(R.id.button1);
		Button mButton2 = (Button)findViewById(R.id.button2);
		Button mButton3 = (Button)findViewById(R.id.button3);
		mButton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent _intent = new Intent(ActivityA.this, ActivityB.class);
				startActivity(_intent);
			}
		});
		
		mButton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _intent = new Intent(ActivityA.this, ActivityC.class);
				startActivityForResult(_intent, 100);
			}
		});
		
		mButton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent 主信息块的例子演示代码：
				Intent intent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("content://contacts/people/2"));
				startActivity(intent);
			
				//Intent 次要信息块的例子演示代码：
//				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//				intent.setType("vnd.android.cursor.item/phone");
//				startActivity(intent);
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == 100 && resultCode == Activity.RESULT_OK){
			String val = data.getExtras().getString("activity_c");//获取返回的数据键值对
			TextView textView = (TextView)findViewById(R.id.tvDisplay);
			textView.setText("来自 ActivityC 的值 ："+ val);
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity, menu);
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
