/*
 * Liam Carter 19083522
 * COMP603
 * This is an abstract method for the lifelines to be built from
 */
package comp603_project2_19083522;

public abstract class LifeLine {
    
    public int uses = 1;
    public GUI gui;

    public LifeLine(GUI gui)
    {
        this.gui = gui;
    }   

    //This is for activating the effect of the lifeline
    public abstract void use(int num, QuestionClass q);

    //This toString allows for printing each lifeline
    public abstract String toString();
}
