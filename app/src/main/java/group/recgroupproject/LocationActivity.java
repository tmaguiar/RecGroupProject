package group.recgroupproject;

import group.recgroupproject.constant.SQLCommand;
import group.recgroupproject.view.TableView;
import group.recgroupproject.util.DBOperator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class LocationActivity extends Activity {
    private ListView listView;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity);

        listView = (ListView) this.findViewById(R.id.location_listview);
        listView.setOnItemClickListener(new ItemClickListener());

        // get the sql string delivered from the QueryActivity
        Intent intent = this.getIntent();
        String sql = intent.getStringExtra("sql");
        // execute the sql
        Cursor cursor = DBOperator.getInstance().execQuery(sql);
        // bind the data to list
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.listitem_location, cursor,
                new String[] { "loc_id", "loc_name", "loc_desc" }, new int[] {
                R.id.loc_id, R.id.loc_name, R.id.loc_desc },
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String loc_id = cursor.getString(0);
            String loc_name = cursor.getString(1);
            String loc_desc = cursor.getString(2);
            Toast.makeText(getApplicationContext(),"Location ID: " + loc_id + "\nLocation Name: " + loc_name+ "\nLocation Description: " + loc_desc, Toast.LENGTH_LONG).show();
        }
    }
}
