package group.recgroupproject.constant;

/**
 * SQL commands
 * Including select/delete/update/insert
 */
public abstract class SQLCommand
{


    //list all data in booking table
    public static String QUERY_STUDENT = "select * from Student";
    //List the call numbers of books with the title ‘Database Management’
    public static String QUERY_LOCATION = "select location.loc_name as _id, loc_id,loc_name, loc_name,loc_desc, loc_desc from location";
    public static String QUERY_MYBOOKING = "select * from booking where booking.s_id = ?";
    public static String QUERY_1 = "select * from location";
    public static String QUERY_2 = "select location.loc_name, timing.time_start,timing.time_end from location, locationdetails, timing where loc_name='Alumni Stadium' and location.loc_id = locationdetails.loc_id and timing.time_id=locationdetails.time_id";
    public static String QUERY_3 = "select location.loc_name, timing.time_start,timing.time_end from location, locationdetails, timing where loc_name='Rooftop Field' and location.loc_id = locationdetails.loc_id and timing.time_id=locationdetails.time_id";
    public static String QUERY_4 = "select location.loc_name, timing.time_start,timing.time_end from location, locationdetails, timing where loc_name='Donahue Rowing Center' and location.loc_id = locationdetails.loc_id and timing.time_id=locationdetails.time_id";
    public static String QUERY_5 = "select location.loc_name, timing.time_start,timing.time_end from location, locationdetails, timing where loc_name='Squash Courts' and location.loc_id = locationdetails.loc_id and timing.time_id=locationdetails.time_id";
    public static String RETURN_BOOK = "update checkout set coreturned=? where stid=? and lbcallnum=?";
    public static String CHECK_BOOK = "insert into checkout(stid,lbcallnum,coduedate,coreturned) values(?,?,?,?)";
    //checkout summary
    public static String CHECKOUT_SUMMARY = "select strftime('%m',coduedate) as month,count(*) as total from checkout where strftime('%Y',coduedate)='2011' group by month order by total desc";
   // public static String CHECKOUT_LIST = "select checkout.stid as _id, lbtitle, coduedate,coreturned,cofine,stname from checkout,student,libbook where student.stid=checkout.stid and libbook.lbcallnum=checkout.lbcallnum";
}

