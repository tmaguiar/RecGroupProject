package group.recgroupproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import group.recgroupproject.util.DBOperator;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener  {

    Button submitBtn,cancelBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_activity);
        submitBtn=(Button)this.findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(this);
        cancelBtn=(Button)this.findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(this);
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.cancel_btn) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);

        }
    }
}
