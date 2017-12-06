package group.recgroupproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.View;
import android.support.v4.app.DialogFragment;

import java.util.LinkedList;
import java.util.List;

import group.recgroupproject.constant.SQLCommand;
import group.recgroupproject.util.DBOperator;
import group.recgroupproject.util.Pair;
import group.recgroupproject.view.TableView;
import group.recgroupproject.view.ChartGenerator;

public class MyBookingActivity extends AppCompatActivity implements View.OnClickListener {
    EditText stud_id;
    Button submit1,cancel1, summary_btn;
    private ListView mybookView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybooking_activity);


        submit1=(Button)this.findViewById(R.id.submit1_btn);
        submit1.setOnClickListener(this);
        cancel1=(Button)this.findViewById(R.id.cancel1_btn);
        cancel1.setOnClickListener(this);
        stud_id = (EditText) this.findViewById(R.id.mybookingedit1);

        mybookView = (ListView) this.findViewById(R.id.mybook_listview);
        mybookView.setOnItemClickListener(new MyBookingActivity.ItemClickListener());

        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.submit1_btn) {
            Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_MYBOOKING, new String[]{stud_id.getText().toString()});

            //if invalid student
            //cursor.moveToFirst();
           // Log.i(cursor.getString(0), "STUDENTID");
            if (cursor.getCount() <= 0) {
                String data1 = "no such id";
                Log.i(data1, "WRONG ID");

              AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("INVALID STUDENT ID");
               // dlgAlert.setTitle("App Title");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                Toast.makeText(getApplicationContext(), "INVALID STUDENT ID", Toast.LENGTH_SHORT).show();
            } else {
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                        getApplicationContext(), R.layout.mybooking_list, cursor,
                        new String[]{"b_id", "event_desc", "loc_name", "time_start", "time_end"}, new int[]{
                        R.id.b_id, R.id.event_desc, R.id.loc_name, R.id.time_start, R.id.time_end},
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
                mybookView.setAdapter(adapter);
            }
               // TableView tableView = new TableView(this, cursor);
                //show data in tableview

               // ScrollView scrollView = (ScrollView) this.findViewById(R.id.scrollView1);
               // scrollView.addView(scrollView1);

            }

        else if (id == R.id.cancel1_btn) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }

        /*else if (id == R.id.summary_btn){
            // show summary chart
            Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.BOOKING_SUMMARY);
            List<Pair> pairList = new LinkedList<Pair>();
            for (int i = 1; i <= 12; i++) {
                Pair pair = new Pair(i, 0);
                pairList.add(pair);
            }
            while (cursor.moveToNext()) {
                int location = Integer.parseInt(cursor.getString(0));
                pairList.get(location - 1).setNumber(
                        Double.parseDouble(cursor.getString(1)));
            }
            Intent intent = ChartGenerator.getBarChart(getBaseContext(),
                    "Booking Summary", pairList);
            this.startActivity(intent);

        } */
    }


    private class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) mybookView.getItemAtPosition(position);
            String b_id = cursor.getString(0);
            String event_desc = cursor.getString(1);
            String loc_name = cursor.getString(2);
            String time_start = cursor.getString(2);
            String time_end = cursor.getString(2);

            Toast.makeText(getApplicationContext(), "Booking ID: " + b_id + "\nEvent: " + event_desc + "\nLocation: " + loc_name + "\nStart Time: " + time_start + "\nEnd Time: " + time_end, Toast.LENGTH_LONG).show();
        }
    }


}
