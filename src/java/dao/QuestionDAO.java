/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sun.javafx.geom.AreaOp;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.Question;

/**
 *
 * @author Admin
 */
public class QuestionDAO {
    
    //Function add option to question with Option Content, OptionCode and for questionCode
    public void addOption(String content, int questionCode, int optionCode) throws Exception {
        String sql = "insert into Options(questionCode,optionCode,optionContent)\n"
                + "values(?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, questionCode);
            ps.setInt(2, optionCode);
            ps.setNString(3, content);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            new context.DBContext().closeConnection(rs, ps, conn);
        }
    }
        // Fuction to add true answers for question
     public void addAnswer(int questionCode, int optionCode) throws Exception {
        String sql = "insert into Answers(questionCode,optionCode)\n" +
        "values(?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, questionCode);
            ps.setInt(2, optionCode);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            new context.DBContext().closeConnection(rs, ps, conn);
        }
    }
    //Function for make new Quizz to database
    public int makeNewQuiz(String contentQuestion, List<String> optionsList, List<Integer> answersList) throws Exception {
        // Input for this funtion having 2 list: List<String> options to get all option of question
        // List<Integer> is a list about all true option answers for question

        String sql = "insert into Questions(questionContent)\n"
                + "values(?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int questionCode = 0;
        try {
            conn = new context.DBContext().getConnection();
            //After add question content, get questionCode of question which added
            ps = conn.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
            ps.setNString(1, contentQuestion);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                // Return result only have one column
                // Get question code of question which added
                questionCode = rs.getInt(1);
            }
            // Add option from option list
            for (int i = 0; i < optionsList.size(); i++) {
                addOption(optionsList.get(i), questionCode, i + 1);
            }
            //Add answers from answer list
            for (int i = 0; i < answersList.size(); i++) {
                addAnswer(questionCode, answersList.get(i));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            new DBContext().closeConnection(rs, ps, conn);
        }
        return questionCode;
    }
    // Function to get all question with pagging
    public List<Question> getAllQuestions(int pageNumber, int pageSize) throws Exception {
        List<Question> result = new ArrayList<Question>();
        String sql = "SELECT * FROM ( \n" +
        "  SELECT q.questionCode, q.questionContent,q.createdAt\n" +
        "  , ROW_NUMBER() OVER(ORDER by q.questionCode) as row \n" +
        "  FROM Questions q\n" +
        " ) q WHERE q.row >= ? and q.row <= ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (pageNumber-1)*pageSize+1);
            ps.setInt(2, pageSize*pageNumber);
            rs = ps.executeQuery();
            while (rs.next()) {
                int questionCode = rs.getInt("questionCode");
                String questionContent= rs.getNString("questionContent");
                Date createdAt = rs.getDate("createdAt");
                // Convert to format dd MMM yyyy
                String pattern = "dd-MMM-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                result.add(new Question(questionCode, questionContent,simpleDateFormat.format(createdAt)));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            //close connect
            new DBContext().closeConnection(rs, ps, conn);
        }
        return result;
    }
    
    //get total question in DB
    public int getTotalQuestions() throws Exception {
        int result = 0;
        String sql = "select COUNT(questionCode) from Questions";
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            //result in SQL will return 1 column
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // close connection
            new DBContext().closeConnection(rs, ps, conn);
        }
        return result;
    }

    //Fuction to get list True Answers of a question with question code
    public List<Integer> getListAnswers(int code) throws Exception {
        List<Integer> result = new ArrayList<>();
        // Maximum number of correct answers in a question is 4
        String sql = "select top 4 * from Answers\n"
                + "where questionCode=?";

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, code);
            rs = ps.executeQuery();
            while (rs.next()) {
                int optionCode = rs.getInt("optionCode");
                result.add(optionCode);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // close connection
            new DBContext().closeConnection(rs, ps, conn);
        }
        return result;
    }

    //Fuction to get list options of a question with question code
    public List<String> getListOption(int code) throws Exception {
        List<String> result = new ArrayList<>();
        // Maximum number of correct answers in a question is 4
        String sql = " select top 4 * from Options\n"
                + "where questionCode=?";

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, code);
            rs = ps.executeQuery();
            while (rs.next()) {
                String optionContent = rs.getNString("optionContent");
                result.add(optionContent);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // close connection
            new DBContext().closeConnection(rs, ps, conn);
        }
        return result;
    }

    //funtion to get list of random question with the number of questions
    public List<Question> getRandomQuestionList(int amount) throws Exception {
        List<Question> result = new ArrayList<>();
        String sql = "  SELECT * FROM ( \n"
                + "SELECT q.questionCode, q.questionContent,q.createdAt\n"
                + ", ROW_NUMBER() OVER(ORDER by NEWID()) as row \n"
                + "FROM Questions q\n"
                + ") q WHERE  q.row <= ?";

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                int code = rs.getInt("questionCode");
                String content = rs.getNString("questionContent");
                List<String> listOptions = getListOption(code);
                List<Integer> listAnswers = getListAnswers(code);
                //Sort answers list in ascending order
                Collections.sort(listAnswers);
                result.add(new Question(code, content, listOptions, listAnswers));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            //close connection
            new DBContext().closeConnection(rs, ps, conn);
        }
        return result;
    }
    
    //Function to delete question
    public void deleteQuestionById(int questionCode) throws Exception {
        String sql = " delete from Answers\n" +
        " where questionCode= ?\n" +
        "\n" +
        " delete from Options\n" +
        " where questionCode=?\n" +
        "\n" +
        " delete from Questions\n" +
        " where questionCode = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new context.DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, questionCode);
            ps.setInt(2, questionCode);
            ps.setInt(3, questionCode);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            new context.DBContext().closeConnection(rs, ps, conn);
        }
    }
}
