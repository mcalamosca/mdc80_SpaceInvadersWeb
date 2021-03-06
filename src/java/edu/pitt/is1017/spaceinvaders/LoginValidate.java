/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.is1017.spaceinvaders;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Master Chief
 */
@WebServlet(name = "LoginValidate", urlPatterns = {"/LoginValidate"})
public class LoginValidate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String userName = "";
    private String password = "";
    User user = null;
    int userID;
    String lastName;
    String firstName;
    String email;

    public LoginValidate() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("btnSubmit") != null) {
                if (request.getParameter("txtUserName") != null) {
                    if (request.getParameter("txtUserName") != "") {
                        userName = request.getParameter("txtUserName");
                    }
                }

                if (request.getParameter("txtPassword") != null) {
                    if (request.getParameter("txtPassword") != "") {
                        password = request.getParameter("txtPassword");
                    }
                }

                if (!userName.equals("") && !password.equals("")) {
                    user = new User(userName, password);

                    if (user.isLoggedIn()) {
                        userID = user.getUserID();
                        lastName = user.getLastName();
                        firstName = user.getFirstName();
                        email = user.getEmail();

                        HttpSession session = request.getSession(true);
                        session.setAttribute("userID", userID);
                        session.setAttribute("lastName", lastName);
                        session.setAttribute("firstName", firstName);
                        session.setAttribute("email", email);
                        
                        response.sendRedirect("game2.jsp");

                        //out.println("<script>alert('Successful Login');top.window.location='game2.jsp';</script>");
                    } else {
                        out.println("<script>alert('Username and/or password is incorrect');top.window.location='index.jsp';</script>");
                    }

                } else {
                    out.println("<script>alert('You must enter both User Name and Password')</script>");
                }
            }
        }

    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
