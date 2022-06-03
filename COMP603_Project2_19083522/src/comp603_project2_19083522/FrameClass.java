
package comp603_project2_19083522;

import java.awt.event.*;
import javax.swing.*;

//Liam Carter 19083522 COMP603
//My program for the assignment is an attempt at a "Who Wants to Be a Millionaire?" game but this time with a GUI
//This is used as the main of the project run this, this class was mainly built so progress could be saved when the game is closed
//If this is not working you may need to add the Java DB Driver to the libraries folder, however it should already be present 

public class FrameClass extends JFrame implements WindowListener{

    private GUI gui;
    
    public FrameClass(String title)
    {
        super(title);

        this.gui = new GUI();
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.getContentPane().add(this.gui);
        super.addWindowListener(this);
        super.pack();
        super.setVisible(true);
        super.setResizable(false);
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
    }

    @Override
    public void windowClosing(WindowEvent e)
    {        
        if(gui.mill != null)//Because the mill object is not created straight away this is needed, otherwise there will be an error
            this.gui.mill.IO.write();
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
    }

    @Override
    public void windowActivated(WindowEvent e)
    {
    }             

    @Override
    public void windowDeactivated(WindowEvent e)
    {
    }  

    public static void main(String[] args) {
        FrameClass frame = new FrameClass("Who wants to be a Millionaire!");//This is all that's needed for running the program
    }
}
