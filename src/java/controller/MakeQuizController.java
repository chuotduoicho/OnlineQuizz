 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MakeQuizController extends HttpServlet {

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
            RequestDispatcher rd = request.getRequestDispatcher("makeQuiz.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "There's some problem.Please try again later");
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
            String newQuestionContent = request.getParameter("question");
            String option1 = request.getParameter("option1Content");
            String option2 = request.getParameter("option2Content");
            String option3 = request.getParameter("option3Content");
            String option4 = request.getParameter("option4Content");

            String checkOption1 = request.getParameter("option1Check");
            String checkOption2 = request.getParameter("option2Check");
            String checkOption3 = request.getParameter("option3Check");
            String checkOption4 = request.getParameter("option4Check");

            boolean check = true;
            // case 4 options checked
            try {
                if (checkOption1.equals("checked") && checkOption2.equals("checked") && checkOption3.equals("checked")
                        && checkOption4.equals("checked")) {
                    check = false;
                }
            } catch (NullPointerException e) { // if one option is null --> pass this case
                check = true;
            }
            // Question and 4 options can not be blank
            if (newQuestionContent == null || option1 == null || option2 == null || option3 == null || option4 == null) {
                check = false;
            }
            // At least one correct answer
            if (checkOption1 == null && checkOption2 == null && checkOption3 == null && checkOption4 == null) {
                check = false;
            }

            if (check) {
                // Create options
                List<String> optionsList = Arrays.asList(option1, option2, option3, option4);
                // Create true answers
                List<Integer> answers = new ArrayList<>();
                // If check box for options is checked, add to answers list
                if (checkOption1 != null && checkOption1.equals("checked")) {
                    answers.add(1);
                }
                if (checkOption2 != null && checkOption2.equals("checked")) {
                    answers.add(2);
                }
                if (checkOption3 != null && checkOption3.equals("checked")) {
                    answers.add(3);
                }
                if (checkOption4 != null && checkOption4.equals("checked")) {
                    answers.add(4);
                }
                // Add new question to Database
                try {
                    QuestionDAO question = new QuestionDAO();
                    question.makeNewQuiz(newQuestionContent, optionsList, answers);
                    request.setAttribute("success", true);
                } catch (Exception e) {
                    // If an error occurs,false
                    request.setAttribute("unsuccess", true);
                }
            } else {
                // data failed
                request.setAttribute("unsuccess", true);
            }
            RequestDispatcher rd = request.getRequestDispatcher("makeQuiz.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            // If error go to make page
            response.sendRedirect("make");
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
