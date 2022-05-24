
package comp603_project2_19083522;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//This acts as the main class of the game

public class GUI extends JPanel implements ActionListener{

    enum State{
        INTRO,
        QUESTIONS,
        OUTRO,
        LOSS
    }
    
    private State state = State.INTRO;
    public Millionaire mill;
    
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
        
//        JPanel buttonPanel = new JPanel(new BorderLayout());
//        buttonPanel.add(this.toggleButton, BorderLayout.NORTH);
//        buttonPanel.add(this.enterButton, BorderLayout.SOUTH);
        
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
        if((e.getSource() == this.enterButton) && (this.state == State.INTRO) && (this.mill == null))
        {
            if(!this.acceptableName(this.text.getText()))
            {
                this.toggleEmergencyLabel("Username cannot be empty or include spaces.");
                return;
            }
            else
            {
                if(this.emergencyLabel.isVisible())
                    this.emergencyLabel.setVisible(false);
                this.mill = new Millionaire(this.text.getText(), this);
                this.printInstructions();
            }
            
            this.text.setText("");
        }
        //This is for starting the game after the instructions have been displayed
        else if((e.getSource() == this.enterButton) && (this.state == State.INTRO) && (this.mill != null))
        {
            this.state = State.QUESTIONS;
            this.nextQuestion(this.mill.QNum);
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
            
            if(this.mill.AnswerQuestion(this.text.getText()))
            {
                this.emergencyLabel.setVisible(false);
                this.mill.QNum++;   
                this.text.setText("");
                this.increaseScore();
            }
            else if (this.mill.compareStrings(this.mill.lifeLines[0].toString(), this.text.getText())) //50/50
            {
                if(this.mill.lifeLines[0].uses == 1)
                {
                    this.mill.lifeLines[0].use(this.mill.QNum, this.mill.getCurrentQ());
                    this.printLifeLines();
                    this.toggleEmergencyLabel("You have used the 50/50 lifeline.");
                }
            }
            else if (this.mill.compareStrings(this.mill.lifeLines[1].toString(), this.text.getText()))
            {
                if(this.mill.lifeLines[1].uses == 1)
                {
                    this.mill.lifeLines[1].use(this.mill.QNum, this.mill.getCurrentQ());
                    this.printLifeLines();
                    this.toggleEmergencyLabel("You have used the Double Dip lifeline.");
                }
            }
            else
            {
                this.mill.getCurrentQ().attempts--;
                if(this.mill.getCurrentQ().attempts != 0)
                {
                    this.gameLoss();
                }
                else if(this.mill.getCurrentQ().attempts > 0)
                {
                    this.toggleEmergencyLabel("That is incorrect! good thing you used your lifeline!");
                    this.text.setText("");
                }
            }
        }
        
        if((e.getSource() == this.enterButton) && (this.state == State.LOSS))
        {
            this.toggleEmergencyLabel("Click the enter button again to confirm.");
            this.state = State.INTRO;
            String temp = this.mill.user;
            this.mill = new Millionaire(temp, this);
        }
    }
    
    //This is needed because the username is not allowed to be empty or include, this is because either can create problems with the read function
    public boolean acceptableName(String input)
    {
        int count = 0;
        
        for(char current : input.toCharArray())
        {
            if((current == ' ') || (current == Character.MIN_VALUE))
            {
                count++;
            }
        }
        
        if(count == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void gameLoss()
    {
        this.state = State.LOSS;
        this.label.setText("You are incorrect! your progress has been saved you may try again if you wish, your score: " + this.mill.score);
        this.mill.IO.write();
        this.toggleEmergencyLabel("If you want to restart click the enter button, otherwise close the application.");
    }
    
    public void increaseScore()
    {
        this.mill.score = this.mill.score + 100000;
    }
    
    public void toggleEmergencyLabel(String output)//Used displays an error message when something goes wrong
    {
        this.emergencyLabel.setText(output);
        this.emergencyLabel.setVisible(true);
    }
    
    public void printInstructions()
    {
        this.label.setText("<html>Welcome to Who wants to Be a Millionaire!<br/><br/>Made by Liam Carter 19083522<br/><br/>--RULES--<br/><br/>There are 10 questions and you will win once answer all of them correctly<br/>You will lose when you get a quesition wrong!"
        + "Whenever you get a question correct you will have $100,000 added to your total. <br/>You will also have 2 lifelines each of which can only be used once, these include: " + 
        "<br/>50/50<br/>Two of the incorrect answers will be removed.<br/>Double Dip <br/>You will have 2 attempts at the current question.<br/>To play type in a shown answer or the name of lifeline you want to use and click the enter button:" + 
            "<br/>Click the enter key to start.</html>");
        
        this.printLifeLines();
    }
    
    public void printLifeLines()
    {
        if((this.mill.lifeLines[0].uses == 1) && (this.mill.lifeLines[1].uses == 1))
        {
            this.scoreLabel.setText("Score: $" + this.mill.score + " Remaining Lifelines: " + this.mill.lifeLines[0] + ", " + this.mill.lifeLines[1]);
        }
        else if(this.mill.lifeLines[0].uses == 1)
        {
            this.scoreLabel.setText("Score: $" + this.mill.score + "Remaining Lifelines: " + this.mill.lifeLines[1]);
        }
        else if(this.mill.lifeLines[1].uses == 1)
        {
            this.scoreLabel.setText("Score: $" + this.mill.score + "Remaining Lifelines: " + this.mill.lifeLines[0]);
        }
        else
        {
            this.scoreLabel.setText("Score: $" + this.mill.score + "Remaining Lifelines: No lifelines left!");
        }
    }
    
    public void nextQuestion(int num)
    {
        String options = "";
        
        for(int counter = 0; counter < this.mill.getCurrentQ().options.length; counter++)
        {
            options += this.mill.getCurrentQ().options[counter] + "\n";
        }
        
        this.label.setText("Question " + (this.mill.QNum + 1) + ": "+ this.mill.getCurrentQ() + "\n\n" + options);
        
        this.printLifeLines();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Who wants to be a Millionaire!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUI());
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
