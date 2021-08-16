/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Question;
import model.Test;
import model.User;

/**
 *
 * @author Admin
 */
public class TakeTestController extends HttpServlet {

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
        try {
            String numberParam = request.getParameter("number");
            //get session user info
            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute("user");
            request.setAttribute("name", user.getUserName());
            QuestionDAO questionDao = new QuestionDAO();
            
            //when input of user invalid
            if (numberParam != null && numberParam.matches("\\d+")) {
                int amount = 0;
                try {
                    amount = Integer.parseInt(numberParam);
                    if (amount > questionDao.getTotalQuestions()) {
                        request.setAttribute("error", "The number of question must be smaller than "
                        + questionDao.getTotalQuestions());
                    }
                    //case invalid amount
                    else if (amount <= 0) {
                        request.setAttribute("error", "The number of question must be bigger than 0");
                    } else {
                        //Before create new test, remove all test having in session of this user
                        session.removeAttribute("test");
                        List<Question> listQuestions = questionDao.getRandomQuestionList(amount);
                        //get time now to set begin time
                        long beginTime = new Date().getTime();
                        long endTime = beginTime + (10 * 1000 * amount);
                        //create new test
                        Test test = new Test(user, endTime, listQuestions);
                        //write in session
                        session.setAttribute("test", test);
                        //send to serverlet get question
                        response.sendRedirect("test?question=1");
                        return;
                    }
                } catch (Exception e) {
                    request.setAttribute("error", "The number of question must be a number and bigger than 0");
                }
                //Case input of user not null but not invalid (must be a number > 0)
            } else if (numberParam != null && !numberParam.matches("\\d+")) {
                request.setAttribute("error", "The number of question must be a number and bigger 0");
            }
            RequestDispatcher rd = request.getRequestDispatcher("takeQuiz.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            request.setAttribute("error", "There's some problem. Please try again later");
            rd.forward(request, response);
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
