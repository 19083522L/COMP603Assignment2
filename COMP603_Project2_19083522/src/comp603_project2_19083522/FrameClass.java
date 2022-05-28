
package comp603_project2_19083522;

import java.awt.event.*;
import javax.swing.*;

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
        FrameClass frame = new FrameClass("Who wants to be a Millionaire!");
    }
}
