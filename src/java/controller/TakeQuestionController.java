/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

/**
 *
 * @author Admin
 */
public class TakeQuestionController extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            Test testSession = (Test) session.getAttribute("test");

            //time remaining =time end - time now
            long now = new Date().getTime();
            long timeRemain = testSession.getEndTime() - now;

            // Time out
            if (timeRemain <= 0) {
                response.sendRedirect("result");
                return;
            }
            int questionNumber;
            // If question is not number, default to question number 1
            try {
                questionNumber = Integer.parseInt(request.getParameter("question"));
            } catch (NumberFormatException ex) {
                questionNumber = 1;
            }
            // If question number require bigger than having in session, go to question 1
            int totalQuestionInSession = testSession.getListQuestion().size();
            if (questionNumber > totalQuestionInSession) {
                response.sendRedirect("test?question=1");
                return;
            }
            // Get question with number which request by user 
            Question question = testSession.getListQuestion().get(questionNumber - 1);
            request.setAttribute("name", testSession.getUser().getUserName());
            request.setAttribute("number", questionNumber);
            request.setAttribute("remain", timeRemain);
            request.setAttribute("chosen", question.getUserChoice());
            request.setAttribute("content", question.getQuestionContent());
            request.setAttribute("options", question.getOptionsList());

            // If question get is a last question in session, add finish option for user to finish all 
            if (questionNumber == totalQuestionInSession) {
                request.setAttribute("isLastQuest", true);
            }
            RequestDispatcher rd = request.getRequestDispatcher("question.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            // Go to error page
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            request.setAttribute("error", "There's some problem. Please try again later");
            rd.forward(request, response);
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
            HttpSession session = request.getSession(true);

            // Check session. If session dont have test, 
            if (session.getAttribute("test") == null) {
                response.sendRedirect("take");
                return;
            }
            int questionNumber;
            Test testSession = (Test) session.getAttribute("test");
            String questionParam = request.getParameter("question");
            int totalQuestionInSession = testSession.getListQuestion().size();
            if (questionParam == null || !questionParam.matches("\\d+")
                    || Integer.valueOf(questionParam) > totalQuestionInSession) {
                response.sendRedirect("test?question=1");
                return;
            }
            questionNumber = Integer.valueOf(questionParam);
            // Get answer from user
            String checkOption1 = request.getParameter("option1");
            String checkOption2 = request.getParameter("option2");
            String checkOption3 = request.getParameter("option3");
            String checkOption4 = request.getParameter("option4");
            // Create a list answers of user
            List<Integer> userChoice = new ArrayList<>();
            // check checkbox
            if (checkOption1 != null) {
                userChoice.add(1);
            }
            if (checkOption2 != null) {
                userChoice.add(2);
            }
            if (checkOption3 != null) {
                userChoice.add(3);
            }
            if (checkOption4 != null) {
                userChoice.add(4);
            }
            // Write answer of user to session
            testSession.getListQuestion().get(questionNumber - 1).setUserChoice(userChoice);
            // Go to next question
            int nextQuestion = questionNumber + 1;
            if(request.getParameter("isEnd").isEmpty())
                response.sendRedirect("test?question=" + nextQuestion);
            else response.sendRedirect("result");
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            request.setAttribute("error", "There's some problem. Please try again later");
            rd.forward(request, response);
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
