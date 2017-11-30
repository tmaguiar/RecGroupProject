package group.recgroupproject;

        import group.recgroupproject.constant.SQLCommand;
        import group.recgroupproject.view.TableView;
        import group.recgroupproject.util.DBOperator;
        import android.app.Activity;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.widget.ScrollView;

public class LocationActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity);
        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        //implement SQL query and get cursor of resultset
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_LOCATION);
        TableView tableView = new TableView(this, cursor);
        //show data in tableview
        ScrollView scrollView = (ScrollView)this.findViewById(R.id.scrollView);
        scrollView.addView(tableView);
    }
}


