package org.rw.phonebook.util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServlet;


public class RestartEmailingService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static void resumeSchedule() {
        try {
            PreparedStatement pstmt = null; // DBManager.conn.prepareStatement("SELECT * FROM EMAIL_SCHEDULE")
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                Date sdate = rset.getDate("StartDate");
                Date edate = rset.getDate("EndDate");
                int sday = Integer.parseInt(sdate.toString().substring(0, 4));
                int smon = Integer.parseInt(sdate.toString().substring(5, 7));
                int syr = Integer.parseInt(sdate.toString().substring(8));
                int eday = Integer.parseInt(edate.toString().substring(0, 4));
                int emon = Integer.parseInt(edate.toString().substring(5, 7));
                int eyr = Integer.parseInt(edate.toString().substring(8));
                String stime = rset.getTime("StartTime").toString().substring(0, 5);
                String etime = rset.getTime("EndTime").toString().substring(0, 5);
                int minutes = rset.getInt("IntervalMin");
                String days = rset.getString("Days");
                long adminId = rset.getLong("AdminID");
                long uid = rset.getLong("UserID");
                // schedule(sday, smon, syr, stime, eday, emon, eyr, etime, minutes, days, adminId, uid)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        // EmailScheduler.resumeSchedule()
    }
}
