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
    public static String QUERY_MYBOOKING = "select booking.b_id as _id, b_id, event_desc, loc_name , time_start, time_end from booking, timing, location where booking.s_id = ? and booking.time_id = timing.time_id and booking.loc_id = location.loc_id";

    //AVAILABILITY
    public static String QUERY_1 = "select location.loc_id as loc_id ,loc_name , b_date, time_start,time_end from location,timing,booking where booking.loc_id=location.loc_id and booking.time_id=timing.time_id and loc_name='Harrington Auditorium'";
    public static String QUERY_2 = "select location.loc_id as loc_id ,loc_name , time_start,time_end from location,timing,booking where booking.loc_id=location.loc_id and booking.time_id=timing.time_id and loc_name='Alumni Stadium'";
    public static String QUERY_3 = "select location.loc_id as loc_id ,loc_name , time_start,time_end from location,timing,booking where booking.loc_id=location.loc_id and booking.time_id=timing.time_id and loc_name='Rooftop Field'";
    public static String QUERY_4 = "select location.loc_id as loc_id ,loc_name , time_start,time_end from location,timing,booking where booking.loc_id=location.loc_id and booking.time_id=timing.time_id and loc_name='Donahue Rowing Center'";
    public static String QUERY_5 = "select location.loc_id as loc_id ,loc_name , time_start,time_end from location,timing,booking where booking.loc_id=location.loc_id and booking.time_id=timing.time_id and loc_name='Squash Courts'";
    public static String RETURN_BOOK = "update checkout set coreturned=? where stid=? and lbcallnum=?";

    //BOOKING
    public static String LOC_1 = "insert into booking(b_id,b_participantcount,event_desc,s_id,o_id) values(?,?,?,?,1)";
    public static String LOC_2 = "insert into booking(b_id,b_participantcount,event_desc,s_id,o_id) values(?,?,?,?,2)";
    public static String LOC_3 = "insert into booking(b_id,b_participantcount,event_desc,s_id,o_id) values(?,?,?,?,3)";
    public static String LOC_4 = "insert into booking(b_id,b_participantcount,event_desc,s_id,o_id) values(?,?,?,?,4)";
    public static String LOC_5 = "insert into booking(b_id,b_participantcount,event_desc,s_id,o_id) values(?,?,?,?,5)";

    public static String BOOKING_SUMMARY = "select strftime('%m',b_id) as month,count(*) as total from booking where strftime('%Y',b_date)='2011' group by month order by total desc";

    //checkout summary
    public static String CHECKOUT_SUMMARY = "select strftime('%m',coduedate) as month,count(*) as total from checkout where strftime('%Y',coduedate)='2011' group by month order by total desc";
   // public static String CHECKOUT_LIST = "select checkout.stid as _id, lbtitle, coduedate,coreturned,cofine,stname from checkout,student,libbook where student.stid=checkout.stid and libbook.lbcallnum=checkout.lbcallnum";
}

