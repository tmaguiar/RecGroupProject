package group.recgroupproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import group.recgroupproject.constant.SQLCommand;
import group.recgroupproject.util.DBOperator;
import group.recgroupproject.view.TableView;

public class TimeActivity  extends AppCompatActivity implements View.OnClickListener {
    /** Called when the activity is first created. */

    Button goback_btn;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_activity);
        goback_btn=(Button)this.findViewById(R.id.goback_btn);
        goback_btn.setOnClickListener(this);
        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.goback_btn) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }
}