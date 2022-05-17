
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
    private Millionaire mill;
    
    //Buttons
    private JButton enterButton; //Used for input what is currently in the text field
    private JButton toggleButton; //Used for toggling lifeline input
    
    //TextField
    private TextField text;
    
    //Labels
    private JLabel label;
    private JLabel scoreLabel;
    private JLabel emergencyLabel; //This Label will be shown whenever an error is detected.
    
    public GUI()
    {   
        super(new BorderLayout());
        
        //Component Intialization
        this.enterButton = new JButton("Enter");
        this.toggleButton = new JButton("Input a LifeLine");
        
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
        
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(this.toggleButton, BorderLayout.NORTH);
        buttonPanel.add(this.enterButton, BorderLayout.SOUTH);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        bottomPanel.add(this.text, BorderLayout.WEST);
        
        super.add(bottomPanel, BorderLayout.SOUTH);
        super.add(labelPanel, BorderLayout.CENTER);
        
        //Action listeners
        this.enterButton.addActionListener(this);
        this.toggleButton.addActionListener(this);
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
            this.mill = new Millionaire(this.text.getText());
            this.text.setText("");
        }
    }
    
    public void toggleEmergencyLabel(String output)//Used displays an error message when something goes wrong
    {
        this.emergencyLabel.setText(output);
        this.emergencyLabel.setVisible(true);
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
