package org.rw.phonebook.functions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class RestartEmailingService extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public RestartEmailingService() {
    }


    @Override
    public void init() throws ServletException {
        // EmailScheduler.resumeSchedule();
    }
}
