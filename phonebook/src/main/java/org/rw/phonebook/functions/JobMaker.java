package org.rw.phonebook.functions;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * The job scheduled previously by the scheduler is executed here based on some context and details about AdminID and
 * UserID.
 *
 * @author Raafat
 */
public class JobMaker implements Job {
    public JobMaker() {

    }


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap data = context.getJobDetail().getJobDataMap();
            int adminId = Integer.parseInt(data.getString("adminId"));
            int uid = Integer.parseInt(data.getString("uid"));

            String adminEmail = MainFunctions.getUserInfo(adminId).getString("email");
            // String text = "";
            // String subject = "";
            if (MainFunctions.getUserInfo(uid) == null) {
                // text = "<html><p>Dear Admin,</p><p>The user with ID# "
                // + uid
                // + " has deleted his account.</p>"
                // + "<br>This will be the last email you receive."
                // +
                // "<br><br><p><small><em>This message is sent by automatic system. "
                // + "If it was sent to you by mistake, "
                // +
                // "delete it immediately and report to raafatwahb@gmail.com</em></small></p>";
                // subject = "Notification About User With ID# " + uid;
                MainFunctions.sendNotificationEmail(adminEmail, uid);
                EmailScheduler.cancelSchedule(adminId, uid);
            } else {
                // text = MainFunctions.getContactsDetails(uid);
                // subject = "Contact List for "
                // + MainFunctions.getUserInfo(uid).getString("email");
            }
            MainFunctions.sendUserContactsEmail(uid, adminEmail);
            // GMailer.send(adminEmail, subject, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
