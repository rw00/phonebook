package org.rw.phonebook.util;

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
    public static final SchedulerFactory SCHEDULER_FACTORY = new StdSchedulerFactory();
    private static Scheduler scheduler;

    static {
        try {
            scheduler = SCHEDULER_FACTORY.getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private EmailScheduler() {
    }

    public static void cancelSchedule(long adminId, long uid) {
        try {
            scheduler.deleteJob(JobKey.jobKey(adminId + "-" + uid));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void setSchedule(int sday, int smon, int syr, String stime, int eday, int emon, int eyr, String etime, int minutes, String days, long adminId, long uid) throws SchedulerException {
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

        Set<Integer> selectedDays = new HashSet<>();
        for (int i = 0; i < days.length(); i++) {
            selectedDays.add(Integer.parseInt(days.charAt(i) + ""));
        }

        JobDetail job = createNewJob(adminId, uid);
        Trigger trig = TriggerBuilder.newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey(adminId + "_" + uid)).startAt(scal.getTime()).withSchedule(
            DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().onDaysOfTheWeek(selectedDays).startingDailyAt(TimeOfDay.hourAndMinuteOfDay(sHr, sMin)).endingDailyAt(TimeOfDay.hourAndMinuteOfDay(eHr, eMin))
                                            .withIntervalInMinutes(minutes)).endAt(ecal.getTime()).build();

        if (scheduler.checkExists(JobKey.jobKey(adminId + "-" + uid))) {
            scheduler.rescheduleJob(TriggerKey.triggerKey(adminId + "_" + uid), trig);
        } else {
            scheduler.scheduleJob(job, trig);
        }
    }

    public static JobDetail createNewJob(long adminId, long uid) {
        JobDataMap jobData = new JobDataMap();
        jobData.put("adminId", String.valueOf(adminId));
        jobData.put("uid", String.valueOf(uid));
        return JobBuilder.newJob(JobExecutor.class).setJobData(jobData).withIdentity(JobKey.jobKey(adminId + "-" + uid)).build();
    }

    public static void reschedule(int sday, int smon, int syr, String stime, int eday, int emon, int eyr, String etime, int minutes, String days, long adminId, long uid) throws SchedulerException {
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

        Set<Integer> selectedDays = new HashSet<>();
        for (int i = 0; i < days.length(); i++) {
            selectedDays.add(Integer.parseInt(days.charAt(i) + ""));
        }

        JobDetail job = createNewJob(adminId, uid);
        Trigger trig = TriggerBuilder.newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey(adminId + "_" + uid)).startAt(scal.getTime()).withSchedule(
            DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().onDaysOfTheWeek(selectedDays).startingDailyAt(TimeOfDay.hourAndMinuteOfDay(sHr, sMin)).endingDailyAt(TimeOfDay.hourAndMinuteOfDay(eHr, eMin))
                                            .withIntervalInMinutes(minutes)).endAt(ecal.getTime()).build();
        scheduler.rescheduleJob(TriggerKey.triggerKey(adminId + "_" + uid), trig);
    }
}
