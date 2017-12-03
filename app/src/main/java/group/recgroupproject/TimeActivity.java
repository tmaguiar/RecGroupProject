package group.recgroupproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import group.recgroupproject.constant.SQLCommand;
import group.recgroupproject.util.DBOperator;
import group.recgroupproject.view.TableView;

public class TimeActivity  extends AppCompatActivity implements View.OnClickListener {
    /** Called when the activity is first created. */

    Button goback_btn, result_btn;
    Spinner location_spinner;
    ScrollView scrollview_loc;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_activity);
        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        goback_btn=(Button)this.findViewById(R.id.goback_btn);
        goback_btn.setOnClickListener(this);
        result_btn=(Button)this.findViewById(R.id.result_btn);
        result_btn.setOnClickListener(this);
        location_spinner=(Spinner)this.findViewById(R.id.location_spinner);
        scrollview_loc=(ScrollView) this.findViewById(R.id.scrollview_loc);
    }

    public void onClick(View v)
    {
        String sql="";
        int id=v.getId();
        if (id==R.id.result_btn){
            //show query result
            int pos=location_spinner.getSelectedItemPosition();
            if (pos==Spinner.INVALID_POSITION){
                //User doesn't choose any query, show warning
                Toast.makeText(this.getBaseContext(), "Please choose a location!", Toast.LENGTH_SHORT).show();
                return;
            }
            scrollview_loc.removeAllViews();
            if (pos==0){
                //show all books
                sql=SQLCommand.QUERY_1;
            }else if (pos==1){
                sql=SQLCommand.QUERY_2;
            }else if (pos==2){
                sql=SQLCommand.QUERY_3;
            }else if (pos==3){
                sql=SQLCommand.QUERY_4;
            }else if (pos==4){
                sql=SQLCommand.QUERY_5;
            }

            Cursor cursor=DBOperator.getInstance().execQuery(sql);
            scrollview_loc.addView(new TableView(this.getBaseContext(),cursor));
        }else if (id == R.id.goback_btn) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }


    }
}
