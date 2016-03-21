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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Master Chief
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = "";
            String password = "";
            String confirmPassword = "";
            String firstName = "";
            String lastName = "";
            User user;

            if (request.getParameter("btnSubmit") != null) {
                if ("".equals(request.getParameter("txtFirstName"))) {
                    out.println("<script>alert('Please enter an first name");
                } else if ("".equals(request.getParameter("txtLastName"))) {
                    out.println("<script>alert('Please enter a last name");
                } else if ("".equals(request.getParameter("txtEmail"))) {
                    out.println("<script>alert('Please enter an email");
                } else if ("".equals(request.getParameter("txtPassword"))) {
                    out.println("<script>alert('Please enter a password");
                } else if ("".equals(request.getParameter("txtPasswordConfirm"))) {
                    out.println("<script>alert('Please confirm password");
                } else if (!(request.getParameter("txtPassword").equals(request.getParameter("txtPasswordConfirm")))) {
                    out.println("<script>alert('Passwords do not match");
                } else {
                    email = request.getParameter("txtEmail");
                    password = request.getParameter("txtPassword");
                    firstName = request.getParameter("txtFirstName");
                    lastName = request.getParameter("txtLastName");

                    out.println("<script>alert('Registered Successfully')</script>");

                    user = new User(firstName, lastName, email, password);
                    response.sendRedirect("game.jsp");
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
