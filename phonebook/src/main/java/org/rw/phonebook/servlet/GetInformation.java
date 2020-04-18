package org.rw.phonebook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GetInformation extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public GetInformation() {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String newAccountPhoneNumber = (String) session.getAttribute("newAccount");
        session.setAttribute("newAccount", null);
        response.setContentType("text/plain");
        //response.setCharacterEncoding("UTF-8");
        if (newAccountPhoneNumber == null) {
            response.getWriter().write("");
        } else {
            response.getWriter().write(newAccountPhoneNumber);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
