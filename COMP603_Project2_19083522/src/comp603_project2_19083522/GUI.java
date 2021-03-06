
package comp603_project2_19083522;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Liam Carter 19083522 COMP603
//This controls a lot of the behind the scenes and visual components of the game 

public class GUI extends JPanel implements ActionListener{

    enum State{ //How the game treats the players inputs is entirely depended on these states
        INTRO,
        QUESTIONS,
        LOSS
    }
    
    private State state = State.INTRO;
    public Millionaire mill;
    public DataBase db;
    
    //Buttons
    public JButton enterButton; //Used for input what is currently in the text field
    
    //TextField
    public TextField text;
    
    //Labels
    public JLabel label;
    public JLabel scoreLabel;
    public JLabel emergencyLabel; //This Label will be shown whenever an error is detected.
    
    public GUI()
    {   
        super(new BorderLayout());
        try
        {
            this.db = new DataBase();
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("Exception: " + e.getMessage());
        }   
        
        //Component Intialization
        this.enterButton = new JButton("Enter");
        
        this.label = new JLabel("Welcome to Who wants to be a Millionaire! \nPlease enter your username.\n");
        this.scoreLabel = new JLabel("High Scores: ");
        this.emergencyLabel = new JLabel("");
        
        //Hides labels that need to wait for data
        this.scoreLabel.setVisible(false);
        this.emergencyLabel.setVisible(false);
        
        this.text = new TextField("");
        this.text.setPreferredSize(new Dimension(200, 10));
        
        //panel settings
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(this.label, BorderLayout.NORTH);
        labelPanel.add(this.scoreLabel, BorderLayout.CENTER);
        labelPanel.add(this.emergencyLabel, BorderLayout.SOUTH);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(this.enterButton, BorderLayout.EAST);
        bottomPanel.add(this.text, BorderLayout.WEST);
        
        labelPanel.setPreferredSize(new Dimension(300, 100));
        
        super.add(bottomPanel, BorderLayout.SOUTH);
        super.add(labelPanel, BorderLayout.CENTER);
        
        super.setPreferredSize(new Dimension(500, 400));
        
        //Action listeners
        this.enterButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //First thing the user will see is a prompt for an acceptable username 
        //The program waits to create a millionaire class since a username is required for the millionaire constructor 
        //and would otherwise require a total rewrite of the Millioaire code
        if((e.getSource() == this.enterButton) && (this.state == State.INTRO) && (this.mill == null))
        {
            if(!this.acceptableName(this.text.getText()))
            {
                this.toggleEmergencyLabel("Username cannot be empty or include spaces.");
                this.text.setText("");
                return;
            }
            else
            {
                if(this.emergencyLabel.isVisible())
                    this.emergencyLabel.setVisible(false);
                this.mill = new Millionaire(this.text.getText(), this, this.db);
                this.printInstructions();
            }
            
            this.text.setText("");
        }
        //This is for starting the game after the instructions have been displayed
        else if((e.getSource() == this.enterButton) && (this.state == State.INTRO) && (this.mill != null))
        {
            this.state = State.QUESTIONS;
            this.nextQuestion();
            this.scoreLabel.setVisible(true);
            this.emergencyLabel.setVisible(false);
            this.printLifeLines();
            this.text.setText("");
        }
        
        //This is for printing the questions to the label and handling answers
        if((e.getSource() == this.enterButton) && (this.state == State.QUESTIONS))
        {
            this.mill.IO.write();
            this.printLifeLines();        
            
            if(this.mill.AnswerQuestion(this.text.getText()))//For answer handling
            {
                this.emergencyLabel.setVisible(false);
                this.mill.QNum++; 
                this.increaseScore();
                this.text.setText("");    
                this.printLifeLines();
                if(this.mill.score == 1000000) //two different methods are used if the player wins or loses, functionally they are the same just with different messages
                {
                    this.gameWon();
                }  
                else
                {
                    this.nextQuestion(); 
                }               
            }
            //The following else ifs are for determining if the user entered a lifeline
            else if (this.mill.compareStrings(this.mill.lifeLines[0].toString(), this.text.getText())) //50/50
            {
                if(this.mill.lifeLines[0].uses == 1)
                {
                    this.mill.lifeLines[0].use(this.mill.QNum, this.mill.getCurrentQ());
                    this.printLifeLines();
                    this.toggleEmergencyLabel("You have used the 50/50 lifeline.");
                    this.text.setText("");                   
                }
                else
                {
                    this.label.setText("");
                    this.printLifeLines();
                    this.toggleEmergencyLabel("You have already used the 50/50 lifeline.");
                }
            }
            else if (this.mill.compareStrings(this.mill.lifeLines[1].toString(), this.text.getText()))
            {
                if(this.mill.lifeLines[1].uses == 1)
                {
                    this.mill.lifeLines[1].use(this.mill.QNum, this.mill.getCurrentQ());
                    this.printLifeLines();
                    this.toggleEmergencyLabel("You have used the Double Dip lifeline.");
                    this.text.setText("");    
                }
                else
                {
                    this.label.setText("");
                    this.printLifeLines();
                    this.toggleEmergencyLabel("You have already used the Double Dip lifeline.");
                }
            }
            //The default else is for if the player gets an answer wrong
            else
            {
                this.mill.getCurrentQ().attempts--;
                //There are multiple ifs required for the first and non-first questions (There is a problem that will cause the player to instantly lose if they use the same criteria, 
                //the only fix for this too either have a state for the first question or too have some other way of distinguishing between the two)
                
                //For the fisrt question logic
                if((this.mill.getCurrentQ().attempts < 0) && (this.mill.QNum == 0))
                {
                    this.gameLoss();
                }
                else if((this.mill.getCurrentQ().attempts == 0) && (this.mill.lifeLines[1].uses == 0))
                {
                    this.toggleEmergencyLabel("That is incorrect! good thing you used your lifeline!");
                    this.text.setText("");
                }
                //Non first question logic
                else if((this.mill.getCurrentQ().attempts == 0) && (this.mill.QNum != 0))
                {
                    this.gameLoss();
                }
                else if((this.mill.getCurrentQ().attempts == 1) && (this.mill.QNum != 0) && (this.mill.lifeLines[1].uses == 0))
                {
                    this.toggleEmergencyLabel("That is incorrect! good thing you used your lifeline!");
                    this.text.setText("");
                }
            }
        }
        //This finally is for restarting the game after losing or winning
        if((e.getSource() == this.enterButton) && (this.state == State.LOSS))
        {
            this.state = State.INTRO;
            String temp = this.mill.user;
            this.mill = new Millionaire(temp, this, this.db);
        }
    }
    
    //This is needed because the username is not allowed to be empty or include spaces, this is because either can create problems with the read function
    public boolean acceptableName(String input)
    {
        int num = input.length();
        
        if(num == 0)
        {
            return false;
        }
        
        for(int counter = 0; counter < input.length(); counter++)
        {
            if(input.charAt(counter) == ' ')
            {
                return false;
            }
        }
        
        return true;
    }
    
    public void gameLoss()
    {
        this.state = State.LOSS;
        this.label.setText("<html>You are incorrect! your progress has been saved.<br/> You may try again if you wish, your score is: " + this.mill.score + "</html>");
        this.toggleEmergencyLabel("If you want to restart click the enter button, otherwise close the application.");
        this.text.setText("");
        this.mill.IO.write();  
    }

    public void gameWon()
    {
        this.state = State.LOSS;
        this.label.setText("<html>Congratulations! you have won Who wants to be a Millionaire! your progress has been saved.<br/> You may try again if you wish, your score is: " + this.mill.score + "</html>");
        this.toggleEmergencyLabel("If you want to restart click the enter button, otherwise close the application.");
        this.text.setText("");
        this.mill.IO.write();
    }
    
    public void increaseScore()
    {
        this.mill.addScore();
    }
    
    public void toggleEmergencyLabel(String output)//Originally used to display error messages, but is also used for other messages of importance
    {
        this.emergencyLabel.setText(output);
        this.emergencyLabel.setVisible(true);
    }
    
    //print methods
    public void printInstructions()
    {
        this.label.setText("<html>Welcome to Who wants to Be a Millionaire!<br/><br/>Made by Liam Carter 19083522<br/><br/>--RULES--<br/><br/>There are 10 questions and you will win once answer all of them correctly<br/>You will lose when you get a quesition wrong!"
        + "Whenever you get a question correct you will have $100,000 added to your total. <br/>You will also have 2 lifelines each of which can only be used once, these include: " + 
        "<br/>50/50<br/>Two of the incorrect answers will be removed.<br/>Double Dip <br/>You will have 2 attempts at the current question.<br/>To play type in a shown answer or the name of lifeline you want to use and click the enter button:" + 
            "<br/>Click the enter button to start.</html>");
        
        this.printHighScores();
    }
    
    public void printHighScores()
    {
        String input = this.mill.IO.highScores;
        
        this.scoreLabel.setVisible(true);
        this.scoreLabel.setText(input);
    }
    
    public void printLifeLines()
    {
        if((this.mill.lifeLines[0].uses == 1) && (this.mill.lifeLines[1].uses == 1))
        {
            this.scoreLabel.setText("Score: $" + this.mill.score + " Remaining Lifelines: " + this.mill.lifeLines[0] + ", " + this.mill.lifeLines[1]);
        }
        else if(this.mill.lifeLines[0].uses == 1)
        {
            this.scoreLabel.setText("Score: $" + this.mill.score + " Remaining Lifelines: " + this.mill.lifeLines[0]);
        }
        else if(this.mill.lifeLines[1].uses == 1)
        {
            this.scoreLabel.setText("Score: $" + this.mill.score + " Remaining Lifelines: " + this.mill.lifeLines[1]);
        }
        else
        {
            this.scoreLabel.setText("Score: $" + this.mill.score + " Remaining Lifelines: No lifelines left!");
        }
    }
    
    //This for getting the question and preparing it for the GUI

    public void nextQuestion()
    {
        String options = "";
        
        for(int counter = 0; counter < this.mill.getCurrentQ().options.length; counter++)
        {
            options += "<html> " + this.mill.getCurrentQ().options[counter] + "<br/>";
        }
        
        options += "</html>";
        
        this.label.setText("<html>Question " + (this.mill.QNum + 1) + ": "+ this.mill.getCurrentQ().getQuestion() + "<br/><br/>" + options + "</html>");
        
        this.printLifeLines();
    }
}
