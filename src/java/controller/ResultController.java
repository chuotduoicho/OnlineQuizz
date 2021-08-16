/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TestDAO;
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

/**
 *
 * @author Admin
 */
public class ResultController extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            TestDAO test = new TestDAO();
            Test testSession = (Test) session.getAttribute("test");
            int totalQuestion = testSession.getListQuestion().size();
            //Check time
            long now = new Date().getTime();
            long takeTime = now - testSession.getEndTime();
            // 5 seconds to send
            if (takeTime > 5 * 1000) {
                test.recordResultTest(testSession.getUser().getUserName(), 0);
                request.setAttribute("score", "Over Time. Not passed");
            } else {
                // Calculate score
                int totalTrue = 0;
                List<Question> listQuestion = testSession.getListQuestion();
                for (Question question : listQuestion) {
                    // case answer in db == answer chosen
                    if (question.getAnswersList().equals(question.getUserChoice())) {
                        ++totalTrue;
                    }
                }
                //0.33333333 --> (33) /10 -->3.3 (round float number)
                float score = (float) Math.round((totalTrue*1.0 / totalQuestion) * 100) / 10;
                //Write in database
                test.recordResultTest(testSession.getUser().getUserName(), score);
                //Classify
                if (score >= 5) {
                    request.setAttribute("score", "" + score + " (" + (int) (score * 10) + "%) - Passed");
                    request.setAttribute("pass", true);
                } else {
                    request.setAttribute("score", "" + score + " (" + (int) (score * 10) + "%) - Not Passed");
                }
            }
            // Send total question to view to user retake another quizz
            request.setAttribute("total", totalQuestion);
            // Go to view
            RequestDispatcher rd = request.getRequestDispatcher("score.jsp");
            rd.forward(request, response);
            // Delete session of quiz to block back to question after finish
            session.removeAttribute("test");
        } catch (Exception e) {
            // Go to error page
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            request.setAttribute("error", "The link you followed may be broken, or the page may have been removed.");
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
