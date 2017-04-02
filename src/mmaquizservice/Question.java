/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmaquizservice;

/**
 *
 * @author Windows 8
 */
public class Question {
    private String question = null;
    private String answerA = null;
    private String answerB = null;
    private String answerC = null;
    private String correctAnswer = null;
    
    public Question(String question, String A, String B, String C, String correctAnswer)
    {
        this.question = question;
        answerA = A;
        answerB = B;
        answerC = C;
        this.correctAnswer = correctAnswer;
    }
}
