package org.rw.phonebook.functions;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;


public class EmailScheduler {
    public static SchedulerFactory schFactory = new StdSchedulerFactory();
    public static Scheduler sch;


    static {
        try {
            sch = schFactory.getScheduler();
            sch.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public EmailScheduler() {
    }


    // public static void resumeSchedule() {
    // try {
    // PreparedStatement pstmt = DBManager.conn
    // .prepareStatement("SELECT * FROM EMAIL_SCHEDULE");
    // ResultSet rset = pstmt.executeQuery();
    // while (rset.next()) {
    // Date sdate = rset.getDate("StartDate");
    // Date edate = rset.getDate("EndDate");
    // int sday = Integer.parseInt(sdate.toString().substring(0, 4));
    // int smon = Integer.parseInt(sdate.toString().substring(5, 7));
    // int syr = Integer.parseInt(sdate.toString().substring(8));
    // int eday = Integer.parseInt(edate.toString().substring(0, 4));
    // int emon = Integer.parseInt(edate.toString().substring(5, 7));
    // int eyr = Integer.parseInt(edate.toString().substring(8));
    // String stime = rset.getTime("StartTime").toString()
    // .substring(0, 5);
    // String etime = rset.getTime("EndTime").toString()
    // .substring(0, 5);
    // int minutes = rset.getInt("IntervalMin");
    // String days = rset.getString("Days");
    // int adminId = rset.getInt("AdminID");
    // int uid = rset.getInt("UserID");
    // schedule(sday, smon, syr, stime, eday, emon, eyr, etime,
    // minutes, days, adminId, uid);
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    public static JobDetail makeNewJob(int adminId, int uid) {
        JobDataMap jobData = new JobDataMap();
        jobData.put("adminId", String.valueOf(adminId));
        jobData.put("uid", String.valueOf(uid));
        JobDetail job = JobBuilder.newJob(JobMaker.class).setJobData(jobData).withIdentity(JobKey.jobKey(adminId + "-" + uid)).build();
        // .requestRecovery(true).build();
        return job;
    }

    public static void cancelSchedule(int adminId, int uid) {
        try {
            sch.deleteJob(JobKey.jobKey(adminId + "-" + uid));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void setSchedule(int sday, int smon, int syr, String stime, int eday, int emon, int eyr, String etime, int minutes, String days, int adminId, int uid) throws SchedulerException {
        // org.apache.log4j.BasicConfigurator.configure();

        int sHr = Integer.parseInt(stime.substring(0, 2));
        int sMin = Integer.parseInt(stime.substring(3));
        int eHr = Integer.parseInt(etime.substring(0, 2));
        int eMin = Integer.parseInt(etime.substring(3));

        Calendar scal = new GregorianCalendar(syr, smon - 1, sday);
        scal.set(Calendar.HOUR, sHr);
        scal.set(Calendar.MINUTE, sMin);

        Calendar ecal = new GregorianCalendar(eyr, emon - 1, eday);
        ecal.set(Calendar.HOUR, eHr);
        ecal.set(Calendar.MINUTE, eMin);

        // String today = new SimpleDateFormat("u").format(new Date());

        Set<Integer> selectedDays = new HashSet<>();
        for (int i = 0; i < days.length(); i++) {
            selectedDays.add(Integer.parseInt(days.charAt(i) + ""));
        }

        JobDetail job = makeNewJob(adminId, uid);
        Trigger trig = TriggerBuilder.newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey(adminId + "_" + uid)).startAt(scal.getTime()).withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .onDaysOfTheWeek(selectedDays).startingDailyAt(TimeOfDay.hourAndMinuteOfDay(sHr, sMin)).endingDailyAt(TimeOfDay.hourAndMinuteOfDay(eHr, eMin)).withIntervalInMinutes(minutes)).endAt(ecal.getTime()).build();

        if (sch.checkExists(JobKey.jobKey(adminId + "-" + uid))) {
            sch.rescheduleJob(TriggerKey.triggerKey(adminId + "_" + uid), trig);
        } else {
            sch.scheduleJob(job, trig);
        }
    }

    public static void reschedule(int sday, int smon, int syr, String stime, int eday, int emon, int eyr, String etime, int minutes, String days, int adminId, int uid) throws SchedulerException {
        int sHr = Integer.parseInt(stime.substring(0, 2));
        int sMin = Integer.parseInt(stime.substring(3));
        int eHr = Integer.parseInt(etime.substring(0, 2));
        int eMin = Integer.parseInt(etime.substring(3));

        Calendar scal = new GregorianCalendar(syr, smon - 1, sday);
        scal.set(Calendar.HOUR, sHr);
        scal.set(Calendar.MINUTE, sMin);

        Calendar ecal = new GregorianCalendar(eyr, emon - 1, eday);
        ecal.set(Calendar.HOUR, eHr);
        ecal.set(Calendar.MINUTE, eMin);

        // String today = new SimpleDateFormat("u").format(new Date());

        Set<Integer> selectedDays = new HashSet<>();
        for (int i = 0; i < days.length(); i++) {
            selectedDays.add(Integer.parseInt(days.charAt(i) + ""));
        }

        JobDetail job = makeNewJob(adminId, uid);
        Trigger trig = TriggerBuilder.newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey(adminId + "_" + uid)).startAt(scal.getTime()).withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .onDaysOfTheWeek(selectedDays).startingDailyAt(TimeOfDay.hourAndMinuteOfDay(sHr, sMin)).endingDailyAt(TimeOfDay.hourAndMinuteOfDay(eHr, eMin)).withIntervalInMinutes(minutes)).endAt(ecal.getTime()).build();
        sch.rescheduleJob(TriggerKey.triggerKey(adminId + "_" + uid), trig);
    }
}
