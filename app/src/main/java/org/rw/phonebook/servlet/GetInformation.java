package org.rw.phonebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GetInformation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String newAccountPhoneNumber = (String) session.getAttribute("newAccount");
        session.setAttribute("newAccount", null);
        response.setContentType("text/plain");
        PrintWriter responseWriter = response.getWriter();
        if (newAccountPhoneNumber == null) {
            responseWriter.write("");
        } else {
            responseWriter.write(newAccountPhoneNumber);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
