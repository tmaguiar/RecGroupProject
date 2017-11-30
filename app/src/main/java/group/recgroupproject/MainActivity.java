package group.recgroupproject;

import group.recgroupproject.constant.SQLCommand;
import group.recgroupproject.view.TableView;
import group.recgroupproject.util.DBOperator;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.view.View.OnClickListener;
import android.content.Intent;



public class MainActivity extends AppCompatActivity implements OnClickListener{

    Button bookingBtn,checkBtn,locationBtn, myBooking;

    /** Called when the activity is first created. */
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookingBtn=(Button)this.findViewById(R.id.booking_btn);
        bookingBtn.setOnClickListener(this);
        checkBtn=(Button)this.findViewById(R.id.Check_btn);
        checkBtn.setOnClickListener(this);
        locationBtn=(Button)this.findViewById(R.id.location_btn);
        locationBtn.setOnClickListener(this);
        myBooking=(Button)this.findViewById(R.id.myBooking_btn);
        myBooking.setOnClickListener(this);

        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public void onClick(View v)
    {
        int id=v.getId();
        if (id==R.id.booking_btn){
            Intent intent = new Intent(this, BookingActivity.class);
            this.startActivity(intent);

        }
        else if (id==R.id.Check_btn){
            Intent intent = new Intent(this, TimeActivity.class);
            this.startActivity(intent);
        }
        else if (id==R.id.location_btn){
            Intent intent = new Intent(this, LocationActivity.class);
            this.startActivity(intent);
        }
        else if (id==R.id.myBooking_btn){
            Intent intent = new Intent(this, MyBookingActivity.class);
            this.startActivity(intent);
        }
    }
}


