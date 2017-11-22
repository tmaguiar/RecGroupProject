package group.recgroupproject.constant;

/**
 * SQL commands
 * Including select/delete/update/insert
 */
public abstract class SQLCommand
{


    //list all data in booking table
    public static String QUERY_1 = "select * from Booking";
    //List the call numbers of books with the title ‘Database Management’
    public static String QUERY_2 = "select lbcallnum from libbook where lbtitle = 'Database Management";
    public static String QUERY_3 = "select avg(COFine), max(COFine) from CheckOut";
    public static String QUERY_4 = "select sum(COFine) from CheckOut where CoReturned = 'Y'";
    public static String QUERY_5 = "select STName, LBCallNum from Student, CheckOut where Student.STID = CheckOut.STID Order By STName";
    public static String QUERY_6 = "select LBCallNum, Count(*) from CheckOut Group By LBCallNum";
    public static String QUERY_7 = "select CheckOut.STID, SUM(COFine) from Checkout Group by CheckOut.STID";
    public static String RETURN_BOOK = "update checkout set coreturned=? where stid=? and lbcallnum=?";
    public static String CHECK_BOOK = "insert into checkout(stid,lbcallnum,coduedate,coreturned) values(?,?,?,?)";
    //checkout summary
    public static String CHECKOUT_SUMMARY = "select strftime('%m',coduedate) as month,count(*) as total from checkout where strftime('%Y',coduedate)='2011' group by month order by total desc";
    public static String CHECKOUT_LIST = "select checkout.stid as _id, lbtitle, coduedate,coreturned,cofine,stname from checkout,student,libbook where student.stid=checkout.stid and libbook.lbcallnum=checkout.lbcallnum";
}

