package org.rw.phonebook.util;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;


/**
 * The job scheduled previously by the scheduler is executed here based on some context and details about AdminID and UserID.
 *
 * @author Raafat
 */
public class JobExecutor implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        try {
            JobDataMap data = context.getJobDetail().getJobDataMap();
            long adminId = Long.parseLong(data.getString("adminId"));
            long uid = Long.parseLong(data.getString("uid"));

            String adminEmail = Utilities.getUserInfo(adminId).getString("email");
            if (Utilities.getUserInfo(uid) == null) {
                Utilities.sendNotificationEmail(adminEmail, uid);
                EmailScheduler.cancelSchedule(adminId, uid);
            }
            Utilities.sendUserContactsEmail(uid, adminEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
