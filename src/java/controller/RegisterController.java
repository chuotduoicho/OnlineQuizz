/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
        try {
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            // Go to error page
            request.setAttribute("error", "There's some problem. Please try again later");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
        try {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String email = request.getParameter("email");
            String mailRegex = "^[A-Za-z0-9_.-]+@[A-Za-z0-9.]+[A-Za-z0-9]$"; //regex email ex dung_aa@gmail.com
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            
            if (!email.matches(mailRegex)) {// check email format
                request.setAttribute("error", "Invalid email! Choose another email please");
            }
            //Check username, password can not be blank, check role
            else if (userName == null || password == null || (!role.equals("student") && !role.equals("teacher"))) {
                request.setAttribute("error", "The fields you entered are not valid");
            }
            else {
                UserDAO dao = new UserDAO();
                
                // Check user name exist. If user name already exists, go back register form with error noti
                if (dao.checkUserNameExist(userName)) {
                    request.setAttribute("error", "This user name already in use. Please choose another name");
                } else {
                    int userRole;
                    // teacher = 1, student = 0
                    if (role.equals("student")) {
                        userRole = 0;
                    } else {
                        userRole = 1;
                    }
                    dao.registerNew(userName, password, email, userRole);
                    
                    request.setAttribute("success", "Register successful. Now click to the link below to login.");
                    request.getRequestDispatcher("home").forward(request, response);
                    return;
                }
            }
            // invalid input
            // value of all field except password
            request.setAttribute("username", userName);
            request.setAttribute("email", email);
            if (role.equals("student")) {
                request.setAttribute("isStudent", true);
            }
            rd.forward(request, response);   
            
        } catch (Exception e) {
            // Go to error page
            request.setAttribute("error", "There's some problem. Please try again later");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
