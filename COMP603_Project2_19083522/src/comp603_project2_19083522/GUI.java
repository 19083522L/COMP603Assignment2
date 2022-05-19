
package comp603_project2_19083522;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GUI extends JPanel implements ActionListener{

    enum State{
        INTRO,
        QUESTIONS,
        OUTRO
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
        
        super.add(bottomPanel, BorderLayout.SOUTH);
        super.add(labelPanel, BorderLayout.CENTER);
        
        //Action listeners
        this.enterButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if((e.getSource() == this.enterButton) && (this.state == State.INTRO) && (this.mill == null))
        {
            if(Millionaire.compareStrings("", this.text.getText()))
            {
                this.toggleEmergencyLabel("Username cannot be empty.");
                return;
            }
            
            if(this.emergencyLabel.isVisible())
                this.emergencyLabel.setVisible(false);
            this.mill = new Millionaire(this.text.getText(), this);
            this.printInstructions();
            this.text.setText("");
        }
        else if((e.getSource() == this.enterButton) && (this.state == State.INTRO) && (this.mill != null))
        {
            this.state = State.QUESTIONS;
            this.nextQuestion(this.mill.QNum);
            this.text.setText("");
        }
        
        if((e.getSource() == this.enterButton) && (this.state == State.QUESTIONS))
        {
            if(this.mill.compareStrings(this.mill.getCurrentQ().answer, this.text.getText()))
            {
                this.mill.QNum++;   
                this.text.setText("");
                this.increaseScore();
            }
            else if (this.mill.compareStrings(this.mill.lifeLines[0].toString(), this.text.getText()))
            {
                if(this.mill.lifeLines[0].uses == 1)
                {
                    this.mill.lifeLines[0].use(this.mill.QNum, this.mill.getCurrentQ());
                }
            }
        }
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
        this.label.setText("Welcome to Who wants to Be a Millionaire!\n\nMade by Liam Carter 19083522\n\n--RULES--\n\nThere are 10 questions and you will win once answer all of them correctly\nYou will lose when you get a quesition wrong!"
        + "Whenever you get a question correct you will have $100,000 added to your total. \nYou will also have 2 lifelines each of which can only be used once, these include: " + 
        "\n50/50 \nTwo of the incorrect answers will be removed.\nDouble Dip \nYou will have 2 attempts at the current question.\nTo play type in a shown answer or the name of lifeline you want to use and click the enter button \nclick the enter key to start.");
        
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
        
        this.label.setText("Question " + (this.mill.QNum + 1) + ": "+ this.mill.getCurrentQ() + "\n\n " + options);
        
        this.printLifeLines();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("HotPlate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUI());
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
