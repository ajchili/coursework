/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirinpatel;

/**
 * This class is a container for information pertaining to trivia games.
 * 
 * @author Kirin Patel
 * @version 1.0
 * @see com.kirinpatel.Game
 */
public class TriviaGame extends Game {
    
    private int prizeMoney;
    private int numberOfQuestions;
    
    /**
     * Main constructor that will set the prize money and number of questions in
     * a trivia game.
     * 
     * @param prizeMoney
     * @param numberOfQuestions 
     */
    public TriviaGame(int prizeMoney, int numberOfQuestions) {
        super("Trivia Game");
        setPrizeMoney(prizeMoney);
        setNumberOfQuestions(numberOfQuestions);
    }
    
    /**
     * Provided TriviaGame in string format.
     * 
     * @return Returns printable version of TriviaGame
     */
    @Override
    public String toString() {
        String returnValue = getDescription();
        returnValue += "\nPrize money: $" + getPrizeMoney();
        returnValue += "\nNumber of questions: " + getNumberOfQuestions();
        return returnValue;
    }
    
    /**
     * Provides the prize money of a trivia game.
     * 
     * @return Returns prize money
     */
    public int getPrizeMoney() {
        return prizeMoney;
    }
    
    /**
     * Provides the number of questions in a trivia game.
     * 
     * @return Returns number of questions
     */
    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }
    
    /**
     * Sets the prize money of a trivia game.
     * 
     * @param prizeMoney Prize money
     */
    public void setPrizeMoney(int prizeMoney) {
        if (prizeMoney > 0) {
            this.prizeMoney = prizeMoney;
        } else {
            // Error handling
            System.out.println("ERROR: MINIMUM PRIZE MONEY NEEDS TO BE GREATER THAN 0");
            this.prizeMoney = -1;
        }
    }
    
    /**
     * Sets the number of questions in a trivia game.
     * 
     * @param numberOfQuestions Number of questions
     */
    public void setNumberOfQuestions(int numberOfQuestions) {
        if (numberOfQuestions > 0) {
            this.numberOfQuestions = numberOfQuestions;
        } else {
            // Error handling
            System.out.println("ERROR: MINIMUM NUMBER OF QUESTIONS NEED TO BE GREATER THAN 0");
            this.numberOfQuestions = -1;
        }
    }
}
