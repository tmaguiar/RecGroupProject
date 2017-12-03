package group.recgroupproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybooking_activity);
        submit1=(Button)this.findViewById(R.id.submit1_btn);
        submit1.setOnClickListener(this);
        cancel1=(Button)this.findViewById(R.id.cancel1_btn);
        cancel1.setOnClickListener(this);
        stud_id = (EditText) this.findViewById(R.id.mybookingedit1);
        summary_btn=(Button)this.findViewById(R.id.summary_btn);
        summary_btn.setOnClickListener(this);

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

            Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_MYBOOKING,new String[]{stud_id.getText().toString()});
            TableView tableView = new TableView(this, cursor);
            //show data in tableview
            ScrollView scrollView = (ScrollView) this.findViewById(R.id.scrollView1);
            scrollView.addView(tableView);

        } else if (id == R.id.cancel1_btn) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }else if (id == R.id.summary_btn){
            // show summary chart
            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.BOOKING_SUMMARY);
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

        }
    }
}
