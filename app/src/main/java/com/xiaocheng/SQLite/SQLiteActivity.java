package com.xiaocheng.SQLite;

import com.xiaocheng.usbhost.R;
import com.xiaocheng.usbhost.SharedPreferencesActivity;
import com.xiaocheng.usbhost.R.id;
import com.xiaocheng.usbhost.R.layout;
import com.xiaocheng.usbhost.R.menu;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SQLiteActivity extends Activity {
	private MySQLiteHelper mySQLiteHelper;
	private SQLiteDatabase db;
	
	private  TextView SQLiteQuery;
	private Button sqliteBtn;
	private Button sqliteBtnAdd;
	private Button sqliteBtnDelete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		
		mySQLiteHelper = new MySQLiteHelper(this, "mydatabase.db", null, 1);
		
		db = mySQLiteHelper.getReadableDatabase();
		
		sqliteBtn = (Button)findViewById(R.id.sqliteBtn);
		sqliteBtnAdd = (Button)findViewById(R.id.sqliteBtnAdd);
		sqliteBtnDelete = (Button)findViewById(R.id.sqliteBtnDelete);
		
		sqliteBtnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//插入一条数据的两种方式
				//1
				String insert="insert into mytable(name, age) values(\"小楠\" ,28)";
				db.execSQL(insert);
				
				//2
//				ContentValues cv=new ContentValues();
//				cv.put("_id",1);
//				cv.put("name"," 李楠");
//				cv.put("age",35);
//				db.insert("mytable",null,cv);
			}
		});
		
		//查询全表数据
		sqliteBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//查询数据
				Cursor cursor =db.query("mytable", new String[]{"_id","name","age"}, null, null, null,null, null);
				String result = "";
				while (cursor.moveToNext()) {
					int idindex = cursor.getColumnIndex("_id");
					int id = cursor.getInt(idindex);
					int nameindex = cursor.getColumnIndex("name");
					String name = cursor.getString(nameindex);
					int ageindex = cursor.getColumnIndex("age");
					int age = cursor.getInt(ageindex);
					result = result +  id + " " + name + " " + age + " \n";
				}
				SQLiteQuery = (TextView)findViewById(R.id.SQLiteQuery);
				
				SQLiteQuery.setText(result);
				
				//进行提交成功的提示
//				Toast.makeText(SQLiteActivity.this, result, Toast.LENGTH_LONG).show();
			}
		});
		
		//删除键  用于清空所有数据
		sqliteBtnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.delete("mytable", "_id = ?", new String[]{"1"});
			}
		});
		
		db.beginTransaction();
		try {
			
			
			db.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			db.endTransaction();
		}
		
	}

	
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		db.close();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite, menu);
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
