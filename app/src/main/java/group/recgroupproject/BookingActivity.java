package group.recgroupproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import group.recgroupproject.constant.SQLCommand;
import group.recgroupproject.util.DBOperator;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener  {

    Button submitBtn,cancelBtn;
    Spinner orgspinner;
    EditText studID,count,descEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_activity);
        orgspinner=(Spinner)this.findViewById(R.id.orgspinner);

        submitBtn=(Button)this.findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(this);
        cancelBtn=(Button)this.findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(this);
        studID = (EditText) this.findViewById(R.id.bookingedit1);
        count = (EditText) this.findViewById(R.id.bookingedit2);
        descEdit = (EditText) this.findViewById(R.id.bookingedit3);
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }



    }

    public void onClick(View v) {
        String sql="";
        int id = v.getId();
        if (id == R.id.cancel_btn) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);

        }else if (id==R.id.submit_btn){
            int pos=orgspinner.getSelectedItemPosition();
            String selected = orgspinner.getItemAtPosition(pos).toString();

            if (pos== Spinner.INVALID_POSITION){
                //User doesn't choose any query, show warning
                Toast.makeText(this.getBaseContext(), "Please choose a location!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (pos==0){
                //show all books
                sql= SQLCommand.LOC_1;
            }else if (pos==1){
                sql=SQLCommand.LOC_2;
            }else if (pos==2){
                sql=SQLCommand.LOC_3;
            }else if (pos==3){
                sql=SQLCommand.LOC_4;
            }else if (pos==4){
                sql=SQLCommand.LOC_5;
            }
            DBOperator.getInstance().execQuery(sql,this.getArgs(true));
            Toast.makeText(getBaseContext(), "Booking successfully",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Get input data including studentID, book callnum, date and returned state
     *
     * @param isCheckout
     * @return
     */
    private String[] getArgs(boolean isCheckout) {
        String[] args = null;
        if (isCheckout) {
            args = new String[4];
            // stid

            args[0] = studID.getText().toString();
            // callnum
            args[1] = count.getText().toString();
            args[2] = descEdit.getText().toString();
            args[3] = studID.getText().toString();

        }
        return args;
    }


}
