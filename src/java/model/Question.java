/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Question {
    private int questionCode;
    private String questionContent;
    private String createdAt;
    private List<String> optionsList;
    private List<Integer> answersList;
    private List<Integer> userChoice;

    public Question(int questionCode, String questionContent, String createdAt, List<String> optionsList, List<Integer> answersList) {
        this.questionCode = questionCode;
        this.questionContent = questionContent;
        this.createdAt = createdAt;
        this.optionsList = optionsList;
        this.answersList = answersList;
    }
    
    public Question(int questionCode, String questionContent, List<String> optionsList, List<Integer> answersList) {
        this.questionCode = questionCode;
        this.questionContent = questionContent;
        this.optionsList = optionsList;
        this.answersList = answersList;
    }
    
    public Question(int questionCode, String questionContent, String createdAt) {
        this.questionCode = questionCode;
        this.questionContent = questionContent;
        this.createdAt = createdAt;
    }

    public int getQuestionCode() {
        return questionCode;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<String> getOptionsList() {
        return optionsList;
    }

    public List<Integer> getAnswersList() {
        return answersList;
    }

    public List<Integer> getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(List<Integer> userChoice) {
        this.userChoice = userChoice;
    }
    
    
}
