package com.xiaocheng.usbhost;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferencesActivity extends Activity implements OnSharedPreferenceChangeListener{
	
	private EditText text;
	private Button btnAdd;
	private Button btnDelete;
	private Button btnSearch;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_preferences);
		
		text = (EditText)findViewById(R.id.shareET);//获取编辑框实例
		btnAdd = (Button)findViewById(R.id.shareBtnAdd);
		btnDelete = (Button)findViewById(R.id.shareBtnDelete);
		btnSearch = (Button)findViewById(R.id.shareBtnSearch);
		
		sp = this.getSharedPreferences("share_demo", MODE_PRIVATE);
		
		sp.registerOnSharedPreferenceChangeListener(this);//监听sharePreferrences数据变化
		
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editor et = sp.edit();
				
				et.putString("name", text.getText().toString());
				
				et.commit();
				
				//进行提交成功的提示
				Toast.makeText(SharedPreferencesActivity.this, "恭喜，添加成功！", Toast.LENGTH_LONG).show();
			}
		});
		
		btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String value = sp.getString("name", "");
				
				text.setText(value);
			}
		});
		
		
	}
	
	

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		sp.unregisterOnSharedPreferenceChangeListener(this);//注销监听sharePreferrences数据变化
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shared_preferences, menu);
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

	/**
	 * 实现OnSharedPreferenceChangeListener接口的方法，做数据监听
	 */
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		Editor et = sharedPreferences.edit();
		
		et.putString("name", text.getText().toString());
		
		et.commit();
	}
}
